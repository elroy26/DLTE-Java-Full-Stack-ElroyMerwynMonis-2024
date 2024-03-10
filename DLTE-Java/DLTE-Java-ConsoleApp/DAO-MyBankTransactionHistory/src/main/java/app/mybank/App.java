package app.mybank;

import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StorageTarget storageTarget= (StorageTarget) new DatabaseTarget();
    }
}
