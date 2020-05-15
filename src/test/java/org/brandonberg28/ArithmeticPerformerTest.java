package org.brandonberg28;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ArithmeticPerformerTest {
    
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
        assertEquals(expected, actual);
        //assertTrue(expected==actual);  
    }

    @Test
    public void subtractTest()
    {
        //arrange
        int num1 = 20;
        int num2 = 18;
        int expected = num1 - num2;
        int actual;

        //act
        actual = ArithmeticPerformer.subtract(num1, num2);

        //assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void multiplyTest()
    {
        //arrange
        int num1 = 4;
        int num2 = 2;
        int expected = num1 * num2;
        int actual;

        //act
        actual = ArithmeticPerformer.multiply(num1, num2);

        //assert
        assertEquals(expected, actual);
    }

    @Test
    public void divideTest()
    {
        //arrange
        int num1 = 10;
        int num2 = 5;
        int expected = num1 / num2;
        int actual;

        //act
        actual = ArithmeticPerformer.divide(num1, num2);

        //assert
        assertEquals(expected, actual);
    }

    @Test
    public void wildTest()
    {
        //arrange
        int num1 = 10;
        int num2 = 2;
        int num3 = 6;
        int num4 = 10;
        int num5 = 3;
        int expected = 20;
        int actual;

        //act
        actual = ArithmeticPerformer.add(num1, num2);         // 10 + 2 = 12
        actual = ArithmeticPerformer.subtract(actual, num3);  // 12 - 6 = 6
        actual = ArithmeticPerformer.multiply(actual,num4);   // 6 * 10 = 60
        actual = ArithmeticPerformer.divide(actual,num5);     // 60 / 3 = 20

        //assert
        assertEquals(expected, actual);
    }

}