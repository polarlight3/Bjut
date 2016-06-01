package Junit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hadoop on 3/28/16.
 */
public class DefaultController implements Controller {
    private Map requestHandlers = new HashMap();
    protected RequestHandler getHandler(Request request){
        if (!this.requestHandlers.containsKey(request.getName())){
            String message = "Cannot find handler for request name " + "[" + request.getName() + "]";
            throw new RuntimeException(message);
        }
        return (RequestHandler)this.requestHandlers.get(request.getName());
    }

    public Response processRequest(Request request){
        Response response;
        try {
            response = getHandler(request).process(request);
        }
        catch (Exception e){
            response = new ErrorResponse(request, e);
        }
        return response;
    }

    public void addHandler(Request request, RequestHandler requestHander){
        if (this.requestHandlers.containsKey(request.getName())){
            throw new RuntimeException("A request handler has" +
                    "already been registered for request name" +
                    "[" + request.getName() + "]");
        }
        else{
            this.requestHandlers.put(request.getName(), requestHander);
        }
    }
}
