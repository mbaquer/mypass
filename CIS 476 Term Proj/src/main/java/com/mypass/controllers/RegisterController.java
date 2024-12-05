package com.mypass.controllers;

import com.mypass.models.User;
import com.mypass.services.AuthenticationService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private ProgressBar passwordStrength;
    @FXML
    private TextField txtSecurity1;
    @FXML
    private TextField txtAnswer1;
    @FXML
    private TextField txtSecurity2;
    @FXML
    private TextField txtAnswer2;
    @FXML
    private TextField txtSecurity3;
    @FXML
    private TextField txtAnswer3;

    private final AuthenticationService authService = AuthenticationService.getInstance();

    @FXML
    public void handleRegister() {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        String security1 = txtSecurity1.getText();
        String answer1 = txtAnswer1.getText();
        String security2 = txtSecurity2.getText();
        String answer2 = txtAnswer2.getText();
        String security3 = txtSecurity3.getText();
        String answer3 = txtAnswer3.getText();

        if (password.equals(confirmPassword)) {
            User user = new User(email, password, security1, answer1, security2, answer2, security3, answer3);
            authService.register(user);
            try {
                // Navigate to the login screen
                Stage stage = (Stage) txtEmail.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mypass/fxml/login.fxml"));
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match.");
            alert.showAndWait();
        }
    }

    @FXML
    public void handlePasswordKeyRelease() {
        String password = txtPassword.getText();
        passwordStrength.setVisible(true);
        passwordStrength.setProgress(calculatePasswordStrength(password));
    }

    private double calculatePasswordStrength(String password) {
        int score = 0;
        if (password.length() >= 8) score++;  // Length at least 8
        if (password.matches(".*[A-Z].*")) score++;  // Has uppercase letter
        if (password.matches(".*[a-z].*")) score++;  // Has lowercase letter
        if (password.matches(".*\\d.*")) score++;  // Has digit
        if (password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) score++;  // Has special character

        return (double) score / 5;  // Normalize score to 0-1 range
    }
}