package org.example;

import app.mybank.entity.Transaction;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.TreeMap;

public interface MyTransactions extends Remote  {
    List<Transaction> fetchByType(String user, String type) throws RemoteException;

}
