package app.mybank.exceptions;

public class TransactionHistoryException extends RuntimeException {
    public TransactionHistoryException(String message) {
        super(message);
    }
}
