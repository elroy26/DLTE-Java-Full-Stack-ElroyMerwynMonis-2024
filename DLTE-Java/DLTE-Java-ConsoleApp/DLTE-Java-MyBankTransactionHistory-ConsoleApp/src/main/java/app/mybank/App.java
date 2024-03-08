package app.mybank;

import app.mybank.entity.Account;
import app.mybank.exceptions.TransactionHistoryException;
import app.mybank.middleware.TransactionFileRepository;
import app.mybank.remotes.TransactionRepository;
import app.mybank.services.TransactionService;

import java.util.*;
import java.util.regex.Pattern;

import static java.lang.System.exit;

public class App {
    private static TransactionService transactionService = new TransactionService();
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private static ResourceBundle resourceBundleTransaction = ResourceBundle.getBundle("transactionHistory");
    private static String userName;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        String choice1 = "", choice2 = "", choice3 = "", choice4 = "";
        int attempts = 0;
        final int maxAttempts = 3;
        boolean loggedIn = false;
        System.out.println(resourceBundle.getString("app.greet"));
        while (attempts < maxAttempts && !loggedIn) {
            try {
                System.out.println(resourceBundle.getString("app.log.name"));
                userName = scanner.nextLine();
                System.out.println(resourceBundle.getString("app.log.password"));
                String password = scanner.next();
                scanner.nextLine();

                if (transactionService.verifyAccount(userName, password)) {
                    loggedIn = true;
                } else {
                    attempts++;
                    int attemptsLeft = maxAttempts - attempts;
                    if (attempts >= 3) {
                        System.out.println(resourceBundle.getString("app.log.suspend"));
                        exit(0);
                    } else {
                        System.out.println(resourceBundle.getString("app.login.fail") + attemptsLeft + resourceBundle.getString("app.login.attempts"));
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("app.exception.input");
                scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("app.exception.noElement");
                scanner.nextLine();
            }
        }

        if (loggedIn) {
            while (true) {
                try {
                    System.out.println(resourceBundle.getString("app.greet.jarvis"));
                    System.out.println(resourceBundle.getString("app.jarvis.menu"));
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 4:
                            do {
                                System.out.println(resourceBundleTransaction.getString("user.transaction.greet"));
                                System.out.println(resourceBundleTransaction.getString("user.transaction.menu"));
                                option = scanner.nextInt();
                                switch (option) {
                                    case 1:
                                        transactionService.viewTransaction(userName);
                                        System.out.println(resourceBundleTransaction.getString("user.menu.continue"));
                                        choice1 = scanner.next();
                                        break;
                                    case 2:
                                        while (true) {
                                            System.out.println(resourceBundleTransaction.getString("user.transaction.date"));
                                            String startDate = scanner.next();
                                            System.out.println(resourceBundleTransaction.getString("user.transaction.date.endDate"));
                                            String endDate = scanner.next();

                                            try {
                                                transactionService.findByDate(startDate, endDate);
                                                break;
                                            } catch (TransactionHistoryException e) {
                                                System.out.println(e.getMessage()); // Print the exception message
                                                System.out.println(resourceBundleTransaction.getString("date.request"));
                                            }
                                        }
                                        System.out.println(resourceBundleTransaction.getString("user.menu.continue"));
                                        choice2 = scanner.next();
                                        break;
                                    case 3:
                                        while (true) {

                                            try {
                                                System.out.println(resourceBundleTransaction.getString("user.transaction.amount"));
                                                Double amount = scanner.nextDouble();
                                                transactionService.findByAmount(amount);
                                                break;
                                            } catch (InputMismatchException e) {
                                                System.out.println(e.getMessage());
                                                scanner.nextLine();
                                                System.out.println(resourceBundleTransaction.getString("amount.request"));
                                            }
                                        }
                                            System.out.println(resourceBundleTransaction.getString("user.menu.continue"));
                                            choice3 = scanner.next();
                                            break;

                                    case 4:
                                        while (true) {

                                            try {
                                                System.out.println(resourceBundleTransaction.getString("user.transaction.type"));
                                                String type = scanner.next();
                                                if (!type.matches("(?i)^(Withdrawal|Deposit|Transfer)$")) {
                                                    throw new TransactionHistoryException(resourceBundleTransaction.getString("exception.type"));
                                                }
                                                transactionService.findByType(type);
                                                break;
                                            } catch (InputMismatchException e) {
                                                System.out.println(e.getMessage());
                                                scanner.nextLine();
                                                System.out.println(resourceBundleTransaction.getString("exception.type"));

                                            }catch (TransactionHistoryException e) {
                                                System.out.println(e.getMessage());
                                                System.out.println(resourceBundleTransaction.getString("type.request"));
                                            }
                                        }
                                            System.out.println(resourceBundleTransaction.getString("user.menu.continue"));
                                            choice4 = scanner.next();
                                            break;


                                    default:
                                        System.out.println(resourceBundle.getString("app.jarvis.choice"));
                                }
                            } while (choice1.equalsIgnoreCase("yes") || choice2.equalsIgnoreCase("yes") || choice3.equalsIgnoreCase("yes") || choice4.equalsIgnoreCase("yes"));
                            break;
                        case 5:
                            System.out.println(resourceBundle.getString("app.exit"));
                            exit(0);
                        default:
                            System.out.println(resourceBundle.getString("app.jarvis.choice"));

                    }
                } catch (InputMismatchException e) {
                    System.out.println(resourceBundle.getString("app.exception.input"));
                    scanner.nextLine();
                } catch (NoSuchElementException e) {
                    System.out.println(resourceBundle.getString("app.exception.noElement"));
                    scanner.nextLine();
                }
            }
        }
    }
}
