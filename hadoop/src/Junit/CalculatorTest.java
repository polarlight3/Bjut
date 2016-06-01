package Junit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * Created by hadoop on 3/25/16.
 */
public class CalculatorTest {
    Calculator jt;
    @Before //何谓 Fixture？它是指在执行一个或者多个测试方法时需要的一系列公共资源或者数据，例如测试环境，测试数据等等
    public  void setUp(){
        jt = new Calculator();
    }
    @Test
    public void testAdd(){
        double rel = jt.add(23, 24);
        assertEquals("add there is a problem", 46, rel, 1);
    }

    @Test
    public void testMinus(){
        double rel = jt.minus(3, 24);
        assertEquals("minus there is a problem", rel, -21, 0);
    }

    @Test(expected = AssertionError.class)
    public void testDivide(){
        double rel = jt.divide(24, 0);
        assertEquals("divide there is a problem", rel, 0, 0);
    }

    @Test(timeout = 100)
    public void testMul(){
        double rel = jt.mul(2, 4);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("mul there is a problem", rel, 8, 0);
    }
}
