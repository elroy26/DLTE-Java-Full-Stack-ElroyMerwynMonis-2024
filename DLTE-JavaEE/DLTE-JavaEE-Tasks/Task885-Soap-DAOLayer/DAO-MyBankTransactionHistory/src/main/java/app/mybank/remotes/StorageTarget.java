package app.mybank.remotes;

public interface StorageTarget {
    TransactionRepository getTransactionRepository();
}
