package JavaTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by hadoop on 3/24/16.
 */
public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("JavaTest.Student");
/*        Constructor[] c = clazz.getConstructors();
        for(Constructor con:c)
            System.out.println(con);*/

/*        Constructor c = clazz.getConstructor();
        Object obj = c.newInstance();
        System.out.println(obj);*/
        Constructor c = clazz.getConstructor(int.class, String.class);
        Object obj = c.newInstance(2,"kobe");
        System.out.println(obj);
        Field f = clazz.getField("name");
        Field f1 = clazz.getDeclaredField("age");
        f1.setAccessible(true);
        f.set(obj, "bryant");
        f1.set(obj, 24);
        System.out.println(f.get(obj));
        System.out.println(f1.get(obj));

        Method m = clazz.getDeclaredMethod("show", String.class, int.class);
        m.invoke(obj,"lebron", 23);



    }
}
