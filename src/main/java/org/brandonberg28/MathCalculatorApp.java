package org.brandonberg28;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;


/**
 * JavaFX App
 */
public class MathCalculatorApp extends Application {

    FlowPane root = new FlowPane();
   ContentHolder contentHolder = new ContentHolder();   
     
    @Override
    public void start(final Stage stage) {
        
        root.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setAlignment(Pos.CENTER);
        
        final var javaVersion = SystemInfo.javaVersion();
        final var javafxVersion = SystemInfo.javafxVersion();

        final var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        root.getChildren().add(label);

        root.getChildren().add(contentHolder); 
      
        Scene scene = new Scene(root,350,400);
        stage.setScene(scene);
        stage.setTitle("Math Calculator");
        stage.show();      
    }

    public static void main(final String[] args) {
        launch();
    }

}