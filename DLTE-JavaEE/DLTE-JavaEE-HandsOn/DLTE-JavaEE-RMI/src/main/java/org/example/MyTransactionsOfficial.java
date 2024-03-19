package org.example;

import app.mybank.entity.Transaction;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;

public class MyTransactionsOfficial {
    public static void main(String[] args) throws NamingException, RemoteException {
        Hashtable properties=new Hashtable();
        properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
        properties.put(Context.PROVIDER_URL,"rmi://localhost:3030");
        Context context=new InitialContext(properties);
        MyTransactions myCardServer = (MyTransactions) context.lookup("java:/type-filter");
        List<Transaction> transactionList= myCardServer.fetchByType("elroy","transfer");
        System.out.println(transactionList);
    }
}
