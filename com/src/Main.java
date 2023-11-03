package com.inventorymanagement;

import com.inventorymanagement.items.Item;
import com.inventorymanagement.userinteraction.InventoryController;
import com.inventorymanagement.userinteraction.UserInterface;

public class Main {
    public static void main(String[] args) {
        Item.InventoryManager inventoryManager = new Item.InventoryManager();
        InventoryController inventoryController = new InventoryController(inventoryManager);
        UserInterface cli = new UserInterface(inventoryController);
            cli.start();
    }
}
