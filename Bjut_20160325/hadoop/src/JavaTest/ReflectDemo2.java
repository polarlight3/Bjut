package JavaTest;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by hadoop on 3/24/16.
 */
public class ReflectDemo2 {
    public static void main(String[] args) throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("/home/hadoop/workspace/hadoop/config.properties"));
        Class clazz = Class.forName(p.getProperty("className"));
        Constructor c = clazz.getConstructor();
        Object obj = c.newInstance();
        Method m = clazz.getMethod(p.getProperty("methodName"), null);
        m.invoke(obj, null);
    }
}
