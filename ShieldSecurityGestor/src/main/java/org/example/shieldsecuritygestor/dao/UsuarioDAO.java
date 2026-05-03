package org.example.shieldsecuritygestor.dao;

import org.example.shieldsecuritygestor.database.DBConnection;
import org.example.shieldsecuritygestor.database.SchemaDB;
import org.example.shieldsecuritygestor.model.Cliente;
import org.example.shieldsecuritygestor.model.Servicio;
import org.example.shieldsecuritygestor.model.usuario.ClienteUsuario;
import org.mariadb.jdbc.export.SslMode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection connection;

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public UsuarioDAO(){
        connection = DBConnection.getConnection();
    }


    public int crearClientePS(ClienteUsuario cliente) throws SQLException{
        // INSERT INTO usuario (nombre, apellido,correo, password, cif, empresa VALUES ()

        String query = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s,%s) VALUES (?,?,?,?,?,?,?,3,1)",
                SchemaDB.TABLE_USUARIOS,
                SchemaDB.COL_NOMBRE_USUARIO,
                SchemaDB.COL_APELLIDO_USUARIO,
                SchemaDB.COL_EMAIL,
                SchemaDB.COL_PASSWORD,
                SchemaDB.COL_PHONE,
                SchemaDB.COL_CIF,
                SchemaDB.COL_NOMBRE_EMPRESA,
                SchemaDB.COL_ID_ROL,
                SchemaDB.COL_ACTIVO
        );

            preparedStatement =  connection.prepareStatement(query);
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getCorreo());
            preparedStatement.setString(4, cliente.getPassword());
            preparedStatement.setString(5, cliente.getTelefono());
            preparedStatement.setString(6, cliente.getCif());
            preparedStatement.setString(7, cliente.getEmpresa());

            return preparedStatement.executeUpdate();




    }

    public void eliminarCliente(int idCliente) {
        String query = "DELETE FROM usuario WHERE id_usuario =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idCliente);
            ps.executeUpdate();
            System.out.println("Cliente eliminado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }


    public List<Cliente> obtenerTodosLosClientes() {                   List<Cliente> clientes = new ArrayList<>();

        String query = "SELECT id_usuario, nombre_usuario, apellido_usuario, email, cif, nombre_empresa, activo FROM usuario WHERE id_rol = 3";
        try {
            PreparedStatement ps =
                    connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_usuario"));
                cliente.setNombre(rs.getString("nombre_usuario"));
                cliente.setApellido(rs.getString("apellido_usuario"));
                cliente.setCorreo(rs.getString("email"));
                cliente.setCif(rs.getString("cif"));
                cliente.setEmpresa(rs.getString("nombre_empresa"));
                cliente.setActivo(rs.getBoolean("activo"));
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " +
                    e.getMessage());
        }

        return clientes;
    }



}
