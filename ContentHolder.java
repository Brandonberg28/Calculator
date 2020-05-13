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

   private TextField answerTextBox = new TextField();
   private HBox buttonHolder = new HBox();
   
   private FlowPane numButtonHolder = new FlowPane();
   private VBox arithmeticButtonHolder = new VBox();
   
   private Button [] numButtonArr = new Button [12];  //array that holds number buttons
   
   private ArithmeticOperator arithmeticOperatorHolder;
   private Button add = new Button("+");
   private Button subtract = new Button("-");
   private Button multiply = new Button("*");
   private Button clear = new Button("Clear");
   private Button equal = new Button("=");
   private Button divide = new Button("/"); 
   
   private int num1 = 0;
   private int num2 = 0;
   private boolean arithmeticButtonPressed = false;

    
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
         btn.setOnAction(new NumberButtonListener());
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
      numButtonArr[0].setOnAction(new NumberButtonListener());
      numButtonArr[10].setOnAction(new ClearButtonListener());
      numButtonArr[11].setOnAction(new EqualsButtonListener());
      
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
      //KeyEvent(EventType<KeyEvent> eventType, String character, String text, KeyCode code, boolean shiftDown, boolean controlDown, boolean altDown, boolean metaDown)
      //answerTextBox.onKeyTyped(new KeyEvent(KeyEvent.KEY_TYPED,number,number,KeyCode.NUMPAD1,false,false,false,false));
      answerTextBox.setText(answerTextBox.getText() + number);
      //if flag is set then just set the text(overwrite it) **************
   }
   
   public class NumberButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         Button btn  = (Button)e.getSource();
         addNumber(btn.getText());
      }
   }
   
   public class ClearButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {       
         answerTextBox.setText("");
      }
   }
   
   private void performEquals()
   {
      num2 = Integer.parseInt(answerTextBox.getText());    //parse user's 2nd num to an int
      answerTextBox.setText(Integer.toString(arithmeticOperatorHolder.execute(num1,num2)));  //display sum in textBox as a String
      arithmeticOperatorHolder = null;
   }
   
   public class EqualsButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {          
         performEquals();
      }
   }
   
   private void performOperation(ArithmeticOperator ao)
   {
      if(arithmeticOperatorHolder != null)
         {
            performEquals();
         } 
         num1 = Integer.parseInt(answerTextBox.getText());      //parse user's 1st num to an int
         arithmeticOperatorHolder = ao;
         answerTextBox.requestFocus();
         answerTextBox.selectAll();
   }
      
   public class AddButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {    
         performOperation(new AdditionArithmeticOperator());
      }
   }
   
   public class SubtractButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         performOperation(new SubtractionArithmeticOperator());      
      }
   }
   
   public class MultiplyButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         performOperation(new MultiplicationArithmeticOperator());                
      }
   }
   
   public class DivideButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         performOperation(new DivisionArithmeticOperator());                
      }
   }   
   
}