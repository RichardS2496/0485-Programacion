package org.example.shieldsecuritygestor.dao;

import org.example.shieldsecuritygestor.database.DBConnection;
import org.example.shieldsecuritygestor.database.SchemaDB;
import org.example.shieldsecuritygestor.model.usuario.ClienteUsuario;
import org.mariadb.jdbc.export.SslMode;

import java.sql.*;

public class UsuarioDAO {

    private Connection connection;

    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public UsuarioDAO(){
        connection = DBConnection.getConnection();
    }

//    public int crearCliente(ClienteUsuario cliente){
//        // INSERT INTO usuario (nombre, apellido,correo, password, cif, empresa VALUES ()
//
//        String query = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s) VALUES ('%s','%s','%s','%s','%s','%s',3,1)",
//                SchemaDB.TABLE_USUARIOS,
//                SchemaDB.COL_NOMBRE_USUARIO,
//                SchemaDB.COL_APELLIDO_USUARIO,
//                SchemaDB.COL_EMAIL,
//                SchemaDB.COL_PASSWORD,
//                SchemaDB.COL_CIF,
//                SchemaDB.COL_NOMBRE_EMPRESA,
//                SchemaDB.COL_ID_ROL,
//                SchemaDB.COL_ACTIVO,
//                cliente.getNombre(), cliente.getApellido(), cliente.getCorreo(), cliente.getPassword(), cliente.getCif(), cliente.getEmpresa()
//
//                );
//
//        try {
//            statement =  connection.createStatement();
//
//            return statement.executeUpdate(query);
//        } catch (SQLException e){
//            System.out.println("Error en la ejecucion");
//            System.out.println(e.getMessage());
//        }
//
//        return -1;
//
//    }

    public int crearClientePS(ClienteUsuario cliente) throws SQLException{
        // INSERT INTO usuario (nombre, apellido,correo, password, cif, empresa VALUES ()

        String query = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s) VALUES (?,?,?,?,?,?,3,1)",
                SchemaDB.TABLE_USUARIOS,
                SchemaDB.COL_NOMBRE_USUARIO,
                SchemaDB.COL_APELLIDO_USUARIO,
                SchemaDB.COL_EMAIL,
                SchemaDB.COL_PASSWORD,
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
            preparedStatement.setString(5, cliente.getCif());
            preparedStatement.setString(6, cliente.getEmpresa());

            return preparedStatement.executeUpdate();




    }


}
