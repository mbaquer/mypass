package com.mypass.services;

import com.mypass.models.VaultItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class VaultService {
    private static VaultService instance;
    private final ObservableList<VaultItem> vaultItems;

    private VaultService() {
        this.vaultItems = FXCollections.observableArrayList();
    }

    public static synchronized VaultService getInstance() {
        if (instance == null) {
            instance = new VaultService();
        }
        return instance;
    }

    public ObservableList<VaultItem> getVaultItems() {
        return vaultItems;
    }

    public void addItem(VaultItem item) {
        vaultItems.add(item);
        // Notify observers if needed
    }

    public void editItem(VaultItem item) {
        // Logic to edit item
        // Notify observers if needed
    }

    public void deleteItem(VaultItem item) {
        vaultItems.remove(item);
        // Notify observers if needed
    }
}