package com.example.javafx;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import weka.core.neighboursearch.LinearNNSearch;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Attribute;


public class kNN {
    public static void Algorithm(double a,double b,double c,double d,int k) throws IOException {
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

        ArrayList<Double> allSl=new ArrayList<>();
        ArrayList<Double> allSw=new ArrayList<>();
        ArrayList<Double> allPl=new ArrayList<>();
        ArrayList<Double> allPw=new ArrayList<>();

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
        System.out.println(dataRaw);
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
    }
    public static void printGraphs(){

    }

    public static void main(String[] args) {

    }
}
