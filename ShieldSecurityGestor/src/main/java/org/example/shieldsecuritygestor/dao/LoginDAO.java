package org.example.shieldsecuritygestor.dao;

import org.example.shieldsecuritygestor.database.DBConnection;
import org.example.shieldsecuritygestor.model.usuario.Administrador;
import org.example.shieldsecuritygestor.model.usuario.ClienteUsuario;
import org.example.shieldsecuritygestor.model.usuario.Master;
import org.example.shieldsecuritygestor.model.usuario.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    private static Connection connection;

    public LoginDAO (){
        connection = DBConnection.getConnection();
    }

    public static Usuario login(String user, String password){

        String query = "SELECT id_usuario, nombre_usuario, id_rol, activo FROM usuario WHERE email=? AND password=? AND activo=1";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int rol = rs.getInt("id_rol");
                int id = rs.getInt("id_usuario");

                Usuario usuario = null;


                switch (rol){
                    case 1 -> usuario = new Master();
                    case 2 -> usuario = new Administrador();
                    case 3 -> usuario = new ClienteUsuario();
                }

                if (usuario != null){
                    usuario.setId(id);
                    usuario.setCorreo(user);
                    usuario.setNombre(rs.getString("nombre_usuario"));
                    return usuario;
                }

            }


        } catch (SQLException e){
            System.out.println("Error en login");
            System.out.println(e.getMessage());
        }

        return null;
    }
}
