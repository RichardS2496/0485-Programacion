package org.example.shieldsecuritygestor.dao;

import org.example.shieldsecuritygestor.database.DBConnection;
import org.example.shieldsecuritygestor.model.Servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {

    private Connection connection;

    public ServicioDAO(){
        connection = DBConnection.getConnection();
    }

    public List<Servicio> obtenerTodosLosServicios() {
        List<Servicio> servicios = new ArrayList<>();

        String query = "SELECT id_servicio, nombre, descripcion, activo FROM servicio";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setId(rs.getInt("id_servicio"));
                servicio.setNombre(rs.getString("nombre"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setActivo(rs.getBoolean("activo"));

                servicios.add(servicio);
            }


        } catch (SQLException e) {
            System.out.println("Error al obtener servicios: "
                    + e.getMessage());
        }

        return servicios;
    }
}
