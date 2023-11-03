package com.inventorymanagement.userinteraction;

import com.inventorymanagement.IPaymentMethod;
import com.inventorymanagement.items.*;
import com.inventorymanagement.order.Order;
import com.inventorymanagement.payment.CreditCardPayment;
import com.inventorymanagement.payment.PayPalPayment;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.inventorymanagement.userinteraction.InputValidator.*;

public class InventoryController {
    private final Item.InventoryManager inventoryManager;
    private int nextOrderID = 1;

    public InventoryController(Item.InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;


    }

    // TODO
//    void viewShoppingCart(Scanner order) {
//        ShoppingCart shoppingCart = order.getShoppingCart();
//        List<OrderItem> cartItems = shoppingCart.getItems();
//
//        if (cartItems.isEmpty()) {
//            System.out.println("Your shopping cart is empty.");
//        } else {
//            System.out.println("Shopping Cart Contents:");
//            for (OrderItem item : cartItems) {
//                InventoryItem inventoryItem = item.getInventoryItem();
//                int quantity = item.getQuantity();
//                double totalCost = item.calculateCost();
//
//                System.out.println("Item: " + inventoryItem.getName());
//                System.out.println("ID: " + inventoryItem.getItemID());
//                System.out.println("Quantity: " + quantity);
//                System.out.println("Total Cost: $" + totalCost);
//                System.out.println("------------");
//            }
//        }
//    }

    void placeOrder(Scanner scanner) {
        System.out.println("Available Items:");

        List<InventoryItem> inventory = inventoryManager.loadInventoryItemsFromFile("src/com/inventorymanagement/data/items.txt");

        for (InventoryItem item : inventory) {
            if (item.getQuantity() > 0) {
                System.out.println("Item -> " + "Name: " + item.getName()
                        + " (ID: " + item.getItemID() + ") " + ", Quantity: " + item.getQuantity()
                        + ", Price: " + item.getPrice() + ", Category: " + item.getCategory());
            }
        }
        int itemIDSearch;
        while (true) {
            System.out.print("Purchase item by ID: ");
            if (scanner.hasNextInt()) {
                itemIDSearch = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for the item ID.");
                scanner.nextLine();
            }
        }

        InventoryItem selectedItem = null;
        boolean itemFound = false;

        for (InventoryItem item : inventory) {
            if (item.getItemID() == itemIDSearch) {
                itemFound = true;
                selectedItem = item;
                break;
            }
        }

        if (!itemFound) {
            System.out.println("Item not found in inventory.");
            return;
        }

        System.out.print("Enter the quantity to purchase: ");
        int quantityToPurchase;
        try {
            quantityToPurchase = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the quantity.");
            return;
        }

        if (quantityToPurchase <= 0 || quantityToPurchase > selectedItem.getQuantity()) {
            System.out.println("Invalid quantity. Please try again.");
            return;
        }

        double totalCost = selectedItem.getPrice() * quantityToPurchase;

        selectedItem.reduceQuantity(quantityToPurchase);

        Item.InventoryManager.updateInventoryItemsToFile("src/com/inventorymanagement/data/items.txt", inventory);

        Order order = new Order(generateOrderID(), new ArrayList<>(), totalCost, choosePaymentMethod(scanner));
        order.addOrderItem(selectedItem, quantityToPurchase);

        System.out.println("Order placed successfully!");
        System.out.println("Order ID: " + order.getOrderID());
        System.out.println("Payment Method: " + order.getPaymentMethod().getDescription());
        System.out.println("Total cost: " + totalCost);
    }


    private int generateOrderID() {
        return nextOrderID++;
    }

