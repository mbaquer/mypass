package com.mypass.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VaultItem {
    private final StringProperty type;
    private final StringProperty email;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty cardNumber;
    private final StringProperty cardExpiration;
    private final StringProperty cvv;

    public VaultItem(String type, String email, String username, String password, String cardNumber, String cardExpiration, String cvv) {
        this.type = new SimpleStringProperty(type);
        this.email = new SimpleStringProperty(email != null ? email : "");
        this.username = new SimpleStringProperty(username != null ? username : "");
        this.password = new SimpleStringProperty(password != null ? password : "");
        this.cardNumber = new SimpleStringProperty(cardNumber != null ? cardNumber : "");
        this.cardExpiration = new SimpleStringProperty(cardExpiration != null ? cardExpiration : "");
        this.cvv = new SimpleStringProperty(cvv != null ? cvv : "");
    }

    // Getters and Setters for type
    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

    // Getters and Setters for email
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    // Getters and Setters for username
    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    // Getters and Setters for password
    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    // Getters and Setters for cardNumber
    public String getCardNumber() {
        return cardNumber.get();
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber.set(cardNumber);
    }

    public StringProperty cardNumberProperty() {
        return cardNumber;
    }

    // Getters and Setters for cardExpiration
    public String getCardExpiration() {
        return cardExpiration.get();
    }

    public void setCardExpiration(String cardExpiration) {
        this.cardExpiration.set(cardExpiration);
    }

    public StringProperty cardExpirationProperty() {
        return cardExpiration;
    }

    // Getters and Setters for CVV
    public String getCvv() {
        return cvv.get();
    }

    public void setCvv(String cvv) {
        this.cvv.set(cvv);
    }

    public StringProperty cvvProperty() {
        return cvv;
    }
}