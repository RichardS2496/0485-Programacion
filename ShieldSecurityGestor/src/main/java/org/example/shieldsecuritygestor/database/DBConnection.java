package org.example.shieldsecuritygestor.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection(){

        if (connection == null){
            createConnection();
        }
        return connection;
    }

    private static void createConnection(){
        String user = "root";
        String pass = "";
        String database = "shieldsecurity";

        try {
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3308/"+database,user,pass);
            System.out.println("Conexion Exitosa");

        } catch (SQLException e) {
            System.out.println("Problemas para conectar");
            System.out.println(e.getMessage());
        }
    }
}
