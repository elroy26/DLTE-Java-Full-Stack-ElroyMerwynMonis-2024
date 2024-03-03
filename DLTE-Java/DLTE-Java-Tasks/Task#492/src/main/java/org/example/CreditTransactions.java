package org.example;
import org.example.CreditCard;

import java.util.Date;
import java.util.Scanner;

public class CreditTransactions implements Runnable {
    private CreditCard[] transactionsDetails;

    public CreditTransactions(CreditCard[] transactionsDetails) {
        this.transactionsDetails = transactionsDetails;
    }

    public static void main(String[] args) {
        CreditCard[] transactionsDetails = {
                new CreditCard(new Date(2024, 1, 15), 2000.0, "akash", "Education"),
                new CreditCard(new Date(2024, 1, 5), 3456.0, "akash", "Bills"),
                new CreditCard(new Date(2024, 1, 20), 300.0, "arun", "Bills"),
                new CreditCard(new Date(2024, 1, 25), 400.0, "arun", "Family"),
                new CreditCard(new Date(2024, 1, 30), 5007.0, "akash", "Emergency"),
                new CreditCard(new Date(2024, 2, 6), 350.0, "varun", "Education")
        };

        CreditTransactions creditTransactions = new CreditTransactions(transactionsDetails);
        Thread thread1 = new Thread(creditTransactions::filterDate);
        Thread thread2 = new Thread(creditTransactions::sortBeneficiary);
        Thread thread3 = new Thread(creditTransactions::sortAmount);
        Thread thread4 = new Thread(creditTransactions);
        Thread thread5 = new Thread(creditTransactions);
        Thread thread6 = new Thread(creditTransactions);
        Thread thread7 = new Thread(creditTransactions);
        Thread thread8 = new Thread(creditTransactions);
        Thread thread9 = new Thread(creditTransactions);
        Thread thread10 = new Thread(creditTransactions);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
    }

    @Override
    public void run() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("------Welcome to Transactions folder---------");
            System.out.println("1. Least amount transferred");
            System.out.println("2. Maximum amount Transferred");
            System.out.println("3. Number of transactions made to particular beneficiary");
            System.out.println("4. Filter based on particular remarks ");
            System.out.println("5. Filter Date");
            System.out.println("6. Exit");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    minAmount();
                    break;
                case 2:
                    maxAmount();
                    break;
                case 3:
                    System.out.println("Enter the name of the beneficiary: ");
                    String beneficiaryName = input.next();
                    numberOfTransactions(beneficiaryName);
                    break;
                case 4:
                    System.out.println("Enter the remark: ");
                    String remark = input.next();
                    filterRemarks(remark);
                    break;
                case 5:
                    filterDate();
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please choose a number from 1 to 5.");
            }
        }
    }

    public synchronized void filterDate() {
        for (CreditCard each : transactionsDetails) {
            if (each.getDateOfTransaction().after(new Date(2024, 1, 1)) && each.getDateOfTransaction().before(new Date(2024, 1, 31))) {
                System.out.println("Date of Transaction:" + each.getDateOfTransaction() +
                        "\nAmount transacted:" + each.getAmountInTransaction() +
                        "\nRecipient:" + each.getToRecipient() +
                        "\nRemarks:" + each.getRemarks() +
                        "\n-----------------------");
            }
        }
    }

    public synchronized void sortBeneficiary() {

        boolean swapped;
        int n = transactionsDetails.length;

        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (transactionsDetails[i].getToRecipient().compareTo(transactionsDetails[i + 1].getToRecipient()) < 0) {
                    CreditCard temp = transactionsDetails[i];
                    transactionsDetails[i] = transactionsDetails[i + 1];
                    transactionsDetails[i + 1] = temp;
                    swapped = true;
                }
            }
            n--;
        } while (swapped);


        System.out.println("Sorted transactions by beneficiary name in descending order:");
        for (CreditCard card : transactionsDetails) {
            System.out.println(card);
        }
    }

    public synchronized void sortAmount() {
        boolean swapped;
        int n = transactionsDetails.length;

        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (transactionsDetails[i].getAmountInTransaction().compareTo(transactionsDetails[i + 1].getAmountInTransaction()) > 0) {
                    CreditCard temp = transactionsDetails[i];
                    transactionsDetails[i] = transactionsDetails[i + 1];
                    transactionsDetails[i + 1] = temp;
                    swapped = true;
                }
            }
            n--;
        } while (swapped);


        System.out.println("Sorted transactions by amount in descending order:");
        for (CreditCard card : transactionsDetails) {
            System.out.println(card);
        }
    }

    public void filterRemarks(String remark) {
        double total = 0;
        for (CreditCard each : transactionsDetails) {
            if (each.getRemarks().equals(remark)) {
                total += each.getAmountInTransaction();
            }
        }
        System.out.println("Total amount transacted toward the remark " + remark + " is " + total);
    }

    public void minAmount() {
        String name="";
        double temp=999999;
        for(CreditCard each: transactionsDetails){
            if(each.getAmountInTransaction()<temp){
                temp=each.getAmountInTransaction();
                name=each.getToRecipient();
            }
        }
        System.out.println("Minimum amount transferred to "+name+" is: "+temp);
    }

    public void maxAmount() {
        String name="";
        double temp=0.0;
        for(CreditCard each: transactionsDetails){
            if(each.getAmountInTransaction()>temp){
                temp=each.getAmountInTransaction();
                name=each.getToRecipient();
            }
        }
        System.out.println("Maximum amount transferred to "+name+" is: "+temp);
    }

    public void numberOfTransactions(String name) {
        int count=0;
        for (CreditCard each: transactionsDetails){
            if(each.getToRecipient().equals(name)){
                count++;
            }
        }
        System.out.println("Number of times the transactions has been made to "+name+" is "+count);
    }
}
