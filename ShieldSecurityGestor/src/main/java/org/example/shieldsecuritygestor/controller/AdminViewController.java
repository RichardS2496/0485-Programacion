package org.example.shieldsecuritygestor.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.example.shieldsecuritygestor.model.usuario.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    @FXML
    private Text UserName;

    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnShowClientes;

    @FXML
    private Button btnShowFacturas;

    @FXML
    private Button btnShowIncidencias;

    @FXML
    private Button btnShowServicios;

    @FXML
    private ListView<?> contentSection;

    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        UserName.setText((usuario.getNombre()));
    }

}
