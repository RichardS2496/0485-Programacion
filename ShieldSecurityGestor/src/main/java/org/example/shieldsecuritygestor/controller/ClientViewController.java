package org.example.shieldsecuritygestor.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.shieldsecuritygestor.dao.ContratoDAO;
import org.example.shieldsecuritygestor.dao.FacturaDAO;
import org.example.shieldsecuritygestor.dao.IncidenciaDAO;


import org.example.shieldsecuritygestor.model.Contrato;
import org.example.shieldsecuritygestor.model.Factura;
import org.example.shieldsecuritygestor.model.Incidencia;
import org.example.shieldsecuritygestor.model.usuario.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    private ListView<String> contentSection;

    private Usuario usuario;
    private ContratoDAO contratoDAO = new ContratoDAO();
    private FacturaDAO facturaDAO = new FacturaDAO();
    private IncidenciaDAO incidenciaDAO = new IncidenciaDAO();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    actions();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        UserName.setText((usuario.getNombre()));
    }

    public void actions(){
        btnShowPlan.setOnAction(e -> {
            List<Contrato> contratos = contratoDAO.obtenerContratosPorUsuario(usuario.getId());
            contentSection.getItems().clear();
            for (Contrato contrato : contratos) {
                contentSection.getItems().add(
                        "Contrato #" + contrato.getIdContrato() +
                                " | Plan: " + contrato.getNombrePlan() +
                                " | Desde: " + contrato.getFechaInicio() +
                                " | Hasta: " + contrato.getFechaFin() +
                                " | Importe: " + contrato.getImporteTotal() + "€" +
                                " | Estado: " + contrato.getEstado()
                );

            }
        });
        btnShowFacturas.setOnAction(e -> {
            List<Factura> facturas = facturaDAO.obtenerFacturasPorUsuario(usuario.getId());
            contentSection.getItems().clear();
            for (Factura factura : facturas) {
                contentSection.getItems().add(
                        "Factura #" + factura.getNumeroFactura() +
                                " | Emitida: " + factura.getFechaEmision() +
                                " | Vence: " + factura.getFechaVencimiento() +
                                " | Importe: " + factura.getImporte() + "€" +
                                " | Estado: " + factura.getEstadoPago()
                );
            }
        });
        btnShowIncidencias.setOnAction(e -> {
            List<Incidencia> incidencias = incidenciaDAO.obtenerIncidenciasPorUsuario(usuario.getId());
            contentSection.getItems().clear();
            for (Incidencia i : incidencias) {
                contentSection.getItems().add(
                        "Incidencia #" + i.getIdIncidencia() + " | " + i.getTitulo() + " | Severidad: " + i.getSeveridad() + " | Estado: " + i.getEstado()
                );
            }
        });
        btnLogout.setOnAction(e ->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/shieldsecuritygestor/login-view.fxml"));
                Stage loginStage = new Stage();
                loginStage.setTitle("ShieldSecurity | Panel Administrativo");
                loginStage.setScene(new Scene(loader.load()));
                loginStage.show();
                ((Stage) btnLogout.getScene().getWindow()).close();
            } catch (IOException e2) {
                System.out.println("Error al cerrar sesión: " +
                        e2.getMessage());
            }

        });
    }



}
