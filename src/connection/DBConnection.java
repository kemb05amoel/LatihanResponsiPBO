/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class DBConnection {
   private static final String URL = "jdbc:mysql://localhost:3306/recruit_db";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Koneksi gagal : " + e.getMessage());
            return null;
        }
    }
}
