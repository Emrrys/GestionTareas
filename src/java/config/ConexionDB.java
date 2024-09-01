/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sebastian
 */
public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/db_gestion_tareas";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return con;
    }
}
