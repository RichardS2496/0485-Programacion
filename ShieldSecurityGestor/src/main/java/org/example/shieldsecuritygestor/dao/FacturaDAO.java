package org.example.shieldsecuritygestor.dao;

import org.example.shieldsecuritygestor.database.DBConnection;
import org.example.shieldsecuritygestor.model.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    private Connection connection;

    public FacturaDAO() {
        connection = DBConnection.getConnection();
    }

    public List<Factura> obtenerFacturasPorUsuario(int idUsuario) {
        List<Factura> facturas = new ArrayList<>();

        String query = "SELECT f.numero_factura, f.fecha_emision, f.fecha_vencimiento, f.importe, f.estado_pago FROM factura f JOIN contrato c ON f.id_contrato = c.id_contrato WHERE c.id_usuario = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Factura factura = new Factura();
                factura.setNumeroFactura(rs.getString("numero_factura"));
                factura.setFechaEmision(rs.getString("fecha_emision"));
                factura.setFechaVencimiento(rs.getString("fecha_vencimiento"));
                factura.setImporte(rs.getDouble("importe"));
                factura.setEstadoPago(rs.getString("estado_pago"));
                facturas.add(factura);
            }


        } catch (SQLException e) {
            System.out.println("Error al obtener contratos: "
                    + e.getMessage());
        }

        return facturas;
    }

    public List<Factura> obtenerTodasFacturas() {
        List<Factura> facturas = new ArrayList<>();

        String query = "SELECT c.id_usuario, u.nombre_usuario, f.numero_factura, f.fecha_emision, f.fecha_vencimiento, f.importe, f.estado_pago FROM factura f JOIN contrato c ON f.id_contrato = c.id_contrato JOIN usuario u ON c.id_usuario = u.id_usuario";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Factura factura = new Factura();
                factura.setNumeroFactura(rs.getString("numero_factura"));
                factura.setFechaEmision(rs.getString("fecha_emision"));
                factura.setFechaVencimiento(rs.getString("fecha_vencimiento"));
                factura.setImporte(rs.getDouble("importe"));
                factura.setEstadoPago(rs.getString("estado_pago"));
                facturas.add(factura);
            }


        } catch (SQLException e) {
            System.out.println("Error al obtener contratos: "
                    + e.getMessage());
        }

        return facturas;
    }
}
