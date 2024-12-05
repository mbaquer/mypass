package com.mypass.controllers;

import com.mypass.services.AuthenticationService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;

    private final AuthenticationService authService = AuthenticationService.getInstance();

    @FXML
    public void handleLogin() {
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (authService.authenticate(email, password)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mypass/fxml/vault.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) txtEmail.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("MyPass - Vault");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email or password.");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mypass/fxml/register.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("MyPass - Register");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}