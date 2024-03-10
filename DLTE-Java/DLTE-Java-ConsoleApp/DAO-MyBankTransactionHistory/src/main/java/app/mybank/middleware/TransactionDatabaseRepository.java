package app.mybank.middleware;

import app.mybank.entity.Account;
import app.mybank.remotes.TransactionRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TransactionDatabaseRepository implements TransactionRepository {
    private Connection connection;
    private List<Account> accountList=new ArrayList<>();

    private ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    private Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

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
    public boolean verifyAccount(String userName, String password) {
        return false;
    }

    @Override
    public void addAccount() {

    }

    @Override
    public void viewTransaction(String userName) {

    }

    @Override
    public List<Account> findByDate(String startDate, String endDate) {
        return null;
    }

    @Override
    public List<Account> findByAmount(Double amount) {
        return null;
    }

    @Override
    public List<Account> findByType(String type) {
        return null;
    }
}
