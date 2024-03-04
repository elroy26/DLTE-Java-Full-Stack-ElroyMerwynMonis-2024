package app.mybank.services;

import app.mybank.entity.Account;
import app.mybank.middleware.TransactionFileRepository;
import app.mybank.remotes.TransactionRepository;

import java.util.List;

public class TransactionService {
    TransactionRepository transactionRepository;
    public TransactionService(){
        transactionRepository=new TransactionFileRepository("transactions.txt");
    }
    public List<Account> callFindAllByAccount(String userName,String password){
        return transactionRepository.findAllByAccount(userName, password);
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
}
