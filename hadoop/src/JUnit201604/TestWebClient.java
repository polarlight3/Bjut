package JUnit201604;

import com.google.common.net.HttpHeaders;
import org.junit.*;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by hadoop on 4/1/16.
 */
public class TestWebClient {
    @BeforeClass
    public static void setUp() throws Exception{
        Server server = new Server(8090);
        TestWebClient t = new TestWebClient();
        Context contentOkContext = new Context(server, "/testGetContentOk");
        contentOkContext.setHandler(t.new TestGetContentOkHandler());

        Context contentNotFoundContext = new Context(server, "/testGetContentNotFound");
        contentNotFoundContext.setHandler(t.new TestGetContentNotFoundHandler());
        server.setStopAtShutdown(true);
        server.start();
    }

    @Test
    public void testGetContentOK() throws Exception{
        WebClient client = new WebClient();
        String result = client.getContent(new URL(
                "http://localhost:8090/testGetContentOk"
        ));
        assertEquals("It works", result);
    }

    @Test
    public void testGetContentNotFound() throws Exception{
        WebClient client = new WebClient();
        String result = client.getContent(new URL(
                "http://localhost:8090/testGetContentNotFound"));
        assertNull(result);
    }

    @AfterClass
    public static void tearDown(){}

    private class TestGetContentOkHandler extends AbstractHandler{
        public void handle(String target, HttpServletRequest request,
                           HttpServletResponse response, int dispatch) throws IOException {
            System.out.printf("execute TestGetOK handler");

            OutputStream out = response.getOutputStream();
            ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer();
            writer.write("It works");
            writer.flush();
            response.setIntHeader(HttpHeaders.CONTENT_LENGTH, writer.size());
            writer.writeTo(out);
            out.flush();
        }
    }

    private class TestGetContentNotFoundHandler extends AbstractHandler{
        public void handle(String target, HttpServletRequest request,
                           HttpServletResponse response, int dispatch) throws IOException {
            System.out.printf("execute NOT Found handler");

            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
