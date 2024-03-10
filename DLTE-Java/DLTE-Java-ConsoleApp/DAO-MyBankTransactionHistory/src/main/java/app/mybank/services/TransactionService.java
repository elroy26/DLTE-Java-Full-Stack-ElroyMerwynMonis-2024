package app.mybank.services;

import app.mybank.entity.Account;
import app.mybank.remotes.StorageTarget;
import app.mybank.remotes.TransactionRepository;

import java.util.List;
import java.util.ResourceBundle;

public class TransactionService {
    TransactionRepository transactionRepository;
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
//    public TransactionService(){
//        transactionRepository=new TransactionFileRepository("transactions.txt");
//    }
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
    public void addAccount(){
        try{
            transactionRepository.addAccount();
        }catch (Exception e){

        }
    }

    public void viewTransaction(String userName) {
        try{
            transactionRepository.viewTransaction(userName);
        }catch (Exception e){

        }
    }

    public List<Account> findByDate(String startDate,String endDate){
        return transactionRepository.findByDate(startDate,endDate);
    }
    public List<Account> findByAmount(Double amount){
        return transactionRepository.findByAmount(amount);
    }
    public List<Account> findByType(String type){
        return transactionRepository.findByType(type);
    }

}
