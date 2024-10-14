package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

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
                tarifler.add(new Tarif(tarifID, tarifAdi, kategori, hazirlamaSuresi, talimatlar));
            }
        } catch (SQLException e) {
            System.err.println("Veritabanı sorgusu başarısız: " + e.getMessage());
        }
        return tarifler;
    }

    public static List<Malzeme> getMalzemeler() { // malzemeleri almak için bir yöntem ekleyin
        List<Malzeme> malzemeler = new ArrayList<>();
        String query = "SELECT MalzemeAdi, ToplamMiktar, MalzemeBirim, BirimFiyat FROM Malzemeler"; // Malzemeler tablosunu sorgulayın

        try (Connection connection = DatabaseConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String malzemeAdi = resultSet.getString("MalzemeAdi");
                int miktar = resultSet.getInt("ToplamMiktar");
                String birim = resultSet.getString("MalzemeBirim");
                double birimFiyat = resultSet.getDouble("BirimFiyat");
                malzemeler.add(new Malzeme(malzemeAdi, miktar, birim, birimFiyat));
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
