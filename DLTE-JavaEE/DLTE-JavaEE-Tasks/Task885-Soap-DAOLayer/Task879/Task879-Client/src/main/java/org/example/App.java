package org.example;

import web.Account;
import web.Transaction;
import web.TransactionsSoap;
import web.TransactionsSoapService;

import java.text.SimpleDateFormat;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        TransactionsSoapService transactionsSoapService=new TransactionsSoapService();
        TransactionsSoap soap=transactionsSoapService.getTransactionsSoapPort();
//        List<Transaction> transactions=soap.readAll("elroy","transfer").getTransactions();
//        List<Transaction> transactions=soap.readAllDateByUser("elroy","02/01/2023","04/03/2024").getTransactions();
//        Boolean transactions=soap.login("elroy","1234");
//
//        if (transactions != null && transactions) {
//            System.out.println("Login successful");
//        } else {
//            System.out.println("Login failed");
//        }
//        List<Transaction> transactions=soap.readAllByUser("elroy").getTransactions();
//        for (Transaction transaction : transactions) {
//            System.out.println("Transaction user name: " + transaction.getUserName());
//            System.out.println("Transaction type: " + transaction.getTransactionType());
//            System.out.println("Transaction Amount: " + transaction.getTransactionAmount());
//            System.out.println("Transaction Date:"+ transaction.getTransactionDate());
//            System.out.println("--------------------------------------------");
//      }
        Account account=new Account();
        account.setUserName("ram");
        account.setPassword("1234");
        account.setEmail("ram@123.com");
        account.setPhoneNumber(1234565432L);
        soap.createAccount(account);



    }
}
