module org.example.shieldsecuritygestor {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.google.gson;
    requires org.json;
    requires java.sql;


    opens org.example.shieldsecuritygestor to javafx.fxml;
    opens org.example.shieldsecuritygestor.controller to javafx.fxml;
    exports org.example.shieldsecuritygestor;

}