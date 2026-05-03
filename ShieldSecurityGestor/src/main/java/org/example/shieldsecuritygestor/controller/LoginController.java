package org.example.shieldsecuritygestor.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.shieldsecuritygestor.dao.LoginDAO;
import org.example.shieldsecuritygestor.model.usuario.Usuario;
import org.example.shieldsecuritygestor.model.usuario.ClienteUsuario;
import org.example.shieldsecuritygestor.model.usuario.Administrador;
import org.example.shieldsecuritygestor.model.usuario.Master;


import java.io.IOException;
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

            Usuario encontrado = loginDAO.login(emailUser, passwordUser);

            if(encontrado != null){

                Stage currentView = new Stage();

                try{

                    FXMLLoader fxmlLoader;
                    switch (encontrado.getPerfil()) {
                        case "MASTER" -> {
                            fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/shieldsecuritygestor/master-view.fxml"));
                            currentView.setTitle("ShieldSecurity | Panel Master");
                            currentView.setScene(new Scene(fxmlLoader.load()));
                            MasterViewController controller = fxmlLoader.getController();
                            controller.setUsuario(encontrado);
                            currentView.show();
                            ((Stage) btnLogin.getScene().getWindow()).close();
                        }
                        case "ADMINISTRADOR" -> {
                            fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/shieldsecuritygestor/admin-view.fxml"));
                            currentView.setTitle("ShieldSecurity | Panel Administrador");
                            currentView.setScene(new Scene(fxmlLoader.load()));
                            AdminViewController controller = fxmlLoader.getController();
                            controller.setUsuario(encontrado);
                            currentView.show();
                            ((Stage) btnLogin.getScene().getWindow()).close();
                        }
                        case "CLIENTE" -> {
                            fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/shieldsecuritygestor/client-view.fxml"));
                            currentView.setTitle("ShieldSecurity | Panel del Cliente");
                            currentView.setScene(new Scene(fxmlLoader.load()));
                            ClientViewController controller = fxmlLoader.getController();
                            controller.setUsuario(encontrado);
                            currentView.show();
                            ((Stage) btnLogin.getScene().getWindow()).close();
                        }
                    }


                } catch (IOException e){
                    System.out.println("Error en la ruta");
                    System.out.println(e.getMessage());

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
