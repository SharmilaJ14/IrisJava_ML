module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires weka;


    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
}