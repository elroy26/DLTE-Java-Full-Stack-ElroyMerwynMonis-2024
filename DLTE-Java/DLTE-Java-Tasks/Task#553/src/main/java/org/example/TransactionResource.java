package org.example;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class TransactionResource implements Runnable {
    Lock lock = new ReentrantLock();
    private ArrayList<CreditCard> transactionsDetails;

    public TransactionResource(ArrayList<CreditCard> transactionsDetails) {
        this.transactionsDetails = transactionsDetails;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println("------Welcome to Transaction Analysis---------");
            System.out.println("1. Filter based on date range");
            System.out.println("2. Least amount transferred");
            System.out.println("3. Maximum amount transferred");
            System.out.println("4. Number of transactions made to particular beneficiary");
            System.out.println("5. Filter based on particular remarks");
            System.out.println("6. Exit");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    filterByDateRange();
                    break;
                case 2:
                    leastAmountTransferred();
                    break;
                case 3:
                    maximumAmountTransferred();
                    break;
                case 4:
                    numberOfTransactionsByBeneficiary();
                    break;
                case 5:
                    filterByRemarks();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please choose a number from 1 to 6.");
            }
        } finally {
            lock.unlock();
        }
    }

    public void filterByDateRange() {
        List<CreditCard> filteredList = transactionsDetails.stream()
                .filter(each -> each.getDateOfTransaction().after(new Date(2024, 1, 15))
                        && each.getDateOfTransaction().before(new Date(2024, 1, 26)))
                .collect(Collectors.toList());
        System.out.println("Transactions within the given date range:");
        filteredList.forEach(System.out::println);
    }

    public void leastAmountTransferred() {
        CreditCard leastTransaction = transactionsDetails.stream()
                .min(Comparator.comparingDouble(CreditCard::getAmountInTransaction))
                .orElse(null);

        System.out.println("Least amount transferred:");
        System.out.println(leastTransaction);
    }

    public void maximumAmountTransferred() {
        CreditCard maxTransaction = transactionsDetails.stream()
                .max(Comparator.comparingDouble(CreditCard::getAmountInTransaction))
                .orElse(null);

        System.out.println("Maximum amount transferred:");
        System.out.println(maxTransaction);
    }

    public void numberOfTransactionsByBeneficiary() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the beneficiary:");
        String beneficiary = scanner.nextLine();
        long count = transactionsDetails.stream()
                .filter(card -> card.getToRecipient().equalsIgnoreCase(beneficiary))
                .count();
        System.out.println("Number of transactions made to " + beneficiary + ": " + count);
    }

    public void filterByRemarks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the remark:");
        String remark = scanner.nextLine();
        List<CreditCard> filteredList = transactionsDetails.stream()
                .filter(card -> card.getRemarks().equalsIgnoreCase(remark))
                .collect(Collectors.toList());
        System.out.println("Transactions with the remark '" + remark + "':");
        filteredList.forEach(System.out::println);
    }
}
