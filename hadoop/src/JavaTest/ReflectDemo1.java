package JavaTest;


import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by hadoop on 3/24/16.
 */
public class ReflectDemo1 {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> ar = new ArrayList<>();
        ar.add(1);
        ar.add(2);
//        ar.add("he");
        Class clazz = Class.forName("java.util.ArrayList");
        Method m = clazz.getDeclaredMethod("add", Object.class);
        m.invoke(ar, "hello");
        System.out.println(ar);
    }
}
