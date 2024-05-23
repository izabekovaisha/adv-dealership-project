package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class AdminUserInterface {

    private final String admin_password = "admin_password2024!";
    private final Scanner scanner;
    private final ContractFileManager contractFileManager;

    public AdminUserInterface() {
        scanner = new Scanner(System.in);
        contractFileManager = new ContractFileManager();
    }

    /* Method to display the admin interface and handle admin functionalities by:
     * Reading, validating, and checking  if password is empty
     * Checking if the entered password matches the admin password and performing actions based on admin's choice
     */
    public boolean displayAdminInterface() {
        System.out.println("Enter admin password: ");
        String password = scanner.nextLine().trim();

        if (password.isEmpty()) {
            System.out.println("Password cannot be empty.");
            return false;
        }

        if (password.equals(admin_password)) {
            boolean quit = false;
            while (!quit) {
                System.out.println("---------- Admin menu ----------");
                System.out.println("1. List all contracts");
                System.out.println("2. Search contracts by customer name");
                System.out.println("0. Quit");

                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1":
                        listAllContracts();
                        break;
                    case "2":
                        searchContractsByCustomerName();
                        break;
                    case "0":
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /* Method to list all contracts stored in the system by:
     * Retrieving all contracts from the ContractFileManager
     * Checking if contracts list is empty and displaying details of each contract
     */
    public void listAllContracts() {
        List<Contract> allContracts = contractFileManager.getAllContracts();

        if (allContracts.isEmpty()) {
            System.out.println("No contracts found. Please try again.");
            return;
        }
        System.out.println("All contracts: ");
        for (Contract contract : allContracts) {
            System.out.println(contract.toString());
        }
    }

    /* Method to search contracts by customer name by:
     * Reading customer name from user input and retrieving all contracts
     * Iterating through each contract to find matching customer name
     * Notifying if no contracts found for the provided customer name
     */
    public void searchContractsByCustomerName() {
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine().trim();
        List<Contract> allContracts = contractFileManager.getAllContracts();
        boolean found = false;

        for (Contract contract : allContracts) {
            if (contract.getCustomerName().equalsIgnoreCase(customerName)) {
                System.out.println(contract.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contracts found for customer: " + customerName);
        }
    }
}

