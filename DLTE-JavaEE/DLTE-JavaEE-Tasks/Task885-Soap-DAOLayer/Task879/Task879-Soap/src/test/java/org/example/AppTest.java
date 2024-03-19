package org.example;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import app.mybank.entity.Transaction;
import app.mybank.services.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import web.GroupOfTransactions;
import web.TransactionsSoap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AppTest 
{
    @Mock
    private TransactionService transactionService;
    private TransactionsSoap transactionsSoap;
    List<Transaction> transactions = new ArrayList<>();
    @Before
    public void settingUp(){
        MockitoAnnotations.initMocks(this);
        transactionsSoap=new TransactionsSoap();
        transactionsSoap.service=transactionService;
        transactions.add(new Transaction("elroy", "deposit", 502340.0, new Date("01/13/2024")));
        transactions.add(new Transaction("ajay", "withdrawal", 202340.0, new Date("02/02/2024")));
        transactions.add(new Transaction("aman", "transfer", 202340.0, new Date("03/03/2024")));
    }

    @Test
    public void testFindAll(){
        when(transactionService.callViewAllTransaction()).thenReturn(transactions);
        GroupOfTransactions transactions=transactionsSoap.readAllTransaction();
        System.out.println(transactions.toString());
        assertNotNull(transactions);
    }

    @Test
    public void testFindAllByUsername(){
        Transaction transaction1=new Transaction("elroy", "deposit", 502340.0, new Date("01/13/2024"));
        Transaction transaction2=new Transaction("ajay", "withdrawal", 202340.0, new Date("02/02/2024"));
        Transaction transaction3=new Transaction("aman", "transfer", 202340.0, new Date("03/03/2024"));

        List<Transaction> transactionList1= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        List<Transaction> transactionList2= Stream.of(transaction1).collect(Collectors.toList());
        when(transactionService.callViewTransaction("elroy")).thenReturn(transactionList2);
        GroupOfTransactions groupOfTransactions=transactionsSoap.readAllByUser("elroy");
        assertNotNull(groupOfTransactions);
        assertEquals("elroy",groupOfTransactions.getTransactions().get(0).getUserName());
    }
    @Test
    public void testAddTransaction(){
        Transaction transaction = new Transaction("testUser", "testTransaction", 100.0, new Date("02/03/2024"));
        doNothing().when(transactionService).callSaveTransaction(transaction);
        transactionsSoap.createTransaction(transaction);
        verify(transactionService, times(1)).callSaveTransaction(transaction);

    }
}
