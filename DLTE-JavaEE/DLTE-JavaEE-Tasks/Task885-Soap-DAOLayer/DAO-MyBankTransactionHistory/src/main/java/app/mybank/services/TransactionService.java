package app.mybank.services;

import app.mybank.entity.Account;
import app.mybank.entity.Transaction;
import app.mybank.remotes.StorageTarget;
import app.mybank.remotes.TransactionRepository;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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

    public void callSaveAccount(Account user){
        transactionRepository.saveAccount(user);
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

    public List<Transaction> callViewTransaction(String userName) {
        return transactionRepository.viewTransaction(userName);
    }

    public List<Transaction> callFindByDate(String user,String startDate, String endDate){
        return transactionRepository.findByDate(user,startDate,endDate);
    }
    public List<Transaction> callFindByAmount(Double amount){
        return transactionRepository.findByAmount(amount);
    }
    public List<Transaction> callFindByType(String user,String type){
        return transactionRepository.findByType(user,type);
    }

    public List<Transaction> callViewAllTransaction(){return transactionRepository.viewAllTransaction();}

}
