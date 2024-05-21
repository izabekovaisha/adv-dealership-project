package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class AdminUserInterface {

    private String admin_password = "admin_password2024!";
    private Scanner scanner;

    private ContractFileManager contractFileManager;

    public AdminUserInterface() {
        scanner = new Scanner(System.in);
        ContractFileManager contractFileManager = new ContractFileManager();
    }

    public void displayAdminInterface() {
        System.out.println("Enter admin password: ");
        String password = scanner.nextLine().trim();

        if (password.isEmpty()) {
            System.out.println("Password cannot be empty.");
            return;
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
        }
    }

    private void listAllContracts() {
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

    private void searchContractsByCustomerName() {
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine().trim();
        List<Contract> allContracts = contractFileManager.getAllContracts();
        boolean foundByCustomerNames = false;

        for (Contract contract : allContracts) {
            if (contract.getCustomerName().equalsIgnoreCase(customerName)) {

                System.out.println(contract.toString());
                foundByCustomerNames = true;
            }
        }
        if (!foundByCustomerNames) {
            System.out.println("No contracts found for customer: " + customerName);
        }
    }
}
