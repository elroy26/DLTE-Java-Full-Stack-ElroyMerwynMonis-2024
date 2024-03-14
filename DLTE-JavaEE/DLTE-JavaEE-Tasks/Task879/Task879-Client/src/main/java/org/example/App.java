package org.example;

import web.Transaction;
import web.TransactionsSoap;
import web.TransactionsSoapService;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        TransactionsSoapService transactionsSoapService=new TransactionsSoapService();
        TransactionsSoap soap=transactionsSoapService.getTransactionsSoapPort();
        List<Transaction> transactions=soap.readAll().getTransactions();
        System.out.println(transactions);

    }
}
