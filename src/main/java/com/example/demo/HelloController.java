package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory; // Bu satırı ekleyin

import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private ListView<String> tarifListView; // ListView bileşeni

    @FXML
    private TableView<Tarif> tarifTableView; // TableView bileşeni

    @FXML
    private TableColumn<Tarif, String> tarifIngredientNameColumn; // Malzeme Adı sütunu
    @FXML
    private TableColumn<Tarif, String> tarifQuantityColumn; // Toplam Miktar sütunu
    @FXML
    private TableColumn<Tarif, String> tarifUnitColumn; // Birim sütunu
    @FXML
    private TableColumn<Tarif, String> tarifUnitPriceColumn; // Birim Fiyat sütunu

    @FXML
    private TableView<Malzeme> ingredientsTable; // Malzeme TableView bileşeni
    @FXML
    private TableColumn<Malzeme, String> malzemeIngredientNameColumn; // Malzeme adı sütunu
    @FXML
    private TableColumn<Malzeme, Integer> malzemeQuantityColumn; // Miktar sütunu
    @FXML
    private TableColumn<Malzeme, String> malzemeUnitColumn; // Birim sütunu
    @FXML
    private TableColumn<Malzeme, Double> malzemeUnitPriceColumn; // Birim fiyat sütunu

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize() {
        tarifIngredientNameColumn.setCellValueFactory(new PropertyValueFactory<>("tarifAdi"));
        tarifQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("kategori")); // Örnek olarak kategori
        tarifUnitColumn.setCellValueFactory(new PropertyValueFactory<>("hazirlamaSuresi")); // Hazırlama süresi
        tarifUnitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("talimatlar")); // Talimatlar

        List<Tarif> tarifler = HelloApplication.getTarifler(); // Uygulamadan tarifleri al
        tarifTableView.getItems().addAll(tarifler); // Tüm tarifleri ekle

        // Malzemeleri tabloya yükle
        loadMalzemeler();
    }

    private void loadMalzemeler() {
        List<Malzeme> malzemeler = HelloApplication.getMalzemeler();
        ingredientsTable.getItems().addAll(malzemeler); // Malzemeleri tabloya ekleyin

        // TableColumn'ların verilerini ayarlayın
        malzemeIngredientNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMalzemeAdi()));
        malzemeQuantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMiktar()).asObject());
        malzemeUnitColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirim()));
        malzemeUnitPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBirimFiyat()).asObject());
    }
}
