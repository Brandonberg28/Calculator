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

public class MathCalculator extends Application
{

   //create objects (Buttons, ComboBoxes, Textfields) here
   VBox centerPane = new VBox();
   FlowPane flowPane = new FlowPane();
   GridPane gridPane = new GridPane();
   TextField textBox = new TextField("Calculate something...");
   
   Button one = new Button("1");
   Button two = new Button("2");
   Button three = new Button("3");
   Button four = new Button("4");
   Button five = new Button("5");
   Button six = new Button("6");
   Button seven = new Button("7");
   Button eight = new Button("8");
   Button nine = new Button("9");
   Button zero = new Button("0");
   
   Button add = new Button("+");
   Button subtract = new Button("-");
   Button multiply = new Button("*");
   Button clear = new Button("Clear");
   Button equal = new Button("=");
   Button divide = new Button("/");
   
   String calculatorQueue = ""; //Queue to hold numbers for calculator to do Math on
   int additionActivator = 0;
   int subtractionActivator = 0;
   int multiplicationActivator = 0;
   int divisionActivator = 0;
   
   
   
   public void start(Stage stage)
   {
      BorderPane root = new BorderPane();
      root.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY,Insets.EMPTY)));
      root.setPrefSize(800,600);
   
      //Create label and put inside TOP of BorderPane
      VBox topSpace = new VBox();
      topSpace.setPrefSize(300,80); 
      Label l = new Label("Welcome to the Calculator!");
      l.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY,Insets.EMPTY)));
      l.setAlignment(Pos.CENTER);
      l.setPrefSize(300,80);
      topSpace.getChildren().add(l);  
      
      textBox.setPrefSize(300,50);         //add textbox to top of borderpane
      flowPane.getChildren().add(textBox);
         
      root.setTop(topSpace);
      
      //Create label and put inside LEFT of BorderPane
      HBox leftSpace = new HBox();
      leftSpace.setPrefSize(55,500);      
      Label l2 = new Label();
      l2.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY,Insets.EMPTY)));
      l2.setPrefSize(60,500);
      leftSpace.getChildren().add(l2);     
      root.setLeft(leftSpace);
      
      
      //setPrefSize of buttons
      one.setPrefSize(75,75);
      two.setPrefSize(75,75);
      three.setPrefSize(75,75);
      four.setPrefSize(75,75);
      five.setPrefSize(75,75);
      six.setPrefSize(75,75);
      seven.setPrefSize(75,75);
      eight.setPrefSize(75,75);
      nine.setPrefSize(75,75);
      zero.setPrefSize(75,75);
      
      add.setPrefSize(75,75);
      subtract.setPrefSize(75,75);
      multiply.setPrefSize(75,75);
      clear.setPrefSize(75,75);
      equal.setPrefSize(75,75);
      divide.setPrefSize(75,75);
      
      //put Buttons inside gridPane     
      gridPane.add(one,0,1);
      gridPane.add(two,1,1);
      gridPane.add(three,2,1);
      gridPane.add(add,3,1);
      gridPane.add(four,0,2);
      gridPane.add(five,1,2);
      gridPane.add(six,2,2);
      gridPane.add(subtract,3,2);
      gridPane.add(seven,0,3);
      gridPane.add(eight,1,3);
      gridPane.add(nine,2,3);
      gridPane.add(multiply,3,3);
      gridPane.add(clear,0,4);
      gridPane.add(zero,1,4);
      gridPane.add(equal,2,4);
      gridPane.add(divide,3,4);      
      

      //Create Listener Objects for when buttons are pressed   
      one.setOnAction(new ButtonListener());     
      two.setOnAction(new ButtonListener());
      three.setOnAction(new ButtonListener());
      four.setOnAction(new ButtonListener());
      five.setOnAction(new ButtonListener());
      six.setOnAction(new ButtonListener());
      seven.setOnAction(new ButtonListener());
      eight.setOnAction(new ButtonListener());
      nine.setOnAction(new ButtonListener());
      zero.setOnAction(new ButtonListener());
      
      add.setOnAction(new ButtonArithmeticListener());
      subtract.setOnAction(new ButtonArithmeticListener());
      multiply.setOnAction(new ButtonArithmeticListener());
      clear.setOnAction(new ButtonArithmeticListener());
      equal.setOnAction(new ButtonArithmeticListener());
      divide.setOnAction(new ButtonArithmeticListener());        
     
              
      //add buttons, textfields and combobox to Pane
      root.setCenter(centerPane);
      centerPane.getChildren().add(flowPane);
      centerPane.getChildren().add(gridPane);
      
      
      Scene scene = new Scene(root,410,500);
      stage.setScene(scene);
      stage.setTitle("Lab 6");
      stage.show();
      
   }
   
      
   public static void main(String[]args)
   {
      launch(args);
   }
      
      
   public class ButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         
         if(e.getSource() == one) //button 1
         {
            addNumber("1");
         }
         if(e.getSource() == two) //button 2
         {
            addNumber("2");
         }
         if(e.getSource() == three) //button 3
         {
            addNumber("3");
         }
         if(e.getSource() == four) //button 4
         {
            addNumber("4");
         }
         if(e.getSource() == five) //button 5
         {
            addNumber("5");
         } 
         if(e.getSource() == six) //button 6
         {
            addNumber("6");
         }
         if(e.getSource() == seven) //button 7
         {
            addNumber("7");
         }
         if(e.getSource() == eight) //button 8
         {
            addNumber("8");
         }
         if(e.getSource() == nine) //button 9
         {
            addNumber("9");
         }
         if(e.getSource() == zero) //button 0
         {
            addNumber("0");
         }
         
      }
   }
   
   public class ButtonArithmeticListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
      
         if(e.getSource() == add) //executes if + button is pressed
         {
            calculatorQueue += "+";
            additionActivator++;
         }  
         if(e.getSource() == subtract) //executes if - button is pressed
         {
            calculatorQueue += "-";
            subtractionActivator++;
         }    
         if(e.getSource() == multiply) //executes if * button is pressed
         {
            calculatorQueue += "*";
            multiplicationActivator++;
         } 
         if(e.getSource() == divide) //executes if / button is pressed
         {
            calculatorQueue += "/";
            divisionActivator++;
         } 
         if(e.getSource() == equal) //executes if = button is pressed
         {            
            if(additionActivator > 0)
            {
               textBox.setText(performArithmetic("+")+"");
               clearArithmeticActivators(); //clears arithmetic activators so calculator knows which one is pressed the second time around(cause of > 0)
            }
            else if(subtractionActivator > 0)
            {
               textBox.setText(performArithmetic("-")+"");
               clearArithmeticActivators(); //clears arithmetic activators so calculator knows which one is pressed the second time around(cause of > 0)
            }
            else if(multiplicationActivator > 0)
            {
               textBox.setText(performArithmetic("*")+"");
               clearArithmeticActivators(); //clears arithmetic activators so calculator knows which one is pressed the second time around(cause of > 0)
            }
            else if(divisionActivator > 0)
            {
               textBox.setText(performArithmetic("/")+"");
               clearArithmeticActivators(); //clears arithmetic activators so calculator knows which one is pressed the second time around(cause of > 0)
            } 
         } 
         if(e.getSource() == clear) //executes if 'clear' button is pressed
         {
            textBox.setText("");
            calculatorQueue = "";
            additionActivator = 0;
            subtractionActivator = 0;
            multiplicationActivator = 0;
            divisionActivator = 0;
         } 
         
      }
   }
   
   
   public int performArithmetic(String sign) //function that executes arithmetic and returns the answer
   {
      String stringNum1 = calculatorQueue.substring(0,calculatorQueue.indexOf(sign));
      String stringNum2 = calculatorQueue.substring(calculatorQueue.indexOf(sign)+1,calculatorQueue.length());                      
      int num1 = Integer.parseInt(stringNum1);
      int num2 = Integer.parseInt(stringNum2); 
      if(additionActivator > 0)
      {
         calculatorQueue = (num1 + num2)+"";         
         return num1 + num2;
      }
      else if(subtractionActivator > 0)
      {  
         calculatorQueue = (num1 - num2)+"";
         return num1 - num2;
      }
      else if(multiplicationActivator > 0)
      {
         calculatorQueue = (num1 * num2)+"";
         return num1 * num2;
      }
      else 
      {
         calculatorQueue = (num1 / num2)+"";
         return num1 / num2;
      }               
   }
   
   
   //create add Number function
   public void addNumber(String number)
   {
      calculatorQueue += number;
      textBox.setText(calculatorQueue);
   }
   
   
   //create function that clears arithmetic activators 
   public void clearArithmeticActivators()
   {
      additionActivator = 0;
      subtractionActivator = 0;
      multiplicationActivator = 0;
      divisionActivator = 0;
   }
   
   
      
}//main bracket