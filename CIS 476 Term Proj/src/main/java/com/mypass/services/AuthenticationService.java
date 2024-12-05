package com.mypass.services;

import com.mypass.models.User;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private static AuthenticationService instance;
    private Map<String, User> users = new HashMap<>();

    private AuthenticationService() {}

    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    public boolean authenticate(String email, String password) {
        User user = users.get(email);
        return user != null && user.getPassword().equals(password);
    }

    public void register(User user) {
        users.put(user.getEmail(), user);
    }
}