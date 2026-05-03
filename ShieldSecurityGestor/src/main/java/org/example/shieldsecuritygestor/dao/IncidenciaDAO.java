package org.example.shieldsecuritygestor.dao;

import org.example.shieldsecuritygestor.database.DBConnection;
import org.example.shieldsecuritygestor.model.Incidencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncidenciaDAO {

    private Connection connection;

    public IncidenciaDAO(){
        connection = DBConnection.getConnection();
    }

    public List<Incidencia> obtenerIncidenciasPorUsuario(int idUsuario) {
        List<Incidencia> incidencias = new ArrayList<>();

        String query = "SELECT i.id_incidencia, i.titulo, i.descripcion, i.severidad, i.estado, i.fecha_apertura, i.fecha_cierre FROM incidencia i JOIN contrato c ON i.id_contrato = c.id_contrato WHERE c.id_usuario = ?\n";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Incidencia incidencia = new Incidencia();
                incidencia.setIdIncidencia(rs.getInt("id_incidencia"));
                incidencia.setTitulo(rs.getString("titulo"));
                incidencia.setDescripcion(rs.getString("descripcion"));
                incidencia.setSeveridad(rs.getString("severidad"));
                incidencia.setFechaApertura(rs.getString("fecha_apertura"));
                incidencia.setFechaCierre(rs.getString("fecha_cierre"));
                incidencia.setEstado(rs.getString("estado"));

                incidencias.add(incidencia);
            }


        } catch (SQLException e) {
            System.out.println("Error al obtener contratos: "
                    + e.getMessage());
        }

        return incidencias;
    }
}
