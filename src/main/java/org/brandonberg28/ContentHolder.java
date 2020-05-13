package org.brandonberg28;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ContentHolder extends VBox
{

   private final TextField answerTextBox = new TextField();
   private final HBox buttonHolder = new HBox();

   private final FlowPane numButtonHolder = new FlowPane();
   private final VBox arithmeticButtonHolder = new VBox();

   private final Button[] numButtonArr = new Button[12]; // array that holds number buttons

   private ArithmeticOperator arithmeticOperatorHolder;
   private final Button add = new Button("+");
   private final Button subtract = new Button("-");
   private final Button multiply = new Button("*");
   private final Button divide = new Button("/");

   private int num1 = 0;
   private int num2 = 0;
   
   public ContentHolder() {
      setupAnswerTextBox();

      setupButtonHolder();

      getChildren().add(answerTextBox);
      getChildren().add(buttonHolder);
   }

   private void setupAnswerTextBox() {
      // set text properties
      answerTextBox.setAlignment(Pos.CENTER);
      answerTextBox.setPrefSize(240, 70);
   }

   private void setupButtonHolder() {
      // set buttonHolder properties
      buttonHolder.setPrefSize(240, 240);
      buttonHolder.setAlignment(Pos.CENTER);

      numButtonHolder.setPrefSize(180, 240);

      for (int i = 1; i < 10; i++) // create number buttons 1-9 and store in array
      {
         final var btn = new Button(Integer.toString(i));
         btn.setPrefSize(60, 60);
         btn.setOnAction(new NumberButtonListener());
         numButtonArr[i] = btn;
         numButtonHolder.getChildren().add(btn);
      }

      // create and add 0, clear & = buttons to numButtonHolder array
      numButtonArr[0] = new Button("0");
      numButtonArr[10] = new Button("clear");
      numButtonArr[11] = new Button("=");
      numButtonArr[0].setPrefSize(60, 60);
      numButtonArr[10].setPrefSize(60, 60);
      numButtonArr[11].setPrefSize(60, 60);
      numButtonArr[0].setOnAction(new NumberButtonListener());
      numButtonArr[10].setOnAction(new ClearButtonListener());
      numButtonArr[11].setOnAction(new EqualsButtonListener());

      numButtonHolder.getChildren().add(numButtonArr[0]);
      numButtonHolder.getChildren().add(numButtonArr[10]);
      numButtonHolder.getChildren().add(numButtonArr[11]);

      // set properties of arithmeticButtons
      add.setPrefSize(60, 60);
      subtract.setPrefSize(60, 60);
      multiply.setPrefSize(60, 60);
      divide.setPrefSize(60, 60);

      // make individual button listeners for each artihmeticButton
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

   private void addNumber(final String number) {
      // KeyEvent(EventType<KeyEvent> eventType, String character, String text,
      // KeyCode code, boolean shiftDown, boolean controlDown, boolean altDown,
      // boolean metaDown)
      // answerTextBox.onKeyTyped(new
      // KeyEvent(KeyEvent.KEY_TYPED,number,number,KeyCode.NUMPAD1,false,false,false,false));
      answerTextBox.setText(answerTextBox.getText() + number);
      // if flag is set then just set the text(overwrite it) **************
   }

   public class NumberButtonListener implements EventHandler<ActionEvent> {
      public void handle(final ActionEvent e) {
         final Button btn = (Button) e.getSource();
         addNumber(btn.getText());
      }
   }

   public class ClearButtonListener implements EventHandler<ActionEvent> {
      public void handle(final ActionEvent e) {
         answerTextBox.setText("");
      }
   }

   private void performEquals() {
      num2 = Integer.parseInt(answerTextBox.getText()); // parse user's 2nd num to an int
      answerTextBox.setText(Integer.toString(arithmeticOperatorHolder.execute(num1, num2))); // display sum in textBox
                                                                                             // as a String
      arithmeticOperatorHolder = null;
   }

   public class EqualsButtonListener implements EventHandler<ActionEvent> {
      public void handle(final ActionEvent e) {
         performEquals();
      }
   }

   private void performOperation(final ArithmeticOperator ao) {
      if (arithmeticOperatorHolder != null) {
         performEquals();
      }
      num1 = Integer.parseInt(answerTextBox.getText()); // parse user's 1st num to an int
      arithmeticOperatorHolder = ao;
      answerTextBox.requestFocus();
      answerTextBox.selectAll();
   }

   public class AddButtonListener implements EventHandler<ActionEvent> {
      public void handle(final ActionEvent e) {
         performOperation(new AdditionArithmeticOperator());
      }
   }

   public class SubtractButtonListener implements EventHandler<ActionEvent> {
      public void handle(final ActionEvent e) {
         performOperation(new SubtractionArithmeticOperator());
      }
   }

   public class MultiplyButtonListener implements EventHandler<ActionEvent> {
      public void handle(final ActionEvent e) {
         performOperation(new MultiplicationArithmeticOperator());
      }
   }

   public class DivideButtonListener implements EventHandler<ActionEvent> {
      public void handle(final ActionEvent e)
      {
         performOperation(new DivisionArithmeticOperator());                
      }
   }   
   
}