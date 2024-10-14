package com.example.demo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class HelloController {
    @FXML
    private TableView<Tarif> tarifTableView;
    @FXML
    private TableColumn<Tarif, String> tarifAdiColumn;
    @FXML
    private TableColumn<Tarif, String> kategoriColumn;
    @FXML
    private TableColumn<Tarif, String> hazirlamaSuresiColumn;
    @FXML
    private TableColumn<Tarif, Double> maliyetColumn;

    @FXML
    private TableView<Malzeme> ingredientsTable;
    @FXML
    private TableColumn<Malzeme, String> malzemeIngredientNameColumn;
    @FXML
    private TableColumn<Malzeme, Integer> malzemeQuantityColumn;
    @FXML
    private TableColumn<Malzeme, String> malzemeUnitColumn;
    @FXML
    private TableColumn<Malzeme, Double> malzemeUnitPriceColumn;
    @FXML
    private TableColumn<Malzeme, String> talimatlarIngredientsColumn; // Yeni talimatlar sütunu

    @FXML
    private Label talimatlarLabel; // Yeni talimatlar etiketi

    public void initialize() {
        tarifAdiColumn.setCellValueFactory(new PropertyValueFactory<>("tarifAdi"));
        kategoriColumn.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        hazirlamaSuresiColumn.setCellValueFactory(new PropertyValueFactory<>("hazirlamaSuresi"));
        maliyetColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(calculateMaliyet(cellData.getValue())).asObject());

        List<Tarif> tarifler = HelloApplication.getTarifler();
        tarifTableView.getItems().addAll(tarifler);

        tarifTableView.setRowFactory(tv -> new TableRow<Tarif>() {
            @Override
            protected void updateItem(Tarif tarif, boolean empty) {
                super.updateItem(tarif, empty);
                if (tarif != null) {
                    if (isMalzemeYeterli(tarif)) {
                        setStyle("-fx-background-color: green;");
                    } else {
                        setStyle("-fx-background-color: red;");
                    }
                } else {
                    setStyle("");
                }
                setOnMouseClicked(event -> {
                    if (!empty) {
                        showIngredients(tarif);
                        showTalimatlar(tarif); // Talimatları göster
                    }
                });
            }
        });

        // Malzemeler tablosu için kolonları ayarla
        malzemeIngredientNameColumn.setCellValueFactory(new PropertyValueFactory<>("malzemeAdi"));
        malzemeQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("kullanilanMiktar"));
        malzemeUnitColumn.setCellValueFactory(new PropertyValueFactory<>("birim"));
        malzemeUnitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("birimFiyat"));
    }

    private void showIngredients(Tarif tarif) {
        if (tarif != null) {
            ingredientsTable.getItems().clear();
            List<Malzeme> malzemeler = tarif.getMalzemeler();
            ingredientsTable.getItems().addAll(malzemeler);
            System.out.println("Malzemeler yüklendi: " + malzemeler.size()); // Debug için
        }
    }

    private void showTalimatlar(Tarif tarif) {
        if (tarif != null) {
            talimatlarLabel.setText(tarif.getTalimatlar());
        }
    }

    private double calculateMaliyet(Tarif tarif) {
        double toplamMaliyet = 0;
        for (Malzeme malzeme : tarif.getMalzemeler()) {
            toplamMaliyet += malzeme.getBirimFiyat() * malzeme.getKullanilanMiktar();
        }
        return toplamMaliyet;
    }

    private boolean isMalzemeYeterli(Tarif tarif) {
        for (Malzeme malzeme : tarif.getMalzemeler()) {
            if (malzeme.getToplamMiktar() < malzeme.getKullanilanMiktar()) {
                return false;
            }
        }
        return true;
    }
}
