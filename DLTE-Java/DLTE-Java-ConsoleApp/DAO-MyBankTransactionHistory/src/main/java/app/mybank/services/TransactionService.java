package app.mybank.services;

import app.mybank.entity.Account;
import app.mybank.entity.Transaction;
import app.mybank.remotes.StorageTarget;
import app.mybank.remotes.TransactionRepository;

import java.util.List;
import java.util.ResourceBundle;

public class TransactionService {
    TransactionRepository transactionRepository;
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("database");

//    //    public List<Account> callFindAllByAccount(String userName,String password){
////        return transactionRepository.findAllByAccount(userName, password);
////    }
    public TransactionService(StorageTarget storageTarget){
        transactionRepository=storageTarget.getTransactionRepository();
    }
    public boolean verifyAccount(String userName, String password) {
        try {
            return transactionRepository.verifyAccount(userName, password);
        } catch (Exception e) {

        }
        return false;
    }
//    public void addAccount(){
//        try{
//            transactionRepository.addAccount();
//        }catch (Exception e){
//
//        }
//    }

    public void callViewTransaction(String userName) {
      transactionRepository.viewTransaction(userName);
    }

    public List<Transaction> callFindByDate(String startDate, String endDate){
        return transactionRepository.findByDate(startDate,endDate);
    }
    public List<Transaction> callFindByAmount(Double amount){
        return transactionRepository.findByAmount(amount);
    }
    public List<Transaction> callFindByType(String type){
        return transactionRepository.findByType(type);
    }
//    public List<Transaction> callViewAllTransaction(){return transactionRepository.viewAllTransaction();}

}
