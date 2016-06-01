package Junit;

/**
 * Created by hadoop on 3/28/16.
 */
public interface Controller {
    Response processRequest(Request request);
    void addHandler(Request request, RequestHandler requestHandler);
}
