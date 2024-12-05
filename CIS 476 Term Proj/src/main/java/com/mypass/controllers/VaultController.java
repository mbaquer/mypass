package com.mypass.controllers;

import com.mypass.models.VaultItem;
import com.mypass.services.VaultService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class VaultController {

    @FXML
    private TableView<VaultItem> vaultTable;
    @FXML
    private TableColumn<VaultItem, String> colType;
    @FXML
    private TableColumn<VaultItem, String> colEmail;
    @FXML
    private TableColumn<VaultItem, String> colUsername;
    @FXML
    private TableColumn<VaultItem, String> colPassword;
    @FXML
    private TableColumn<VaultItem, String> colCardNumber;
    @FXML
    private TableColumn<VaultItem, String> colCardExpiration;
    @FXML
    private TableColumn<VaultItem, String> colCVV;

    private VaultService vaultService = VaultService.getInstance();

    @FXML
    public void initialize() {
        // Initialize the vault table with existing vault items
        ObservableList<VaultItem> vaultItems = FXCollections.observableArrayList(vaultService.getVaultItems());
        vaultTable.setItems(vaultItems);
        colType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        colPassword.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        colCardNumber.setCellValueFactory(cellData -> cellData.getValue().cardNumberProperty());
        colCardExpiration.setCellValueFactory(cellData -> cellData.getValue().cardExpirationProperty());
        colCVV.setCellValueFactory(cellData -> cellData.getValue().cvvProperty());
    }

    public VaultService getVaultService() {
        return vaultService;
    }

    public void updateTable() {
        vaultTable.setItems(FXCollections.observableArrayList(vaultService.getVaultItems()));
    }

    @FXML
    public void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mypass/fxml/add_edit_item.fxml"));
            Parent root = loader.load();

            AddEditItemController controller = loader.getController();
            controller.setVaultController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add New Item");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            updateTable();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load the add/edit item dialog.");
        }
    }

    @FXML
    public void handleEdit() {
        VaultItem selectedItem = vaultTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mypass/fxml/add_edit_item.fxml"));
                Parent root = loader.load();

                AddEditItemController controller = loader.getController();
                controller.setVaultController(this);
                controller.setVaultItem(selectedItem);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Edit Item");
                stage.setScene(new Scene(root));
                stage.showAndWait();

                updateTable();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Could not load the add/edit item dialog.");
            }
        } else {
            showAlert("No Selection", "Please select an item to edit.");
        }
    }

    @FXML
    public void handleDelete() {
        int selectedIndex = vaultTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            VaultItem item = vaultTable.getItems().remove(selectedIndex);
            vaultService.deleteItem(item);
        } else {
            showAlert("No Selection", "Please select an item to delete.");
        }
    }

    @FXML
    public void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mypass/fxml/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) vaultTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("MyPass - Login");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load the login screen.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}