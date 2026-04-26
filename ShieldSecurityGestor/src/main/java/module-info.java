module org.example.shieldsecuritygestor {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.shieldsecuritygestor to javafx.fxml;
    exports org.example.shieldsecuritygestor;
}