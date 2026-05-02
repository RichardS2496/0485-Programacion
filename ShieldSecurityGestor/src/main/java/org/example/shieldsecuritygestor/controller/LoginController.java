package org.example.shieldsecuritygestor.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.shieldsecuritygestor.dao.LoginDAO;
import org.example.shieldsecuritygestor.model.usuario.Usuario;
import org.example.shieldsecuritygestor.model.usuario.ClienteUsuario;
import org.example.shieldsecuritygestor.model.usuario.Administrador;
import org.example.shieldsecuritygestor.model.usuario.Master;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class LoginController implements Initializable {

    private LoginDAO loginDAO = new LoginDAO();

    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        cargarUsuariosTest();
        actions();
    }




public void actions(){
        btnLogin.setOnAction(actionEvent -> {
            String emailUser = emailField.getText().trim();
            String passwordUser = passwordField.getText().trim();

            Usuario encontrado = LoginDAO.login(emailUser, passwordUser);

            if(encontrado != null){
                switch (encontrado.getPerfil()){
                    case "MASTER" -> System.out.println("Master");
                    case "ADMINISTRADOR" -> System.out.println("Administrador");
                    case "CLIENTE" -> System.out.println("Cliente");
                }
            } else {
                System.out.println("Datos incorrectos");
            }


        });
    }


//    private Usuario buscarUsuario(String correo, String password){
//        for (Usuario usuario  : usuarios){
//            if(usuario.getCorreo().equals(correo) && usuario.getPassword().equals(password)){
//                return usuario;
//            }
//        }
//        return null;
//    }
}
