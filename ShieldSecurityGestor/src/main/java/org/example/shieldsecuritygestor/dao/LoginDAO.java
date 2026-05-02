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

        String query = "SELECT id_rol FROM usuario WHERE email=? AND password=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int rol = rs.getInt("id_rol");

                switch (rol){
                    case 1 : return new Master();
                    case 2 : return new Administrador();
                    case 3 : return new ClienteUsuario();
                }
            }


        } catch (SQLException e){
            System.out.println("Error en login");
            System.out.println(e.getMessage());
        }

        return null;
    }
}
