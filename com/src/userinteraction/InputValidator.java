package com.inventorymanagement.userinteraction;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    public static boolean readBooleanInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextBoolean();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
                scanner.nextLine();
            }
        }
    }

    public static int readIntInput(Scanner scanner) {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    public static double readDoubleInput(Scanner scanner) {
        while (true) {
            try {
                double input = scanner.nextDouble();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid double.");
                scanner.nextLine();
            }
        }
    }
}
