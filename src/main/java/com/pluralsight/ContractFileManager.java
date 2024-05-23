package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ContractFileManager {

    public void saveContract(Contract contract) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("contracts.csv", true))) {
            String contractType = "";
            if (contract instanceof SalesContract) {
                contractType = "SALE";
            } else if (contract instanceof LeaseContract) {
                contractType = "LEASE";
            }
            String commonContractInfo = contractType + "|" + contract.getDateOfContract()
                    + "|" + contract.getCustomerName() + "|" + contract.getCustomerEmail() + "|";

            Vehicle vehicle = contract.getVehicleSold();
            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;
                bw.write(commonContractInfo + vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake()
                        + "|" + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer()
                        + "|" + vehicle.getPrice() + "|" + salesContract.getSalesTaxAmount() + "|" + salesContract.getRecordingFee()
                        + "|" + salesContract.getProcessingFee() + "|" + (salesContract.isFinance() ? "YES" : "NO"));
            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                bw.write(commonContractInfo + vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake()
                        + "|" + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer()
                        + "|" + vehicle.getPrice() + "|" + leaseContract.getExpectedEndingValue() + "|" + leaseContract.getLeaseFee());
                bw.newLine();
            }

            System.out.println("Contract saved successfully to contracts.csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Contract> getAllContracts() {
        List<Contract> allContracts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("contracts.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                String contractType = fields[0];
                String dateOfContract = fields[1];
                String customerName = fields[2];
                String customerEmail = fields[3];
                int vin = Integer.parseInt(fields[4]);
                int year = Integer.parseInt(fields[5]);
                String make = fields[6];
                String model = fields[7];
                String vehicleType = fields[8];
                String color = fields[9];
                int odometer = Integer.parseInt(fields[10]);
                double price = Double.parseDouble(fields[11]);

                if (contractType.equals("SALE")) {
                    double salesTaxAmount = Double.parseDouble(fields[12]);
                    double recordingFee = Double.parseDouble(fields[13]);
                    double processingFee = Double.parseDouble(fields[14]);
                    boolean finance = fields[15].equalsIgnoreCase("YES");

                    SalesContract salesContract = new SalesContract(dateOfContract, customerName, customerEmail,
                            new Vehicle(vin, year, make, model, vehicleType, color, odometer, price),
                            salesTaxAmount, recordingFee, processingFee, finance);
                    allContracts.add(salesContract);

                } else if (contractType.equals("LEASE")) {
                    double expectedEndingValue = Double.parseDouble(fields[12]);
                    double leaseFee = Double.parseDouble(fields[13]);
                    LeaseContract leaseContract = new LeaseContract(dateOfContract, customerName, customerEmail,
                            new Vehicle(vin, year, make, model, vehicleType, color, odometer, price),
                            expectedEndingValue, leaseFee);
                    allContracts.add(leaseContract);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allContracts;
    }
}




