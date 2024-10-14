package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600); // Pencere boyutunu daha geniş yap
        stage.setTitle("Tarif Uygulaması!");
        stage.setScene(scene);
        stage.show();
    }

    // Tarifler veritabanından alınır
    public static List<Tarif> getTarifler() {
        List<Tarif> tarifler = new ArrayList<>();
        String query = "SELECT TarifID, TarifAdi, Kategori, HazirlamaSuresi, Talimatlar FROM Tarifler"; // Tüm sütunları seç
        try (Connection connection = DatabaseConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int tarifID = resultSet.getInt("TarifID");
                String tarifAdi = resultSet.getString("TarifAdi");
                String kategori = resultSet.getString("Kategori");
                String hazirlamaSuresi = resultSet.getString("HazirlamaSuresi");
                String talimatlar = resultSet.getString("Talimatlar");
                List<Malzeme> malzemeler = getTarifMalzemeleri(tarifID); // Malzemeleri al
                Tarif tarif = new Tarif(tarifID, tarifAdi, kategori, hazirlamaSuresi, talimatlar, malzemeler); // Malzemeler listesini ekle
                tarifler.add(tarif);
            }
        } catch (SQLException e) {
            System.err.println("Veritabanı sorgusu başarısız: " + e.getMessage());
        }
        return tarifler;
    }

    // Veritabanındaki tariflerin malzemelerini getir
    public static List<Malzeme> getTarifMalzemeleri(int tarifID) {
        List<Malzeme> malzemeler = new ArrayList<>();
        String query = "SELECT m.MalzemeAdi, tm.MalzemeMiktar, m.MalzemeBirim, m.BirimFiyat, m.ToplamMiktar " +
                "FROM TarifMalzeme tm " +
                "JOIN Malzemeler m ON tm.MalzemeID = m.MalzemeID " +
                "WHERE tm.TarifID = " + tarifID;
        try (Connection connection = DatabaseConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String malzemeAdi = resultSet.getString("MalzemeAdi");
                int malzemeMiktar = resultSet.getInt("MalzemeMiktar");
                String malzemeBirim = resultSet.getString("MalzemeBirim");
                double birimFiyat = resultSet.getDouble("BirimFiyat");
                int toplamMiktar = resultSet.getInt("ToplamMiktar");
                Malzeme malzeme = new Malzeme(malzemeAdi, malzemeMiktar, malzemeBirim, birimFiyat, toplamMiktar);
                malzemeler.add(malzeme);
            }
        } catch (SQLException e) {
            System.err.println("Veritabanı sorgusu başarısız: " + e.getMessage());
        }
        return malzemeler;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