    private IPaymentMethod choosePaymentMethod(Scanner scanner) {
        System.out.println("Choose a payment method:");
        System.out.println("1. Credit Card Payment");
        System.out.println("2. PayPal Payment");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter credit card number: ");
                String creditCardNumber = scanner.nextLine();
                System.out.print("Credit card holder: ");
                String creditCardHolder = scanner.nextLine();
                System.out.print("Expiration date: ");
                String expirationDate = scanner.nextLine();
                System.out.print("Security code: ");
                String securityCode = scanner.nextLine();
                return new CreditCardPayment(creditCardNumber, creditCardHolder, expirationDate, securityCode);

            case 2:
                System.out.print("Enter PayPal account: ");
                String payPalAccount = scanner.nextLine();
                return new PayPalPayment(payPalAccount);

            default:
                System.out.println("Invalid choice. Defaulting to Credit Card Payment.");
                return null;
        }
    }

    public void searchByCategory(Scanner scanner) {
        System.out.println("Enter category to search for: ");
        String searchCategory = scanner.nextLine();

        List<InventoryItem> inventory = inventoryManager.loadInventoryItemsFromFile("src/com/inventorymanagement/data/items.txt");
        boolean found = false;

        for (InventoryItem item : inventory) {
            if (item.getCategory().equalsIgnoreCase(searchCategory)) {
                System.out.println("Item found: " + item.getName() + " (ID: " + item.getItemID() + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No items found in the specified category.");
        }
    }


    void listItems() {
        List<InventoryItem> inventory = inventoryManager.loadInventoryItemsFromFile("src/com/inventorymanagement/data/items.txt");

        if (inventory.isEmpty()) {
            System.out.println("No items found in the inventory.");
            return;
        }
        for (InventoryItem item : inventory) {
            System.out.println("Item -> " + "ID: " + item.getItemID() + ", Name: "
                    + item.getName() + ", Quantity: " + item.getQuantity()
                    + ", Price: " + item.getPrice() + ", Category: " + item.getCategory());
        }
    }


    void removeItemById(Scanner scanner) {
        System.out.print("Enter item ID to remove: ");
        int itemIDToRemove;
        try {
            itemIDToRemove = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer for the item ID.");
            return;
        }

        List<InventoryItem> inventory = inventoryManager.loadInventoryItemsFromFile("src/com/inventorymanagement/data/items.txt");
        List<InventoryItem> updatedInventory = new ArrayList<>(inventory);

        System.out.println("Items in inventory:");
        for (InventoryItem item : inventory) {
            System.out.println("Item ID: " + item.getItemID());
        }

        boolean itemRemoved = false;

        for (InventoryItem item : inventory) {
            if (item.getItemID() == itemIDToRemove) {
                updatedInventory.remove(item);
                itemRemoved = true;
                break;
            }
        }

        if (itemRemoved) {
            try {
                File inputFile = new File("src/items.txt");
                File tempFile = new File("src/items_temp.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("ID: " + itemIDToRemove)) {
                        continue;
                    }
                    writer.write(line + System.getProperty("line.separator"));
                }

                reader.close();
                writer.close();

                if (!inputFile.delete()) {
                    System.err.println("Error deleting the original file.");
                }

                if (!tempFile.renameTo(inputFile)) {
                    System.err.println("Error renaming the temporary file.");
                }

                System.out.println("Item removed.");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error removing the item.");
            }

            Item.InventoryManager.updateInventoryItemsToFile("src/com/inventorymanagement/data/items.txt", updatedInventory);
        } else {
            System.out.println("Item not found in the inventory.");
        }
    }

    void addItem(Scanner scanner) {
        while (true) {
            System.out.print("Enter item category: ");
            String category = scanner.nextLine();

            System.out.print("Is the item breakable? (true/false): ");
            boolean breakable = readBooleanInput(scanner);

            System.out.print("Is the item perishable? (true/false): ");
            boolean perishable = readBooleanInput(scanner);

            System.out.print("Is the item sellable? (true/false): ");
            boolean sellable = readBooleanInput(scanner);

            System.out.print("Enter item ID: ");
            int itemID = readIntInput(scanner);

            System.out.print("Enter item quantity: ");
            int quantity = readIntInput(scanner);

            System.out.print("Enter item name: ");
            String name = scanner.nextLine();

            System.out.print("Enter item price: ");
            double price = readDoubleInput(scanner);

            System.out.print("Enter item type (Electronic/Grocery/Fragile): ");
            String itemType = scanner.nextLine();


            if (!isValidItemType(itemType)) {
                System.out.println("Invalid item type. Please enter 'Electronic', 'Grocery', or 'Fragile'.");
                continue;
            }

            InventoryItem newItem = createInventoryItem(category, breakable, perishable, sellable, itemID, quantity, name, price, itemType);

            if (newItem == null) {
                System.out.println("Error creating the item.");
                continue;
            }

            inventoryManager.addItem(newItem);

            Item.InventoryManager.saveInventoryItemsToFile("src/com/inventorymanagement/data/items.txt", inventoryManager.getInventory(), false);

            System.out.println("Item added and inventory updated.");
            break;
        }
    }

    private static boolean isValidItemType(String itemType) {
        return itemType.equalsIgnoreCase("Electronic") || itemType.equalsIgnoreCase("Grocery") || itemType.equalsIgnoreCase("Fragile");

    }

    private static InventoryItem createInventoryItem(String category, boolean breakable, boolean perishable, boolean sellable, int itemID, int quantity, String name, double price, String itemType) {
        InventoryItem newItem = null;

        if ("Electronic".equalsIgnoreCase(itemType)) {
            newItem = new ElectronicItem(category, breakable, perishable, sellable, itemID, quantity, name, price);
        } else if ("Grocery".equalsIgnoreCase(itemType)) {
            newItem = new GroceryItem(category, breakable, perishable, sellable, itemID, quantity, name, price);
        } else if ("Fragile".equalsIgnoreCase(itemType)) {
            newItem = new FragileItem(category, breakable, perishable, sellable, itemID, quantity, name, price);
        }
        return newItem;
    }
}