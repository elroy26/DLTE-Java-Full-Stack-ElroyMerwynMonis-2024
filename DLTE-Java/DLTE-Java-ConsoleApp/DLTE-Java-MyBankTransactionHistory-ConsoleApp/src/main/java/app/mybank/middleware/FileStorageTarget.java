package app.mybank.middleware;

import app.mybank.remotes.StorageTarget;
import app.mybank.remotes.TransactionRepository;

public class FileStorageTarget implements StorageTarget {
    @Override
    public TransactionRepository getTransactionRepository() {
        return null;
    }
}
