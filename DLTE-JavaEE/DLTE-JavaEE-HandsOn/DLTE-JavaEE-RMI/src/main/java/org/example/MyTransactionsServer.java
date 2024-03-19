package org.example;

import app.mybank.entity.Transaction;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.services.TransactionService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MyTransactionsServer extends UnicastRemoteObject implements MyTransactions {

    private static Context context;
    private Registry registry;
    private TransactionService service;

    public MyTransactionsServer() throws RemoteException {
        super();
        service=new TransactionService(new DatabaseTarget());
        try {
            registry= LocateRegistry.createRegistry(3030);
            Hashtable properties=new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
            properties.put(Context.PROVIDER_URL,"rmi://localhost:3030");
            context=new InitialContext(properties);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> fetchByType(String user,String type) throws RemoteException {
        List<Transaction> transations = service.callFindByType(user,type);

        return transations;
    }

    public static void main(String[] args) throws RemoteException, NamingException {
        MyTransactionsServer myTransactionsServer=new MyTransactionsServer();
        context.bind("java:/type-filter",myTransactionsServer);

    }
}
