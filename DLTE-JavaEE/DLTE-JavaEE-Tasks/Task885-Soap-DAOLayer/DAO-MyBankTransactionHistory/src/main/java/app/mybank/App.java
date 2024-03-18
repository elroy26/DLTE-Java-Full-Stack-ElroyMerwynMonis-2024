package app.mybank;

import app.mybank.entity.Transaction;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StorageTarget storageTarget=  new DatabaseTarget();
        TransactionService transactionService = new TransactionService(storageTarget);

    }
}
