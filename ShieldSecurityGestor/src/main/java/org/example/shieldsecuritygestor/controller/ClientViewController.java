package org.example.shieldsecuritygestor.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.example.shieldsecuritygestor.dao.ContratoDAO;
import org.example.shieldsecuritygestor.model.Contrato;
import org.example.shieldsecuritygestor.model.usuario.Usuario;

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
            List<Contrato> contratos =  contratoDAO.obtenerContratosPorUsuario(usuario.getId());
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

    }



}
