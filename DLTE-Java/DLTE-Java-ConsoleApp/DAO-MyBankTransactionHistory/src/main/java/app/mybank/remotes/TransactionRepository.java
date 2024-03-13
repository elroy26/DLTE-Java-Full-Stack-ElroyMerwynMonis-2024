package app.mybank.remotes;

import app.mybank.entity.Transaction;

import java.util.List;

public interface TransactionRepository {
    //    List<Account> findAllByAccount(String userName,String password);
    boolean verifyAccount(String userName, String password);
    void viewTransaction(String userName);
    List<Transaction> findByDate(String startDate, String endDate);
    List<Transaction> findByAmount(Double amount);
    List<Transaction> findByType(String type);
    List<Transaction> viewAllTransaction();

}
