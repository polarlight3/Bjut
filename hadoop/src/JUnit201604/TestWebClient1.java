package JUnit201604;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by hadoop on 4/1/16.
 */
public class TestWebClient1 {

    @BeforeClass
    public static void setUp(){
        TestWebClient1 t = new TestWebClient1();
        URL.setURLStreamHandlerFactory(t.new StubStreamHandlerFactory());

    }
    private class StubStreamHandlerFactory implements URLStreamHandlerFactory{

        public URLStreamHandler createURLStreamHandler(String protocol){
            System.out.println("execute factory");

            return new StubHttpURLStreamHandler();
        }
    }

    private class StubHttpURLStreamHandler extends URLStreamHandler{
        protected URLConnection openConnection(URL url) throws IOException{
            System.out.printf("execute handler");
            return new StubHttpURLConnection(url);
        }

    }

    @Test
    public void testGetContentOk() throws Exception{
        WebClient client = new WebClient();
        String result = client.getContent(new URL("http://localhost"));
        assertEquals("It works", result);
    }
}