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
   FlowPane root = new FlowPane();
   ContentHolder contentHolder = new ContentHolder();  //contentHolder.create(); 
     
   public void start(Stage stage)
   {
      //set root properties 
      root.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY,Insets.EMPTY)));
      root.setAlignment(Pos.CENTER);
  
      root.getChildren().add(contentHolder); 
      
      Scene scene = new Scene(root,350,400);
      stage.setScene(scene);
      stage.setTitle("Lab 6");
      stage.show();      
   }   
      
   public static void main(String[]args)
   {
      launch(args);
   }   
      
}//main bracket