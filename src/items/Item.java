package com.inventorymanagement.items;

import com.inventorymanagement.IBreakable;
import com.inventorymanagement.ICategorisable;
import com.inventorymanagement.IPerishable;
import com.inventorymanagement.ISellable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Item implements ICategorisable, IBreakable, IPerishable, ISellable {
    private String category;
    private boolean breakable;
    private boolean perishable;
    private boolean sellable;

    public Item(String category, boolean breakable, boolean perishable, boolean sellable) {
        this.category = category;
        this.breakable = breakable;
        this.perishable = perishable;
        this.sellable = sellable;
    }

    public String getCategory() {
        return category;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public boolean isSellable() {
        return sellable;
    }

    public abstract String getItemDetails();

    public static class InventoryManager {
        private final List<InventoryItem> inventory;

        public InventoryManager() {
            this.inventory = new ArrayList<>();
        }

        /**
         * Saves the inventory items to a file.
         */
        public static void saveInventoryItemsToFile(String fileName, List<InventoryItem> data, boolean b) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                for (InventoryItem item : data) {
                    String itemDetails = getItemDetailsAsString(item);
                    writer.write(itemDetails);
                    writer.write(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error saving data to the file.");
            }
        }

        /**
         * Update the inventory items to a file.
         */
        public static void updateInventoryItemsToFile(String fileName, List<InventoryItem> inventory) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
                for (InventoryItem item : inventory) {
                    String itemDetails = getItemDetailsAsString(item);
                    writer.write(itemDetails);
                    writer.write(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error saving data to the file.");
            }
        }

        /**
         * Load the inventory items from a file.
         */
        public static List<InventoryItem> loadInventoryItemsFromFile(String fileName) {
            List<InventoryItem> inventory = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName = "src/com/inventorymanagement/data/items.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith("Item category:")) {
                        String[] parts = line.split(", ");
                        String category = parts[0].substring("Item category: ".length());
                        boolean breakable = Boolean.parseBoolean(parts[1].substring("Breakable: ".length()));
                        boolean perishable = Boolean.parseBoolean(parts[2].substring("Perishable: ".length()));
                        boolean sellable = Boolean.parseBoolean(parts[3].substring("Sellable: ".length()));
                        int itemID = Integer.parseInt(parts[4].substring("ID: ".length()));
                        int quantity = Integer.parseInt(parts[5].substring("Quantity: ".length()));
                        String name = parts[6].substring("Name: ".length());
                        double price = Double.parseDouble(parts[7].substring("Price: ".length()));
                        String type = parts[8].substring("Type: ".length());
                        InventoryItem item = new GeneralInventoryItem(category, breakable, perishable, sellable, itemID, quantity, name, price);
                        inventory.add(item);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading inventory items from the file.");
            }

            return inventory;
        }

        /**
         * Get the inventory items details.
         */
        public static String getItemDetailsAsString(InventoryItem item) {
            return "Item category: " + item.getCategory() + ", " +
                    "Breakable: " + item.isBreakable() + ", " +
                    "Perishable: " + item.isPerishable() + ", " +
                    "Sellable: " + item.isSellable() + ", " +
                    "ID: " + item.getItemID() + ", " +
                    "Quantity: " + item.getQuantity() + ", " +
                    "Name: " + item.getName() + ", " +
                    "Price: " + item.getPrice() + ", " +
                    "Type: " + item.getItemDetails();
        }

        /**
         * Add the inventory items.
         */
        public void addItem(InventoryItem newItem) {
            inventory.add(newItem);
        }

        /**
         * List preview of inventory items.
         */
        public List<InventoryItem> getInventory() {
            List<String> inventoryList = new ArrayList<>();

            for (InventoryItem item : inventory) {
                String itemDetails = "Item: " + item.getName() + "Price: " + item.getPrice();
                inventoryList.add(itemDetails);
            }
            return inventory;
        }
    }
}

