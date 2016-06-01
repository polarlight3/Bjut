package JUnit201604;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;

/**
 * Created by hadoop on 4/1/16.
 */
public class JettySample {
    public static void main(String[] args) throws Exception{
        Server server = new Server(9980);
        Context root = new Context(server, "/home/hadoop/Desktop");
        root.setResourceBase("./test");
        root.setHandler(new ResourceHandler());
        server.start();
    }
}
