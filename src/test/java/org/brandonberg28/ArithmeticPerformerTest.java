package org.brandonberg28;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ArithmeticPerformerTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void addTest()
    {
        //arrange
        int num1 = 2;
        int num2 = 2;
        int expected = num1 + num2;
        int actual;

        //act
        actual = ArithmeticPerformer.add(num1, num2);

        //assert
        assertEquals(expected, actual, "messageSupplier");
        //assertTrue(expected==actual);
        
    }
    

}