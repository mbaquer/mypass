package com.mypass.controllers;

import com.mypass.models.VaultItem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEditItemController {

    @FXML
    private ComboBox<String> comboType;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtCardNumber;
    @FXML
    private TextField txtCardExpiration;
    @FXML
    private TextField txtCVV;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    private VaultController vaultController;
    private VaultItem vaultItem;

    public void setVaultController(VaultController vaultController) {
        this.vaultController = vaultController;
    }

    public void setVaultItem(VaultItem vaultItem) {
        this.vaultItem = vaultItem;
        if (vaultItem != null) {
            comboType.setValue(vaultItem.getType());
            if (vaultItem.getType().equals("Login")) {
                txtEmail.setText(vaultItem.getEmail());
                txtUsername.setText(vaultItem.getUsername());
                txtPassword.setText(vaultItem.getPassword());
                showLoginFields();
            } else if (vaultItem.getType().equals("Credit Card")) {
                txtCardNumber.setText(vaultItem.getCardNumber());
                txtCardExpiration.setText(vaultItem.getCardExpiration());
                txtCVV.setText(vaultItem.getCvv());
                showCreditCardFields();
            }
        }
    }

    @FXML
    public void initialize() {
        // Initialize comboBox items in the controller
        comboType.setItems(FXCollections.observableArrayList("Login", "Credit Card"));
        comboType.setOnAction(event -> {
            String type = comboType.getValue();
            if (type.equals("Login")) {
                showLoginFields();
            } else if (type.equals("Credit Card")) {
                showCreditCardFields();
            }
        });
    }

    private void showLoginFields() {
        txtEmail.setVisible(true);
        txtUsername.setVisible(true);
        txtPassword.setVisible(true);
        txtCardNumber.setVisible(false);
        txtCardExpiration.setVisible(false);
        txtCVV.setVisible(false);
    }

    private void showCreditCardFields() {
        txtEmail.setVisible(false);
        txtUsername.setVisible(false);
        txtPassword.setVisible(false);
        txtCardNumber.setVisible(true);
        txtCardExpiration.setVisible(true);
        txtCVV.setVisible(true);
    }

    @FXML
    public void handleSave() {
        String type = comboType.getValue();
        String email = type.equals("Login") ? txtEmail.getText() : "";
        String username = type.equals("Login") ? txtUsername.getText() : "";
        String password = type.equals("Login") ? txtPassword.getText() : "";
        String cardNumber = type.equals("Credit Card") ? txtCardNumber.getText() : "";
        String cardExpiration = type.equals("Credit Card") ? txtCardExpiration.getText() : "";
        String cvv = type.equals("Credit Card") ? txtCVV.getText() : "";

        // Create or update the VaultItem
        if (vaultItem != null) {
            vaultItem.setType(type);
            vaultItem.setEmail(email);
            vaultItem.setUsername(username);
            vaultItem.setPassword(password);
            vaultItem.setCardNumber(cardNumber);
            vaultItem.setCardExpiration(cardExpiration);
            vaultItem.setCvv(cvv);
        } else {
            vaultItem = new VaultItem(type, email, username, password, cardNumber, cardExpiration, cvv);
            vaultController.getVaultService().addItem(vaultItem);
        }

        vaultController.updateTable();
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleCancel() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}