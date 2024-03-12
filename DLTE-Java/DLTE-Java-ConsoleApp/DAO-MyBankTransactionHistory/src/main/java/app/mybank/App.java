package app.mybank;

import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;

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
