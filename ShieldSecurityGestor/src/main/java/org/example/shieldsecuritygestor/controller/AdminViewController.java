package org.example.shieldsecuritygestor.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.shieldsecuritygestor.dao.*;

import org.example.shieldsecuritygestor.model.Factura;
import org.example.shieldsecuritygestor.model.Incidencia;
import org.example.shieldsecuritygestor.model.Servicio;
import org.example.shieldsecuritygestor.model.Cliente;



import org.example.shieldsecuritygestor.model.usuario.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private ListView<String> contentSection;

    private Usuario usuario;
    private List<Cliente> listaClientes = new ArrayList<>();

    private ContratoDAO contratoDAO = new ContratoDAO();
    private FacturaDAO facturaDAO = new FacturaDAO();
    private IncidenciaDAO incidenciaDAO = new
            IncidenciaDAO();
    private ServicioDAO servicioDAO = new ServicioDAO();
    private UsuarioDAO usuarioCliente = new UsuarioDAO();

    @Override
    public void initialize(URL url, ResourceBundle
            resourceBundle) {
        actions();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        UserName.setText(usuario.getNombre());
    }

    public void actions(){

        btnDeleteUser.setDisable(true);

        contentSection.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteUser.setDisable(newValue.intValue() < 0 || listaClientes.isEmpty());
        });

        btnShowClientes.setOnAction(actionEvent -> {
            listaClientes = usuarioCliente.obtenerTodosLosClientes();
            contentSection.getItems().clear();
            for (Cliente cliente : listaClientes) {
                contentSection.getItems().add(
                        "Id: " + cliente.getId_cliente() + " | " + cliente.getNombre() + " " + cliente.getApellido() + " | Email: " + cliente.getCorreo() + " | Empresa: " + cliente.getEmpresa() + " | CIF: " + cliente.getCif() + " | Activo: " + cliente.getActivo()
                );
            }
        });

        btnDeleteUser.setOnAction(actionEvent -> {
            int indice =
                    contentSection.getSelectionModel().getSelectedIndex();
            Cliente clienteSeleccionado = listaClientes.get(indice);
            usuarioCliente.eliminarCliente(clienteSeleccionado.getId_cliente());
            listaClientes.remove(indice);
            contentSection.getItems().remove(indice);
            btnDeleteUser.setDisable(true);
        });

        btnShowServicios.setOnAction(actionEvent -> {
            listaClientes.clear();
            List<Servicio> servicios = servicioDAO.obtenerTodosLosServicios();
            contentSection.getItems().clear();
            for (Servicio servicio : servicios) {
                contentSection.getItems().add(
                        "Id: " + servicio.getId() + " | " + servicio.getNombre() + " | Descripcion: " + servicio.getDescripcion() + " | Estado: " + servicio.isActivo()
                );
            }
        });

        btnShowFacturas.setOnAction(actionEvent -> {
            listaClientes.clear();
            List<Factura> facturas = facturaDAO.obtenerTodasFacturas();
            contentSection.getItems().clear();
            for (Factura factura : facturas) {
                contentSection.getItems().add(
                        "Factura #" + factura.getNumeroFactura() + " | Emitida: " + factura.getFechaEmision() + " | Vence: " + factura.getFechaVencimiento() + " | Importe: " + factura.getImporte() + "€" + " | Estado: " + factura.getEstadoPago()
                );
            }
        });

        btnShowIncidencias.setOnAction(actionEvent -> {
            listaClientes.clear();
            List<Incidencia> incidencias = incidenciaDAO.obtenerTodasIncidencias();
            contentSection.getItems().clear();
            for (Incidencia incidencia : incidencias) {
                contentSection.getItems().add("Incidencia #" + incidencia.getIdIncidencia() + " | " + incidencia.getTitulo() + " | Severidad: " + incidencia.getSeveridad() + " | Estado: " + incidencia.getEstado()
                );
            }
        });

        btnLogout.setOnAction(actionEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/shieldsecuritygestor/login-view.fxml"));
                Stage loginStage = new Stage();
                loginStage.setTitle("ShieldSecurity | Panel Administrativo");
                loginStage.setScene(new Scene(loader.load()));
                loginStage.show();
                ((Stage)
                        btnLogout.getScene().getWindow()).close();
            } catch (IOException exception) {
                System.out.println("Error al cerrar sesión: " + exception.getMessage());
            }
        });

        btnAddUser.setOnAction(actionEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/shieldsecuritygestor/addUser-form-view.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Añadir Cliente");
                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (IOException exception) {
                System.out.println("Error al abrir formulario: " + exception.getMessage());
            }
        });
    }
}

