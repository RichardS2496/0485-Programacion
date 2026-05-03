package org.example.shieldsecuritygestor.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.shieldsecuritygestor.dao.UsuarioDAO;
import org.example.shieldsecuritygestor.model.usuario.ClienteUsuario;
import org.example.shieldsecuritygestor.model.usuario.Usuario;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addUserViewController implements Initializable {

    @FXML
    private BorderPane borderGeneral;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnVaciar;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField editApellido;

    @FXML
    private TextField editCif;

    @FXML
    private TextField editEmpresa;

    @FXML
    private TextField editMail;

    @FXML
    private TextField editNombre;

    @FXML
    private TextField editPassword;

    @FXML
    private TextField editphone;

    private Usuario usuario;
    private UsuarioDAO usuarioCliente= new UsuarioDAO();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actions();
    }

    public void actions(){

        btnAgregar.setOnAction(e -> {
            if (!camposValidos()) return;
            ClienteUsuario cliente = new ClienteUsuario();
            cliente.setNombre(editNombre.getText().trim());
            cliente.setApellido(editApellido.getText().trim());
            cliente.setCorreo(editMail.getText().trim());
            cliente.setPassword(editPassword.getText().trim());
            cliente.setTelefono(editphone.getText().trim());
            cliente.setCif(editCif.getText().trim());
            cliente.setEmpresa(editEmpresa.getText().trim());

            try {
                int resultado = usuarioCliente.crearClientePS(cliente);
                if (resultado > 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("¡Usuario añadido!");
                    alert.setContentText("El usuario ha sido añadido con éxito.");
                    alert.show();
                    limpiarCampos();
                    volver();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Error");
                    alert.setContentText("Compruebe los datos");
                    alert.show();
                }
            } catch (SQLException error) {
                System.out.println("Error al crear cliente: " +
                        error.getMessage());
            }
        });


        btnVaciar.setOnAction(e -> limpiarCampos());




        btnVolver.setOnAction(e -> {
            volver();
        });

    }

    private boolean camposValidos() {
        if (editNombre.getText().trim().isEmpty() || editApellido.getText().trim().isEmpty() || editMail.getText().trim().isEmpty() || editPassword.getText().trim().isEmpty() || editCif.getText().trim().isEmpty() || editEmpresa.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Campos incompletos");
            alert.setContentText("Todos los campos son obligatorios.");
            alert.show();
            return false;
        }
        return true;
    }


    private void limpiarCampos() {
        editNombre.clear();
        editApellido.clear();
        editMail.clear();
        editPassword.clear();
        editphone.clear();
        editEmpresa.clear();
        editCif.clear();
    }

    private void volver(){
        ((Stage) btnVolver.getScene().getWindow()).close();
    }
}
