package com.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class STages extends Application {
    Stage stage1;
    Scene scene1,scene2;
    @Override
    public void start(Stage stage) throws Exception {
        stage1=  stage;
        Label label1=new Label("Label!");
        Button b1=new Button("Go to Scene 2");
        b1.setOnAction(e->stage1.setScene(scene2));
        VBox layout1=new VBox(20);
        layout1.getChildren().addAll(label1,b1);
        scene1=new Scene(layout1,200,200);
        Button b2=new Button("Back to scene1");
        b2.setOnAction(e->stage1.setScene(scene1));
        StackPane layout2=new StackPane();
        layout2.getChildren().add(b2);
        scene2=new Scene(layout2,300,300);
        stage1.setScene(scene1);
        stage1.setTitle("Title");
        stage1.show();
    }
}
