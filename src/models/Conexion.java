package models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    public Connection conectarServidor(){
        String url = "jdbc:mysql://localhost:3306/proyecto?serverTimezone=America/Lima";
        String user = "root";
        String pass = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
            System.err.println("No se conectó a la Base de Datos: "+e.getMessage());
        }               
        return con;
    }
}
