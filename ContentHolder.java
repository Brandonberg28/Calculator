import java.util.*;
import java.text.*;
import java.io.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;

public class ContentHolder extends VBox
{

   private TextField answerTextBox = new TextField("Calculate something...");
   private HBox buttonHolder = new HBox();
   
   private FlowPane numButtonHolder = new FlowPane();
   private VBox arithmeticButtonHolder = new VBox();
   
   private Button [] numButtonArr = new Button [12];  //array that holds number buttons
   
   private Button add = new Button("+");
   private Button subtract = new Button("-");
   private Button multiply = new Button("*");
   private Button clear = new Button("Clear");
   private Button equal = new Button("=");
   private Button divide = new Button("/"); 
   
   private String userNumberInput = ""; //Queue to hold numbers for calculator to do Math on
   private int num1 = 0;
   private int num2 = 0;
   private boolean arithmeticButtonPressed = false;
   
   private int additionActivator = 0; 
   private int subtractionActivator = 0;
   private int multiplicationActivator = 0;
   private int divisionActivator = 0;
    
   public ContentHolder()  
   {
      setupAnswerTextBox();

      setupButtonHolder();
                  
      getChildren().add(answerTextBox);
      getChildren().add(buttonHolder);
   }
   
   private void setupAnswerTextBox()   
   {
      //set text properties
      answerTextBox.setAlignment(Pos.CENTER);
      answerTextBox.setPrefSize(240,70);
   } 
   
   private void setupButtonHolder()
   {
      //set buttonHolder properties
      buttonHolder.setPrefSize(240,240); 
      buttonHolder.setAlignment(Pos.CENTER);
      
      numButtonHolder.setPrefSize(180,240);
      
      for(int i=1; i<10; i++)  //create number buttons 1-9 and store in array
      {        
         var btn = new Button(Integer.toString(i));
         btn.setPrefSize(60,60);
         btn.setOnAction(new ButtonListener());
         numButtonArr[i] = btn;
         numButtonHolder.getChildren().add(btn);
      }
      
      //create and add 0, clear & = buttons to numButtonHolder array
      numButtonArr[0] = new Button("0");
      numButtonArr[10] = new Button("clear");
      numButtonArr[11] = new Button("=");
      numButtonArr[0].setPrefSize(60,60);
      numButtonArr[10].setPrefSize(60,60);
      numButtonArr[11].setPrefSize(60,60);
      numButtonArr[0].setOnAction(new ButtonListener());
      numButtonArr[10].setOnAction(new ButtonListener());
      numButtonArr[11].setOnAction(new ButtonListener());
      
      numButtonHolder.getChildren().add(numButtonArr[0]);
      numButtonHolder.getChildren().add(numButtonArr[10]);
      numButtonHolder.getChildren().add(numButtonArr[11]);
      
      //set properties of arithmeticButtons     
      add.setPrefSize(60,60);
      subtract.setPrefSize(60,60);
      multiply.setPrefSize(60,60);
      divide.setPrefSize(60,60);          
      
      //make individual button listeners for each artihmeticButton
      add.setOnAction(new AddButtonListener());
      subtract.setOnAction(new SubtractButtonListener());
      multiply.setOnAction(new MultiplyButtonListener());
      divide.setOnAction(new DivideButtonListener());    
      
      arithmeticButtonHolder.getChildren().add(add); 
      arithmeticButtonHolder.getChildren().add(subtract);
      arithmeticButtonHolder.getChildren().add(multiply);
      arithmeticButtonHolder.getChildren().add(divide);   
      
      buttonHolder.getChildren().add(numButtonHolder);
      buttonHolder.getChildren().add(arithmeticButtonHolder);
   }
   
   private void addNumber(String number)
   {
      userNumberInput += number;
      answerTextBox.setText(userNumberInput);
      
      //answerTextBox.setText(answerTextBox.getText() + number); //NEW CHANGE WITH THIS
   }
   
   public class ButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         for(int i=0; i<10; i++)  //add number (0-9) to textbox if pressed
         {
            if(e.getSource() == numButtonArr[i]) 
            {
               addNumber(Integer.toString(i));
            }
         }
         
         if(e.getSource() == numButtonArr[10])  //execute if CLEAR button is pressed
         {
            answerTextBox.setText("");
            userNumberInput = "";
            additionActivator = 0;
            subtractionActivator = 0;
            multiplicationActivator = 0;
            divisionActivator = 0;
         }
         
         if(e.getSource() == numButtonArr[11])  //execute if = button is pressed
         {
            if(additionActivator > 0)
            {
               num2 = Integer.parseInt(userNumberInput);    //parse user's 2nd num to an int
               answerTextBox.setText(Integer.toString(ArithmeticPerformer.add(num1,num2)));  //display sum in textBox as a String
               userNumberInput = Integer.toString(ArithmeticPerformer.add(num1,num2));       //make sum the new userNumberInput 
               additionActivator = 0;                                                        //resets arithmetic activator
            }
            else if(subtractionActivator > 0)
            {
               num2 = Integer.parseInt(userNumberInput);    //parse user's 2nd num to an int
               answerTextBox.setText(Integer.toString(ArithmeticPerformer.subtract(num1,num2)));  //display sum in textBox as a String
               userNumberInput = Integer.toString(ArithmeticPerformer.subtract(num1,num2));
               subtractionActivator = 0;
            }
            else if(multiplicationActivator > 0)
            {
               num2 = Integer.parseInt(userNumberInput);    //parse user's 2nd num to an int
               answerTextBox.setText(Integer.toString(ArithmeticPerformer.multiply(num1,num2)));  //display sum in textBox as a String
               userNumberInput = Integer.toString(ArithmeticPerformer.multiply(num1,num2));
               multiplicationActivator = 0;
            }
            else if(divisionActivator > 0)
            {
               num2 = Integer.parseInt(userNumberInput);    //parse user's 2nd num to an int
               answerTextBox.setText(Integer.toString(ArithmeticPerformer.divide(num1,num2)));  //display sum in textBox as a String
               userNumberInput = Integer.toString(ArithmeticPerformer.divide(num1,num2));
               divisionActivator = 0;
            }
         }
                          
      }
   }
   
   public class AddButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         answerTextBox.setText("+");                    //set the answerTextBox to show "+"
         num1 = Integer.parseInt(userNumberInput);      //parse user's 1st num to an int
         additionActivator++;                           //keep track that addButton was pressed
         userNumberInput = "";                          //clear user number input for num2
      }
   }
   
   public class SubtractButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         answerTextBox.setText("-");                    //set the answerTextBox to show "-"
         num1 = Integer.parseInt(userNumberInput);      //parse user's 1st num to an int
         subtractionActivator++;                        //keep track that subtractButton was pressed
         userNumberInput = "";                          //clear user number input for num2
      }
   }
   
   public class MultiplyButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         answerTextBox.setText("*");                    //set the answerTextBox to show "*"
         num1 = Integer.parseInt(userNumberInput);      //parse user's 1st num to an int
         multiplicationActivator++;                     //keep track that multiplyButton was pressed
         userNumberInput = "";                          //clear user number input for num2                
      }
   }
   
   public class DivideButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         answerTextBox.setText("/");                    //set the answerTextBox to show "/"
         num1 = Integer.parseInt(userNumberInput);      //parse user's 1st num to an int
         divisionActivator++;                           //keep track that divideButton was pressed
         userNumberInput = "";                          //clear user number input for num2                
      }
   }   
   
}