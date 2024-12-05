package com.mypass.models;

public class PasswordStrengthObserver implements Observer {
    private final User user;

    public PasswordStrengthObserver(User user) {
        this.user = user;
        user.addObserver(this);
    }

    @Override
    public void update() {
        String password = user.getPassword();
        if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*\\d.*")) {
            System.out.println("Weak password warning");
        }
    }
}