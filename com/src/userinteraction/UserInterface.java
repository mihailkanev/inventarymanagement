package com.inventorymanagement.userinteraction;

import com.inventorymanagement.items.Item;

import java.util.Scanner;

public class UserInterface {
    private final InventoryController inventoryController;
    private final MainMenu mainMenu;

    public UserInterface(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
        this.mainMenu = new MainMenu();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            mainMenu.displayAndGetUserChoice();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    inventoryController.addItem(scanner);
                    break;
                case 2:
                    inventoryController.removeItemById(scanner);
                    break;
                case 3:
                    inventoryController.listItems();
                    break;
                case 4:
                    inventoryController.searchByCategory(scanner);
                    break;
                case 5:
                    inventoryController.placeOrder(scanner);
                    break;
                case 6:
                    //inventoryController.viewShoppingCart();
                    break;
                case 7:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
