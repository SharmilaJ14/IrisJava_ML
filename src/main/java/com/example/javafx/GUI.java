package com.example.javafx;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import weka.core.neighboursearch.LinearNNSearch;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Attribute;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
    Stage window;
    Scene scene;
    ArrayList<Double> allSl;
    ArrayList<Double> allSw;
    ArrayList<Double> allPl;
    ArrayList<Double> allPw;
    @Override
    public void start(Stage stage) throws Exception {
        window=stage;
        window.setTitle("kNN Algorithm using Weka");

        //Labels
        Label title=new Label("kNN Algorithm application");
        Label description=new Label("Using weka module's LinearNNsearch class, we find k nearest neighbours to the point entered");
        Label l1=new Label("Enter value of k:");
        Label l2=new Label("Enter sepal-length:");
        Label l3=new Label("Enter sepal-width:");
        Label l4=new Label("Enter petal-length:");
        Label l5=new Label("Enter petal-width:");
        //Combo boxes
        ComboBox<Integer> comboBox=new ComboBox<>();
        comboBox.getItems().addAll(3,5,7,9,11,13,15);
        comboBox.setPromptText("k value");

        TextField inputSl=new TextField();
        inputSl.setPromptText("sepal-length(in cm)");

        TextField inputSw=new TextField();
        inputSw.setPromptText("sepal-width(in cm)");

        TextField inputPl=new TextField();
        inputPl.setPromptText("petal-length(in cm)");

        TextField inputPw=new TextField();
        inputPw.setPromptText("petal-width(in cm)");

        Button b=new Button("Submit");
        b.setOnAction(e->formAttribute(comboBox.getValue(),inputSl.getText(),inputSw.getText(),inputPl.getText(),inputPw.getText()));

        HBox h1=new HBox();
        h1.getChildren().addAll(l1,comboBox);

        HBox h2=new HBox();
        h2.getChildren().addAll(l2,inputSl);

        HBox h3=new HBox();
        h3.getChildren().addAll(l3,inputSw);

        HBox h4=new HBox();
        h4.getChildren().addAll(l4,inputPl);

        HBox h5=new HBox();
        h5.getChildren().addAll(l5,inputPw);

        GridPane layout=new GridPane();
        layout.addRow(0,title);
        layout.addRow(1,description);
        layout.addRow(2,h1);
        layout.addRow(3,h2);
        layout.addRow(4,h3);
        layout.addRow(5,h4);
        layout.addRow(6,h5);
        layout.addRow(7,b);

        scene=new Scene(layout,500,300);
        window.setScene(scene);
        window.show();
    }
    public void formAttribute(int k,String sl,String sw,String pl,String pw){
        double Sl=Double.parseDouble(sl);
        double Sw=Double.parseDouble(sw);
        double Pl=Double.parseDouble(pl);
        double Pw=Double.parseDouble(pw);
        try {
            Algorithm(Sl,Sw,Pl,Pw,k);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void Algorithm(double a,double b,double c,double d,int k) throws IOException {
        //Data set used is Iris.data a dataset which consists of different Iris flowers data.
        //It has three flowers : Iris setosa,iris versicolor and iris verginicia
        //It has four different data-fields:
        //1.sepal length in cm
        //2.sepal width in cm
        //3.petal length in cm
        //4.petal width in cm
        ArrayList<String> Class=new ArrayList<>();    //String list to store three classes of flower
        Class.add("Iris-setosa");
        Class.add("Iris-versicolor");
        Class.add("Iris-virginica");

        ArrayList<Attribute> attr=new ArrayList<>();   //List which stores all columns
        attr.add(new Attribute("Sepal-length"));
        attr.add(new Attribute("Sepal-width"));
        attr.add(new Attribute("Petal-length"));
        attr.add(new Attribute("Petal-width"));
        attr.add(new Attribute("@@class@@",Class));

        Instances dataRaw=new Instances("Iris flower data",attr,0); //List to store all instances of data
        BufferedReader bf=new BufferedReader(new FileReader("iris.txt"));
        String temp;

        allSl=new ArrayList<>();
        allSw=new ArrayList<>();
        allPl=new ArrayList<>();
        allPw=new ArrayList<>();

        while((temp=bf.readLine())!=null){
            StringTokenizer t=new StringTokenizer(temp,",");
            double sl=Double.parseDouble(t.nextToken());
            allSl.add(sl);
            double sw=Double.parseDouble(t.nextToken());
            allSw.add(sw);
            double pl=Double.parseDouble(t.nextToken());
            allPl.add(pl);
            double pw=Double.parseDouble(t.nextToken());
            allPw.add(pw);
            String classVar=t.nextToken();
            double[] instanceVar=new double[dataRaw.numAttributes()];
            double type=0;
            if(classVar.equals("Iris-setosa")){
                type=0;
            }
            else if(classVar.equals("Iris-versicolor")){
                type=1;
            }
            else{
                type=2;
            }
            instanceVar[0]=sl;
            instanceVar[1]=sw;
            instanceVar[2]=pl;
            instanceVar[3]=pw;
            instanceVar[4]=type;
            dataRaw.add(new DenseInstance(1.0,instanceVar));
        }
        double[] arr=new double[5];
        arr[0]=a;
        arr[1]=b;
        arr[2]=c;
        arr[3]=d;
        arr[4]=0;
        dataRaw.add(new DenseInstance(1.0,arr));
        LinearNNSearch knn=new LinearNNSearch(dataRaw);
        try {
            Instances kNearInstanceC1=knn.kNearestNeighbours(dataRaw.lastInstance(),k);
            System.out.println(kNearInstanceC1);
            arr[4]=1;
            dataRaw.add(new DenseInstance(1.0,arr));
            Instances kNearInstanceC2=knn.kNearestNeighbours(dataRaw.lastInstance(),k);
            System.out.println(kNearInstanceC2);
            arr[4]=2;
            dataRaw.add(new DenseInstance(1.0,arr));
            Instances kNearInstanceC3=knn.kNearestNeighbours(dataRaw.lastInstance(),k);
            System.out.println(kNearInstanceC3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        printGraph(a,b,c,d);
    }

    public void printGraph(double sl,double sw,double pl,double pw){
        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(0, 10, 1);
        final ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Length");
        yAxis.setLabel("Width");
        sc.setTitle("kNN Algorithm visualisation");
        XYChart.Series <Number,Number>series1 = new XYChart.Series<>();
        series1.setName("Sepal length Vs Sepal width");
        for(int i=0;i<allSl.size();i++){
            series1.getData().add(new XYChart.Data<Number,Number>(allSl.get(i),allSw.get(i)));
        }
        XYChart.Series<Number,Number> series2=new XYChart.Series<>();
        series2.setName("Petal length Vs Petal width");
        for(int i=0;i<allSl.size();i++){
            series2.getData().add(new XYChart.Data<Number,Number>(allPl.get(i),allPw.get(i)));
        }
        XYChart.Series<Number,Number> series3=new XYChart.Series<>();
        series3.setName("Sepal length Vs Sepal width Target");
        series3.getData().add(new XYChart.Data<Number,Number>(sl,sw));

        XYChart.Series <Number,Number>series4=new XYChart.Series<>();
        series4.setName("Petal length Vs Petal width Target");
        series4.getData().add(new XYChart.Data<Number,Number>(pl,pw));
        sc.getData().addAll(series1,series2,series3,series4);

        Scene scene1=new Scene(sc);
        window.setTitle("Graphs!");
        window.setScene(scene1);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
