package org.brandonberg28;

public class AdditionArithmeticOperator implements ArithmeticOperator
{

   public int execute(int num1, int num2)
   {
      return ArithmeticPerformer.add(num1,num2);
   }
  
}