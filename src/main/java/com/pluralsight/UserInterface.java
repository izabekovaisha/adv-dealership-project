package com.pluralsight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void display() {
        init();
        boolean quit = false;
        while (!quit) {
            System.out.println("---------- Menu ----------");
            System.out.println("1. Get vehicles by price");
            System.out.println("2. Get vehicles by make and model");
            System.out.println("3. Get vehicles by year");
            System.out.println("4. Get vehicles by color");
            System.out.println("5. Get vehicles by mileage");
            System.out.println("6. Get vehicles by type");
            System.out.println("7. Get all vehicles");
            System.out.println("8. Add vehicle");
            System.out.println("9. Remove vehicle");
            System.out.println("10. Sell vehicle");
            System.out.println("11.Lease vehicle");
            System.out.println("12. Admin");
            System.out.println("0. Quit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "10":
                    sellVehicle();
                    break;
                case "11":
                    leaseVehicle();
                    break;
                case "12":
                    adminLogin();
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

    public void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();
        List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int max = scanner.nextInt();
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {
        System.out.print("Enter vehicle vin: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();

        System.out.print("Enter vehicle year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine();

        System.out.print("Enter vehicle mileage: ");
        int mileage = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle type (Car, Truck, SUV, Motorcycle): ");
        String type = scanner.nextLine();

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);

        dealership.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");
        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {
        System.out.print("Enter the VIN of the vehicle you wish to remove: ");
        int vin = scanner.nextInt();

        boolean vehicleRemoved = false;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                System.out.println("Vehicle removed successfully!");
                vehicleRemoved = true;
                break;
            }
        }

        if (!vehicleRemoved) {
            System.out.println("Vehicle not found. Please try again.");
            return;
        }

        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);
    }

    public void sellVehicle() {
        System.out.println("Enter contract date (YYYYMMDD): ");
        String dateOfContract = scanner.nextLine();

        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.println("Enter customer email: ");
        String customerEmail = scanner.nextLine();

        System.out.println("Enter vehicle VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicleSold = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleSold = vehicle;
                break;
            }
        }
        if (vehicleSold == null) {
            System.out.println("Vehicle not found in inventory. Please try again.");
            return;
        }

        System.out.println("Enter sales tax amount: ");
        double salesTaxAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter recording fee: ");
        double recordingFee = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter processing fee: ");
        double processingFee = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Is there an option for financing? (YES/NO): ");
        boolean financeOption = scanner.next().equalsIgnoreCase("YES");
        scanner.nextLine();
        SalesContract salesContract = new SalesContract(dateOfContract, customerName, customerEmail, vehicleSold, salesTaxAmount, recordingFee, processingFee, financeOption);

        ContractFileManager contractFileManager = new ContractFileManager();
        contractFileManager.saveContract(salesContract);

        dealership.removeVehicle(vehicleSold);
        System.out.println("Vehicle sold and contract saved successfully.");
    }

    public void leaseVehicle() {
        System.out.println("Enter contract date (YYYYMMDD): ");
        String dateOfContract = scanner.nextLine();

        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.println("Enter customer email: ");
        String customerEmail = scanner.nextLine();

        System.out.println("Enter vehicle VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicleLeased = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleLeased = vehicle;
                break;
            }
        }
        if (vehicleLeased == null) {
            System.out.println("Vehicle not found in inventory. Please try again.");
            return;
        }

        int currentYear = LocalDateTime.now().getYear();
        if (currentYear - vehicleLeased.getYear() > 3) {
            System.out.println("Vehicle is older than 3 years and cannot be leased.");
            return;
        }

        System.out.println("Enter expected ending value: ");
        double expectedEndingValue = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter lease fee: ");
        double leaseFee = scanner.nextDouble();
        scanner.nextLine();

        LeaseContract leaseContract = new LeaseContract(dateOfContract, customerName, customerEmail, vehicleLeased, expectedEndingValue, leaseFee);

        ContractFileManager contractFileManager = new ContractFileManager();
        contractFileManager.saveContract(leaseContract);

        dealership.removeVehicle(vehicleLeased);
        System.out.println("Vehicle leased and contract saved successfully.");

    }

    private void adminLogin() {
        System.out.println("Enter the admin password: ");
        String password = scanner.nextLine();
        if (password.equals(admin_password)){
            System.out.println("Login was successful");
            AdminUserInterface adminUserInterface = new AdminUserInterface(dealership);
            adminInterface.display();
        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }

    private void init() {
        DealershipFileManager manager = new DealershipFileManager();
        dealership = manager.getDealership();
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }
    }

}