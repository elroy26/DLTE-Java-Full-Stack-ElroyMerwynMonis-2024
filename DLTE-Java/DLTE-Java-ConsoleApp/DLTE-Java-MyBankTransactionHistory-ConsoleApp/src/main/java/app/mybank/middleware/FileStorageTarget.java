package app.mybank.middleware;
import app.mybank.remotes.TransactionRepository;
import app.mybank.remotes.StorageTarget;
public class FileStorageTarget implements StorageTarget {
    @Override
    public TransactionRepository getTransactionRepository() {
        return new TransactionFileRepository("transactions.txt");
    }
}
