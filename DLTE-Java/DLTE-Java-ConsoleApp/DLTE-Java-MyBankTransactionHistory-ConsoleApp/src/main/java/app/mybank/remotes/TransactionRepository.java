package app.mybank.remotes;

import app.mybank.entity.Account;

import java.util.List;

public interface TransactionRepository {
    List<Account> findAllByAccount(String userName,String password);
    boolean verifyAccount(String userName, String password);
    void addAccount();
    void viewTransaction(String userName);
    List<Account> findByDate(String startDate,String endDate);
    List<Account> findByAmount(Double amount);
    List<Account> findByAmount(Double initialAmount,Double finalAmount);
    List<Account> findByType(String type);

}
