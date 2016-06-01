package Junit;

/**
 * Created by hadoop on 3/28/16.
 */
public class ErrorResponse implements Response {
    private  Request originalRequest;
    private Exception originalException;
    public ErrorResponse(Request request, Exception exception){
        this.originalRequest = request;
        this.originalException = exception;
    }
    public String getName(){
        return "test";
    }
    public Request getOriginalRequest(){
        return this.originalRequest;
    }
    public Exception getOriginalException(){
        return this.originalException;
    }
}
