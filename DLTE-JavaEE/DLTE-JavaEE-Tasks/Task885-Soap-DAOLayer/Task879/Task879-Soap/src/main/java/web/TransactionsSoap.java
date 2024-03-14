package web;

import app.mybank.entity.Account;
import app.mybank.entity.Transaction;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Scanner;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TransactionsSoap {
    private TransactionService service;
    public TransactionsSoap(){
        StorageTarget storageTarget=new DatabaseTarget();
        service=new TransactionService(storageTarget);
    }

    @WebMethod
    @WebResult(name="GroupOfTransactions")
    public GroupOfTransactions readAll(@WebParam(name="StringUser") String user, @WebParam(name = "stringType") String type){
        GroupOfTransactions groupOfTransactions=new GroupOfTransactions();
        List<Transaction> transactionList=service.callFindByType(user,type);
        groupOfTransactions.setTransactions(transactionList);
        return groupOfTransactions;
    }

    @WebMethod
    @WebResult(name="GroupOfTransactions")
    public GroupOfTransactions readAllByUser(@WebParam(name="String") String user){
        GroupOfTransactions groupOfTransactions=new GroupOfTransactions();
        List<Transaction> transactionList=service.callViewTransaction(user);
        groupOfTransactions.setTransactions(transactionList);
        return groupOfTransactions;
    }
    @WebMethod
    @WebResult(name="GroupOfTransactions")
    public GroupOfTransactions readAllDateByUser(@WebParam(name="StringUser") String user,@WebParam(name="stringStartDate") String startDate, @WebParam(name="stringEndDate") String endDate){
        GroupOfTransactions groupOfTransactions=new GroupOfTransactions();
        List<Transaction> transactionList=service.callFindByDate(user,startDate,endDate);
        groupOfTransactions.setTransactions(transactionList);
        return groupOfTransactions;
    }
    @WebMethod
    @WebResult(name="GroupOfTransactions")
    public boolean login(@WebParam(name="StringUser") String user,@WebParam(name="stringPassword") String password){
        Boolean transactionList=service.verifyAccount(user,password);
        return transactionList;
    }
    @WebMethod
    public void createAccount(@WebParam (name="CreateAccount")Account account){
        service.callSaveAccount(account);
    }

}
