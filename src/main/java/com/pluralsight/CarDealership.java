package com.pluralsight;

import java.util.Scanner;

public class CarDealership {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the car dealership!");
        System.out.println("Please select an option:");
        System.out.println("1. User interface");
        System.out.println("2. Admin interface");
        System.out.println("0. Quit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (choice == 1) {
            UserInterface userInterface = new UserInterface();
            userInterface.display();
        } else if (choice == 2) {
            AdminUserInterface adminInterface = new AdminUserInterface();
            if (!adminInterface.displayAdminInterface()) {
                System.out.println("Incorrect password. Access denied");
            }
        } else if (choice == 0) {
            System.out.println("Goodbye!");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}


