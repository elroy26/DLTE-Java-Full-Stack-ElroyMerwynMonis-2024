package app.mybank.middleware;

import app.mybank.entity.Account;
import app.mybank.remotes.TransactionRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TransactionFileRepository implements TransactionRepository {
    private String transactionFilePath;
    private Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Account account;
    private List<Account> transactionList=new ArrayList<>();
    private List<String> dataList = new ArrayList<>();
    private List<String[]> timeFormat= new ArrayList<>();
//    public TransactionFileRepository(String url) {
//        transactionFilePath=url;
//        try{
//            FileHandler fileHandler=new FileHandler("transaction-logs.txt",true);
//            SimpleFormatter simpleFormatter=new SimpleFormatter();
//            fileHandler.setFormatter(simpleFormatter);
//            logger.addHandler(fileHandler);
//        }
//        catch (IOException ioException){}
//    }


    public void writeAccountFile() {
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(transactionFilePath,true))){
            objectOutputStream.writeObject(transactionList);
        }catch (IOException e){
            System.out.println("Error writing the"+ e.getMessage());
        }
    }

    private List<Account> readAccountFile() {
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(transactionFilePath))){
            transactionList=(List<Account>) objectInputStream.readObject();
            return transactionList;
        }catch (IOException|ClassNotFoundException e){
            System.out.println("error reading the "+e.getMessage());
        }
        return null;
    }
    @Override
    public List<Account> findAllByAccount(String userName, String password) {
        return null;
    }

    @Override
    public boolean verifyAccount(String userName, String password) {

        readAccountFile();

        account=transactionList.stream().filter(each->each.getUserName().equals(userName)).findFirst().orElse(null);
        if (account==null){
            System.out.println("User does not exit");
            return false;
        }else if (!account.getPassword().equals(password)){
            System.out.println("Password is incorrect");
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void addAccount() {
//        readAccountFile();
        //String userName, String password, String email, String phoneNumber, ArrayList<String> transactions
        ArrayList<String> transactions = new ArrayList<>();
       transactions.add("deposit,50000,03/24/2024,elroy");
        transactions.add("widrawal,60000,12/03/2023,elroy");
        transactions.add("transfer,40000,04/03/2024,elroy");
        Account account= new Account("elroy","1234","123@123.com",12413241324L,transactions);
        transactionList.add(account);
        ArrayList<String> transactions1 = new ArrayList<>();
       transactions1.add("deposit,50000,03/24/2024,arjun");
        transactions1.add("widrawal,60700,12/03/2023,arjun");
        transactions1.add("transfer,43000,04/03/2024,arjun");
        Account account1= new Account("arjun","1234","123@123.com",12413241324L,transactions1);
        transactionList.add(account1);
        ArrayList<String> transactions2 = new ArrayList<>();
       transactions2.add("deposit,50050,03/24/2024,ajay");
        transactions2.add("widrawal,60700,12/03/2023,ajay");
        transactions2.add("transfer,43300,04/03/2024,ajay");
        Account account2= new Account("ajay","1234","123@123.com",12413241324L,transactions2);
        transactionList.add(account2);
        ArrayList<String> transactions3 = new ArrayList<>();
        transactions3.add("deposit,50050,03/24/2024,aman");
        transactions3.add("widrawal,60700,12/03/2023,aman");
        transactions3.add("transfer,43300,04/03/2024,aman");
        Account account3= new Account("aman","1234","123@123.com",12413241324L,transactions3);
        transactionList.add(account3);
        writeAccountFile();


    }

    @Override
    public void viewTransaction(String userName) {
        readAccountFile();
//        account=transactionList.stream().filter(each->each.getUserName().equals(userName)).findFirst().orElse(null);

        if(account!=null){
            for(int i=0;i<account.getTransactions().size();i++){
                System.out.println(account.getTransactions().get(i));

            }

        }else {
            System.out.println("null");
        }



    }

    @Override
    public List<Account> findByDate(String startDate, String endDate) {

        if (account != null) {
            for (String transaction : account.getTransactions()) {
                String[] parts = transaction.split(",");
                Date date = new Date(parts[2]);
                if (date.after(new Date(startDate)) && date.before(new Date(endDate))) {
                    System.out.println(transaction);
                }
            }
        } else {
            System.out.println("User account not found.");
        }
        return null;
    }

    @Override
    public List<Account> findByAmount(Double amount) {
        if (account != null) {
            for (String transaction : account.getTransactions()) {
                String[] parts = transaction.split(",");
                Double initialAmount=Double.parseDouble(parts[1]);
                if (initialAmount<amount) {
                    System.out.println(transaction);
                }
            }
        } else {
            System.out.println("User account not found.");
        }
        return null;
    }

    @Override
    public List<Account> findByAmount(Double initialAmount, Double finalAmount) {
        return null;
    }

    @Override
    public List<Account> findByType(String type) {
        if (account != null) {
            for (String transaction : account.getTransactions()) {
                String[] parts = transaction.split(",");
                String typeTransaction=parts[0];
                if (typeTransaction.equalsIgnoreCase(type)) {
                    System.out.println(transaction);
                }
            }
        } else {
            System.out.println("User account not found.");
        }
        return null;

    }
}
