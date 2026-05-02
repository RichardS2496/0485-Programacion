package org.example.shieldsecuritygestor.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientViewController implements Initializable {


    @FXML
    private Text UserName;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnShowFacturas;

    @FXML
    private Button btnShowIncidencias;

    @FXML
    private Button btnShowPlan;

    @FXML
    private ListView<?> contentSection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
