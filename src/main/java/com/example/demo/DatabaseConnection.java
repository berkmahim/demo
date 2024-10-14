package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/YazlabDB";
    private static final String USER = "root"; // MySQL kullanıcı adınız
    private static final String PASSWORD = "8926"; // MySQL şifreniz

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Veritabanı bağlantısı başarılı!");
        } catch (SQLException e) {
            System.err.println("Veritabanı bağlantısı başarısız: " + e.getMessage());
        }
        return connection;
    }
}
