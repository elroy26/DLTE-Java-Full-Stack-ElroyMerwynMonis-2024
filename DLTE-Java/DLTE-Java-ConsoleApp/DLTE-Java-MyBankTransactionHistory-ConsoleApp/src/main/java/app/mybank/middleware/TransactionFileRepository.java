package app.mybank.middleware;

import app.mybank.entity.Account;
import app.mybank.exceptions.TransactionHistoryException;
import app.mybank.remotes.TransactionRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

public class TransactionFileRepository implements TransactionRepository {
    private String transactionFilePath;
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static ResourceBundle resourceBundleTransaction = ResourceBundle.getBundle("transactionHistory");
    private Account account;
    private List<Account> transactionList = new ArrayList<>();

    public TransactionFileRepository(String url) {
        transactionFilePath = url;
        try {
            FileHandler fileHandler = new FileHandler("transaction-logs.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException ioException) {
            throw new TransactionHistoryException(resourceBundleTransaction.getString("middleware.fileHandler.exception") + ioException.getMessage());
        }
    }

    private List<Account> readAccountFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(transactionFilePath))) {
            transactionList = (List<Account>) objectInputStream.readObject();
            return transactionList;
        } catch (IOException | ClassNotFoundException e) {
            throw new TransactionHistoryException(resourceBundleTransaction.getString("middleware.readFile.exception") + e.getMessage());
        }
    }

    @Override
    public boolean verifyAccount(String userName, String password) {
        readAccountFile();

        account = transactionList.stream().filter(each -> each.getUserName().equals(userName)).findFirst().orElse(null);
        if (account == null) {
            return false;
        } else if (!account.getPassword().equals(password)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void viewTransaction(String userName) {
        readAccountFile();

        if (account != null) {
            for (int i = 0; i < account.getTransactions().size(); i++) {
                System.out.println(account.getTransactions().get(i));
            }
        } else {
            System.out.println(resourceBundleTransaction.getString("user.account.notOk"));
        }
    }

    @Override
    public List<Account> findByDate(String startDate, String endDate) {

        String datePattern = "\\d{2}/\\d{2}/\\d{4}";
        if (!Pattern.matches(datePattern, startDate) || !Pattern.matches(datePattern, endDate)) {
            throw new TransactionHistoryException(resourceBundleTransaction.getString("exception.date"));
        }

        if (account != null) {
            for (String transaction : account.getTransactions()) {
                String[] parts = transaction.split(",");
                Date date = new Date(parts[2]);
                if (date.after(new Date(startDate)) && date.before(new Date(endDate))) {
                    System.out.println(transaction);
                }
            }
        } else {
            System.out.println(resourceBundleTransaction.getString("user.account.notOk"));
        }
        return null;
    }

    @Override
    public List<Account> findByAmount(Double amount) {
        if (account != null) {
            for (String transaction : account.getTransactions()) {
                String[] parts = transaction.split(",");
                Double initialAmount = Double.parseDouble(parts[1]);
                if (initialAmount < amount) {
                    System.out.println(transaction);
                }
            }
        } else {
            System.out.println(resourceBundleTransaction.getString("user.account.notOk"));
        }
        return null;
    }

    @Override
    public List<Account> findByType(String type) {
        if (account != null) {
            for (String transaction : account.getTransactions()) {
                String[] parts = transaction.split(",");
                String typeTransaction = parts[0];
                if (typeTransaction.equalsIgnoreCase(type)) {
                    System.out.println(transaction);
                }
            }
        } else {
            System.out.println(resourceBundleTransaction.getString("user.account.notOk"));
        }
        return null;
    }
}
