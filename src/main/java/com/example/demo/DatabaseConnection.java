package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; // PreparedStatement sınıfını ekliyoruz
import java.sql.ResultSet; // ResultSet sınıfını ekliyoruz
import java.sql.SQLException;
import java.sql.Statement;

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

    public static void addRecipe(Tarif tarif) throws SQLException {
        try (Connection connection = connect()) {
            String insertRecipeQuery = "INSERT INTO Tarifler (TarifAdi, Kategori, HazirlamaSuresi, Talimatlar) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertRecipeQuery, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, tarif.getTarifAdi());
                preparedStatement.setString(2, tarif.getKategori());
                preparedStatement.setString(3, tarif.getHazirlamaSuresi());
                preparedStatement.setString(4, tarif.getTalimatlar());
                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        tarif.setTarifID(generatedKeys.getInt(1));
                    }
                }
            }

            String insertIngredientQuery = "INSERT INTO TarifMalzeme (TarifID, MalzemeAdi, MalzemeMiktar, MalzemeBirim, BirimFiyat, ToplamMiktar) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertIngredientQuery)) {
                for (Malzeme malzeme : tarif.getMalzemeler()) {
                    preparedStatement.setInt(1, tarif.getTarifID());
                    preparedStatement.setString(2, malzeme.getMalzemeAdi());
                    preparedStatement.setInt(3, malzeme.getKullanilanMiktar());
                    preparedStatement.setString(4, malzeme.getBirim());
                    preparedStatement.setDouble(5, malzeme.getBirimFiyat());
                    preparedStatement.setInt(6, malzeme.getToplamMiktar());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        }
    }
}
