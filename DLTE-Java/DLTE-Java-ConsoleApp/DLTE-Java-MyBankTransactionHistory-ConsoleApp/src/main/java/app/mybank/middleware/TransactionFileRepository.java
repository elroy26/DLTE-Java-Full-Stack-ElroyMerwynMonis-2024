package app.mybank.middleware;

import app.mybank.entity.Account;
import app.mybank.exceptions.TransactionHistoryException;
import app.mybank.remotes.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
//import java.util.logging.FileHandler;
//import java.util.logging.Logger;
//import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

public class TransactionFileRepository implements TransactionRepository {
    private String transactionFilePath;
//    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Logger logger = LoggerFactory.getLogger(TransactionFileRepository.class);

    private static ResourceBundle resourceBundleTransaction = ResourceBundle.getBundle("transactionHistory");
    private Account account;
    private List<Account> transactionList = new ArrayList<>();

    public TransactionFileRepository(String url) {
        transactionFilePath = url;
//        try {
//            FileHandler fileHandler = new FileHandler("transaction-logs.txt", true);
//            SimpleFormatter simpleFormatter = new SimpleFormatter();
//            fileHandler.setFormatter(simpleFormatter);
//            logger.addHandler(fileHandler);
//        } catch (IOException ioException) {
//            throw new TransactionHistoryException(resourceBundleTransaction.getString("middleware.fileHandler.exception") + ioException.getMessage());
//        }
    }

    private List<Account> readAccountFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(transactionFilePath))) {
            transactionList = (List<Account>) objectInputStream.readObject();
            return transactionList;
        } catch (IOException | ClassNotFoundException e) {
//            logger.info(resourceBundleTransaction.getString("middleware.readFile.exception"));
            logger.error(resourceBundleTransaction.getString("middleware.readFile.exception"));
            throw new TransactionHistoryException(resourceBundleTransaction.getString("middleware.readFile.exception") + e.getMessage());
        }
    }
    public void writeAccountFile() {
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(transactionFilePath))){
            objectOutputStream.writeObject(transactionList);
        }catch (IOException e){

            System.out.println("Error writing the"+ e.getMessage());
        }
    }

    public void addAccount() {
//        readAccountFile();
        //String userName, String password, String email, String phoneNumber, ArrayList<String> transactions
        ArrayList<String> transactions = new ArrayList<>();
        transactions.add("deposit,50000,03/24/2024,elroy");
        transactions.add("withdrawal,60000,12/03/2023,elroy");
        transactions.add("transfer,40000,04/03/2024,elroy");
        Account account= new Account("elroy","1234","elroy@gmail.com",9613241324L,transactions);
        transactionList.add(account);
        ArrayList<String> transactions1 = new ArrayList<>();
        transactions1.add("deposit,50000,03/24/2024,arjun");
        transactions1.add("transfer,43000,04/03/2024,arjun");
        transactions1.add("withdrawal,60700,12/03/2023,arjun");
        Account account1= new Account("arjun","1234","arjun@gmail.com",9903241324L,transactions1);
        transactionList.add(account1);
        ArrayList<String> transactions2 = new ArrayList<>();
        transactions2.add("deposit,50050,03/24/2024,ajay");
        transactions2.add("withdrawal,60700,12/03/2023,ajay");
        transactions2.add("transfer,43300,04/03/2024,ajay");
        Account account2= new Account("ajay","1234","ajay@gmail.com",8903241324L,transactions2);
        transactionList.add(account2);
        ArrayList<String> transactions3 = new ArrayList<>();
        transactions3.add("deposit,50050,03/24/2024,aman");
        transactions3.add("transfer,43300,04/03/2024,aman");
        transactions3.add("withdrawal,60700,12/03/2023,aman");
        Account account3= new Account("aman","1234","aman@gmail.com",9873241324L,transactions3);
        transactionList.add(account3);
        writeAccountFile();


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
//            logger.info(resourceBundleTransaction.getString("user.account.notOk"));
            logger.warn(resourceBundleTransaction.getString("user.account.notOk"));
            System.out.println(resourceBundleTransaction.getString("user.account.notOk"));
        }
    }

    @Override
    public List<Account> findByDate(String startDate, String endDate) {

        String datePattern = "\\d{2}/\\d{2}/\\d{4}";
        if (!Pattern.matches(datePattern, startDate) || !Pattern.matches(datePattern, endDate)) {
            logger.error(resourceBundleTransaction.getString("exception.date"));
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
            logger.info(resourceBundleTransaction.getString("user.account.notOk"));
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
            logger.info(resourceBundleTransaction.getString("user.account.notOk"));
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
            logger.warn(resourceBundleTransaction.getString("user.account.notOk"));
            System.out.println(resourceBundleTransaction.getString("user.account.notOk"));
        }
        return null;
    }
}
