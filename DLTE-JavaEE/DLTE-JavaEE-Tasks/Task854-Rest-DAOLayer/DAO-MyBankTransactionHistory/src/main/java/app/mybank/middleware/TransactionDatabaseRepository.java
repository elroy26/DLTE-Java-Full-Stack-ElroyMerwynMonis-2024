package app.mybank.middleware;

import app.mybank.entity.Account;
import app.mybank.entity.Transaction;
import app.mybank.remotes.TransactionRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TransactionDatabaseRepository implements TransactionRepository {
    private Connection connection;
    private List<Transaction> accountList=new ArrayList<>();
//    private Account account=new Account();
    private Transaction transaction= new Transaction();

    private ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    private Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
//    private String user;

    public TransactionDatabaseRepository(Connection connection){
        try{
            this.connection=connection;
            FileHandler fileHandler=new FileHandler("transaction-history-logs.txt",true);
            SimpleFormatter simpleFormatter=new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        }
        catch (IOException ioException){
            System.out.println(ioException);
        }
    }


    @Override
    public void saveAccount(Account user) {
        try {
            String query = "insert into Account values(?,?,?,?)";
            preparedStatement=connection.prepareStatement(query);

            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setLong(4,user.getPhoneNumber());
            preparedStatement.executeUpdate();
//            int result = preparedStatement.executeUpdate();
//            if (result!=0) {
//                logger.log(Level.INFO, resourceBundle.getString("record.push.ok"));
//                System.out.println(resourceBundle.getString("record.push.ok"));
//            }
//            else {
//                logger.log(Level.INFO, resourceBundle.getString("record.push.fail"));
//                System.out.println(resourceBundle.getString("record.push.fail"));
//            }

        } catch (SQLException sqlException) {
            System.out.println(resourceBundle.getString("account.not.ok"));
        }
    }

    @Override
    public boolean verifyAccount(String userName, String password) {
//        username VARCHAR(255) PRIMARY KEY,
//        --    password VARCHAR(255),
        try {
            String query = "SELECT count(*) FROM Account WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            // If a matching entry is found, return true; otherwise, return false
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
//                user=userName;
                return count > 0;
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return false;
    }

//    @Override
//    public void addAccount() {
//
//    }

    @Override
    public List<Transaction> viewTransaction(String userName) {
        try{
            accountList.clear();
            String query="select * from TransactionHistory where nameuser=?";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while(resultSet.next()){
                transaction=new Transaction();
                transaction.setUserName(resultSet.getString(1));
                transaction.setTransactionType(resultSet.getString(2));
                transaction.setTransactionAmount(resultSet.getDouble(3));
                transaction.setTransactionDate(resultSet.getDate(4));
                accountList.add(transaction);
            }
            System.out.println(accountList);
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return accountList;

    }

    @Override
    public List<Transaction> findByDate(String user,String startDate, String endDate) {
        try{
            accountList.clear();
            String query="SELECT * FROM TransactionHistory WHERE nameuser=? AND transaction_date BETWEEN TO_DATE(?, 'MM/DD/YYYY') AND TO_DATE(?, 'MM/DD/YYYY')";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,startDate);
            preparedStatement.setString(3,endDate);

            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while(resultSet.next()){
                transaction=new Transaction();
                transaction.setUserName(resultSet.getString(1));
                transaction.setTransactionType(resultSet.getString(2));
                transaction.setTransactionAmount(resultSet.getDouble(3));
                transaction.setTransactionDate(resultSet.getDate(4));
                accountList.add(transaction);
            }
            System.out.println(accountList);
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return accountList;
    }

    @Override
    public List<Transaction> findByAmount(Double amount) {

        try{
            accountList.clear();
            String query="select * from TransactionHistory where nameuser=? and amount > ?";
            preparedStatement=connection.prepareStatement(query);
//            preparedStatement.setString(1,user);
            preparedStatement.setDouble(2,amount);


            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while(resultSet.next()){
                transaction=new Transaction();
                transaction.setUserName(resultSet.getString(1));
                transaction.setTransactionType(resultSet.getString(2));
                transaction.setTransactionAmount(resultSet.getDouble(3));
                transaction.setTransactionDate(resultSet.getDate(4));
                accountList.add(transaction);
            }
            System.out.println(accountList);
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return accountList;
    }

    @Override
    public List<Transaction> findByType(String user, String type) {
        try{
            accountList.clear();
            String query="select * from TransactionHistory where nameuser=? and transaction_type = ?";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,type);


            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while(resultSet.next()){
                transaction=new Transaction();
                transaction.setUserName(resultSet.getString(1));
                transaction.setTransactionType(resultSet.getString(2));
                transaction.setTransactionAmount(resultSet.getDouble(3));
                transaction.setTransactionDate(resultSet.getDate(4));
                accountList.add(transaction);
            }
            System.out.println(accountList);
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return accountList;
    }

    @Override
    public List<Transaction> viewAllTransaction() {
        try{
            accountList.clear();
            String query="select * from TransactionHistory ";
            preparedStatement=connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while(resultSet.next()){
                transaction=new Transaction();
                transaction.setUserName(resultSet.getString(1));
                transaction.setTransactionType(resultSet.getString(2));
                transaction.setTransactionAmount(resultSet.getDouble(3));
                transaction.setTransactionDate(resultSet.getDate(4));
                accountList.add(transaction);
            }
            System.out.println(accountList);
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return accountList;
    }
}
