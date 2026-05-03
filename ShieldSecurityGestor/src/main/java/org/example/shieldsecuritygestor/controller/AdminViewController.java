package org.example.shieldsecuritygestor.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.example.shieldsecuritygestor.dao.ContratoDAO;
import org.example.shieldsecuritygestor.dao.FacturaDAO;
import org.example.shieldsecuritygestor.dao.IncidenciaDAO;
import org.example.shieldsecuritygestor.dao.ServicioDAO;

import org.example.shieldsecuritygestor.model.Contrato;
import org.example.shieldsecuritygestor.model.Factura;
import org.example.shieldsecuritygestor.model.Incidencia;
import org.example.shieldsecuritygestor.model.Servicio;

import org.example.shieldsecuritygestor.model.usuario.Usuario;

import java.net.URL;
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

    private ContratoDAO contratoDAO = new ContratoDAO();
    private FacturaDAO facturaDAO = new FacturaDAO();
    private IncidenciaDAO incidenciaDAO = new IncidenciaDAO();
    private ServicioDAO servicioDAO = new ServicioDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actions();
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        UserName.setText((usuario.getNombre()));
    }

    public void actions(){
        btnShowServicios.setOnAction(e -> {
            List<Servicio> servicios = servicioDAO.obtenerTodosLosServicios();
            contentSection.getItems().clear();
            for (Servicio i : servicios) {
                contentSection.getItems().add(
                        "Id del Servicio: " + i.getId() + " | " + i.getNombre() + " | Descripcion: " + i.getDescripcion() + " | Estado: " + i.isActivo()
                );
            }
        });
    }

}
