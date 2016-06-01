package Junit;

/**
 * Created by hadoop on 3/28/16.
 */
public interface RequestHandler {
    Response process(Request request) throws Exception;
}
