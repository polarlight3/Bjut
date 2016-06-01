package Junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by hadoop on 3/28/16.
 */
@RunWith(value = Parameterized.class)
public class ParameterizedTest {
    private double expected;
    private double valueOne;
    private double valueTwo;
    
    @Parameterized.Parameters
    public static Collection<Integer[]> getTest(){
        return Arrays.asList(new Integer[][]{
                {2, 1, 1},
                {3, 2, 1},
                {4, 3, 1}
        });
    }
    
    public ParameterizedTest(double expected, double valueOne, double valueTwo){
        this.expected = expected;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }
    
    @Test
    public void sum(){
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.add(valueOne, valueTwo), 0);
    }
}
