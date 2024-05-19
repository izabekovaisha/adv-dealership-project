package com.pluralsight;

import java.io.*;


public class ContractFileManager {


    public void saveContract(Contract contract) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("contracts.csv"))) {
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
                        + "|" + salesContract.getProcessingFee() + "|" + salesContract.getTotalPrice()
                        + "|" + (salesContract.isFinance() ? "YES" : "NO" + "|" + salesContract.getMonthlyPayment()));
            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                bw.write(commonContractInfo + vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake()
                        + "|" + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer()
                        + "|" + vehicle.getPrice() + "|" + leaseContract.getExpectedEndingValue() + "|" + leaseContract.getLeaseFee()
                        + "|" + leaseContract.getTotalPrice() + "|" + leaseContract.getMonthlyPayment());
                bw.newLine();
            }

            System.out.println("Contract saved successfully to contracts.csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



