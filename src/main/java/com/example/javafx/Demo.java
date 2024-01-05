package com.example.javafx;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class Demo extends Application {
    Button b;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Test!");
        b=new Button("Click me!");
        b.setOnAction(e->System.out.println("Lambda Expression!"));
        StackPane layout=new StackPane();
        layout.getChildren().add(b);

        Scene scene=new Scene(layout,300,250);
        stage.setScene(scene);
        stage.show();
    }
}
