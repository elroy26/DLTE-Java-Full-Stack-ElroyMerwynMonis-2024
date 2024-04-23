package crud;
import com.example.jdbctemplate.TransactionEntity;
import com.example.jdbctemplate.TransactionService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class TransactionCrud {
    private TransactionService service;
    private TransactionEntity transactionEntity = new TransactionEntity();
    private List<TransactionEntity> filteredTransactions;
    private String sender;
    private String receiver;
    private Double amount;

    public TransactionEntity getTransactionEntity() {
        return transactionEntity;
    }

    public void setTransactionEntity(TransactionEntity transactionEntity) {
        this.transactionEntity = transactionEntity;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void newTransaction() {
        service.newTransaction(transactionEntity);
        transactionEntity = new TransactionEntity();
    }

    public List<TransactionEntity> filterBySender() {
        filteredTransactions = service.findBySender(sender); // Update filtered transactions
        return filteredTransactions;
    }

    public List<TransactionEntity> filterByReceiver() {
        filteredTransactions = service.findByReceiver(receiver); // Update filtered transactions
        return filteredTransactions;
    }

    public List<TransactionEntity> filterByAmount() {
        filteredTransactions = service.findByAmount(amount); // Update filtered transactions
        return filteredTransactions;
    }

    public TransactionService getService() {
        return service;
    }

    public void setService(TransactionService service) {
        this.service = service;
    }

    public List<TransactionEntity> getFilteredTransactions() {
        return filteredTransactions;
    }

    public void setFilteredTransactions(List<TransactionEntity> filteredTransactions) {
        this.filteredTransactions = filteredTransactions;
    }
}
