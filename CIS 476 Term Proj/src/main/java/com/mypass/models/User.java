package com.mypass.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String password;
    private String securityQuestion1;
    private String answer1;
    private String securityQuestion2;
    private String answer2;
    private String securityQuestion3;
    private String answer3;

    private List<Observer> observers = new ArrayList<>();

    public User(String email, String password, String securityQuestion1, String answer1, String securityQuestion2, String answer2, String securityQuestion3, String answer3) {
        this.email = email;
        this.password = password;
        this.securityQuestion1 = securityQuestion1;
        this.answer1 = answer1;
        this.securityQuestion2 = securityQuestion2;
        this.answer2 = answer2;
        this.securityQuestion3 = securityQuestion3;
        this.answer3 = answer3;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyObservers();
    }

    public String getSecurityQuestion1() {
        return securityQuestion1;
    }

    public void setSecurityQuestion1(String securityQuestion1) {
        this.securityQuestion1 = securityQuestion1;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getSecurityQuestion2() {
        return securityQuestion2;
    }

    public void setSecurityQuestion2(String securityQuestion2) {
        this.securityQuestion2 = securityQuestion2;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getSecurityQuestion3() {
        return securityQuestion3;
    }

    public void setSecurityQuestion3(String securityQuestion3) {
        this.securityQuestion3 = securityQuestion3;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    // Observer methods
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}