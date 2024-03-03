package org.example;

import java.util.*;
import java.sql.Date;
import java.util.stream.Collectors;

public class CreditTransactions {
    private static List<CreditCard> transactionsDetails;

    public CreditTransactions(List<CreditCard> transactionsDetails) {
        this.transactionsDetails = transactionsDetails;
    }

    public static void main(String[] args) {
        List<CreditCard> transactionsDetails = new ArrayList<>();
        transactionsDetails.add(new CreditCard(new Date(2024, 1, 15), 2000.0, "akash", "Education"));
        transactionsDetails.add(new CreditCard(new Date(2024, 1, 5), 3456.0, "akash", "Bills"));
        transactionsDetails.add(new CreditCard(new Date(2024, 1, 20), 300.0, "arun", "Bills"));
        transactionsDetails.add(new CreditCard(new Date(2024, 1, 25), 400.0, "arun", "Family"));
        transactionsDetails.add(new CreditCard(new Date(2024, 1, 30), 5007.0, "akash", "Emergency"));
        transactionsDetails.add(new CreditCard(new Date(2024, 2, 6), 350.0, "varun", "Education"));

         CreditTransactions creditTransactions = new CreditTransactions(transactionsDetails);
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("------Welcome to Transactions folder---------");
            System.out.println("1. Filter based on range of date");
            System.out.println("2. Least amount transferred");
            System.out.println("3. Maximum amount transferred");
            System.out.println("4. Customized sort");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

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
                    customizedSort();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please choose a number from 1 to 5.");
            }
        }
    }

    public static void filterByDateRange() {
        List<CreditCard> filteredList = transactionsDetails.stream().filter(each->each.getDateOfTransaction().after(new Date(2024,1,15))&&each.getDateOfTransaction().before(new Date(2024,1,26))).collect(Collectors.toList());
        System.out.println("Transactions within the given date range:");
        filteredList.forEach(creditCard -> {
            System.out.println(creditCard.toString());
        });
    }

    public static void leastAmountTransferred() {
        CreditCard leastTransaction = transactionsDetails.stream()
                .min(Comparator.comparingDouble(CreditCard::getAmountInTransaction))
                .orElse(null);

        System.out.println("Least amount transferred:");
        System.out.println(leastTransaction);
    }

    public static void maximumAmountTransferred() {
        CreditCard maxTransaction = transactionsDetails.stream()
                .max(Comparator.comparingDouble(CreditCard::getAmountInTransaction))
                .orElse(null);

        System.out.println("Maximum amount transferred:");
        System.out.println(maxTransaction);
    }

    public static void customizedSort() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter property to sort (date, amount, recipient, remarks): ");
        String property = input.next().toLowerCase();
        System.out.println("Enter sorting order (asc, desc): ");
        String order = input.next().toLowerCase();

        Comparator<CreditCard> comparator = null;
        switch (property) {
            case "date":
                comparator = Comparator.comparing(CreditCard::getDateOfTransaction);
                break;
            case "amount":
                comparator = Comparator.comparingDouble(CreditCard::getAmountInTransaction);
                break;
            case "recipient":
                comparator = Comparator.comparing(CreditCard::getToRecipient);
                break;
            case "remarks":
                comparator = Comparator.comparing(CreditCard::getRemarks);
                break;
            default:
                System.out.println("Invalid property! Sorting by date.");
                comparator = Comparator.comparing(CreditCard::getDateOfTransaction);
        }

        if (order.equals("desc")) {
            comparator = comparator.reversed();
        }

        List<CreditCard> sortedList = transactionsDetails.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        System.out.println("Sorted transactions:");
        sortedList.forEach(System.out::println);
    }
}
