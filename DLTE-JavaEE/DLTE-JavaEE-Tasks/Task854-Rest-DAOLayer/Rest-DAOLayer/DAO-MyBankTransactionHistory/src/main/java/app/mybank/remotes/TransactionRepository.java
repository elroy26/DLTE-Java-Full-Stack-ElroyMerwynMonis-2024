package app.mybank.remotes;

import app.mybank.entity.Account;
import app.mybank.entity.Transaction;

import java.util.List;

public interface TransactionRepository {
    //    List<Account> findAllByAccount(String userName,String password);
    void saveAccount(Account user);
    boolean verifyAccount(String userName, String password);
    List<Transaction> viewTransaction(String userName);
    List<Transaction> findByDate(String user,String startDate, String endDate);
    List<Transaction> findByAmount(Double amount);
    List<Transaction> findByType(String user, String type);
    List<Transaction> viewAllTransaction();

}
