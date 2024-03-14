package web;

import app.mybank.entity.Transaction;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;

import javax.jws.WebMethod;
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
    public GroupOfTransactions readAll(){
        GroupOfTransactions groupOfTransactions=new GroupOfTransactions();
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter the username");
        String user = scanner.next();
        System.out.println("enter the Transaction type");
        String type =scanner.next();


        List<Transaction> transactionList=service.callFindByType(user,type);
        groupOfTransactions.setTransactions(transactionList);
        return groupOfTransactions;
    }
}
