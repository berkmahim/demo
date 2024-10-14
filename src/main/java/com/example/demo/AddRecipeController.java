package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeController {
    @FXML
    private TextField recipeNameField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField prepTimeField;
    @FXML
    private TextArea instructionsField;

    @FXML
    private TextField ingredientNameField;
    @FXML
    private TextField ingredientQuantityField;
    @FXML
    private TextField ingredientUnitField;
    @FXML
    private TextField ingredientPriceField;

    private List<Malzeme> malzemeler = new ArrayList<>();

    @FXML
    private void addIngredient() {
        String name = ingredientNameField.getText();
        int quantity = Integer.parseInt(ingredientQuantityField.getText());
        String unit = ingredientUnitField.getText();
        double price = Double.parseDouble(ingredientPriceField.getText());

        Malzeme malzeme = new Malzeme(name, quantity, unit, price, quantity);
        malzemeler.add(malzeme);

        ingredientNameField.clear();
        ingredientQuantityField.clear();
        ingredientUnitField.clear();
        ingredientPriceField.clear();
    }

    @FXML
    private void addRecipe() {
        String name = recipeNameField.getText();
        String category = categoryField.getText();
        String prepTime = prepTimeField.getText();
        String instructions = instructionsField.getText();

        Tarif tarif = new Tarif(0, name, category, prepTime, instructions, malzemeler);

        // Veritabanına ekleyin
        try {
            DatabaseConnection.addRecipe(tarif);
        } catch (Exception e) {
            System.err.println("Tarif eklenirken hata: " + e.getMessage());
        }
    }
}
