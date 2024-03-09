package org.example;

import java.util.List;
import java.util.Scanner;

public class MyBankMain {
    public static void main(String[] args) {
        MyBankInterface myBank = new MyBankExecute();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to MyBank!");
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add new loan");
            System.out.println("2. View available loans");
            System.out.println("3. View closed loans");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewLoan(myBank, scanner);
                    break;
                case 2:
                    displayLoans(myBank.getAvailableLoans(), "Available Loans");
                    break;
                case 3:
                    displayLoans(myBank.getClosedLoans(), "Closed Loans");
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addNewLoan(MyBankInterface myBank, Scanner scanner) {
        System.out.println("Enter loan details:");
        System.out.print("Loan Number: ");
        long loanNumber = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Loan Amount: ");
        double loanAmount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Loan Date: ");
        String loanDate = scanner.nextLine();
        System.out.print("Loan Status (Open/Closed): ");
        String loanStatus = scanner.nextLine();
        System.out.print("Borrower Name: ");
        String borrowerName = scanner.nextLine();
        System.out.print("Borrower Contact: ");
        long borrowerContact = scanner.nextLong();
        scanner.nextLine();
        Loans newLoan = new Loans(loanNumber, loanAmount, loanDate, loanStatus, borrowerName, borrowerContact);
        myBank.addNewLoan(newLoan);
        System.out.println("New loan added successfully!");
    }

    private static void displayLoans(List<Loans> loans, String title) {
        System.out.println("\n" + title + ":");
        if (loans.isEmpty()) {
            System.out.println("No loans found.");
        } else {
            for (Loans loan : loans) {
                System.out.println(loan);
            }
        }
    }
}
