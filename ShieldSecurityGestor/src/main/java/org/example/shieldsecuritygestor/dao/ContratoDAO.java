package org.example.shieldsecuritygestor.dao;

import org.example.shieldsecuritygestor.database.DBConnection;
import org.example.shieldsecuritygestor.model.Contrato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO {

    private Connection connection;

    public ContratoDAO(){
        connection = DBConnection.getConnection();
    }

    public List<Contrato> obtenerContratosPorUsuario(int idUsuario){
        List<Contrato> contratos = new ArrayList<>();

        String query = "SELECT c.id_contrato, p.nombre_plan, c.fecha_inicio, c.fecha_fin, c.importe_total, c.estado FROM contrato c JOIN plan p ON c.id_plan = p.id_plan WHERE c.id_usuario = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Contrato contrato = new Contrato();
                contrato.setIdContrato(rs.getInt("id_contrato"));
                contrato.setNombrePlan(rs.getString("nombre_plan"));

                contrato.setFechaInicio(rs.getString("fecha_inicio"));
                contrato.setFechaFin(rs.getString("fecha_fin"));

                contrato.setImporteTotal(rs.getDouble("importe_total"));
                contrato.setEstado(rs.getString("estado"));
                contratos.add(contrato);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener contratos: "
                    + e.getMessage());
        }

        return contratos;
    }

}



