package org.brandonberg28;

public class SubtractionArithmeticOperator implements ArithmeticOperator
{

   public int execute(int num1, int num2)
   {
      return ArithmeticPerformer.subtract(num1,num2);
   }
  
}