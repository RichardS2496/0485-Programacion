package org.example.shieldsecuritygestor.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.shieldsecuritygestor.model.usuario.Usuario;
import org.example.shieldsecuritygestor.model.usuario.ClienteUsuario;
import org.example.shieldsecuritygestor.model.usuario.Administrador;
import org.example.shieldsecuritygestor.model.usuario.Master;


import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class LoginController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarUsuariosTest();
        actions();
    }

    private List<Usuario> usuarios = new ArrayList<>();

    private void cargarUsuariosTest(){
        ClienteUsuario cliente = new ClienteUsuario();
          cliente.setNombre("Juan");
          cliente.setApellido("García");
          cliente.setCorreo("juan@shield.com");
          cliente.setPassword("1234");
          cliente.setEdad(30);

        Administrador admin = new Administrador();
          admin.setNombre("Ana");
          admin.setApellido("López");
          admin.setCorreo("ana@shield.com");
          admin.setPassword("admin123");
          admin.setEdad(35);

        Master master = new Master();
          master.setNombre("Carlos");
          master.setApellido("Ruiz");
          master.setCorreo("carlos@shield.com");
          master.setPassword("master123");
          master.setEdad(40);

          usuarios.add(cliente);
          usuarios.add(admin);
          usuarios.add(master);
}



public void actions(){
        btnLogin.setOnAction(actionEvent -> {
            String emailUser = emailField.getText().trim();
            String passwordUser = passwordField.getText().trim();

            Usuario encontrado = buscarUsuario(emailUser, passwordUser);

            if(encontrado != null){
                switch (encontrado.getPerfil()){
                    case "CLIENTE" -> System.out.println("Cliente");
                    case "ADMINISTRADOR" -> System.out.println("Administrador");
                    case "MASTER" -> System.out.println("Master");
                }
            } else {
                System.out.println("Datos incorrectos");
            }


        });
    }


    private Usuario buscarUsuario(String correo, String password){
        for (Usuario usuario  : usuarios){
            if(usuario.getCorreo().equals(correo) && usuario.getPassword().equals(password)){
                return usuario;
            }
        }
        return null;
    }
}
