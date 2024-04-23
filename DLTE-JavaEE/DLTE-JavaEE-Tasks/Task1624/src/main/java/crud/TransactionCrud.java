package crud;

import com.example.jdbctemplate.TransactionEntity;
import com.example.jdbctemplate.TransactionService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class TransactionCrud {
    private TransactionService service;
    private TransactionEntity transactionEntity = new TransactionEntity();
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
        return service.findBySender(sender);
    }

    public List<TransactionEntity> filterByReceiver() {
        return service.findByReceiver(receiver);
    }

    public List<TransactionEntity> filterByAmount() {
        return service.findByAmount(amount);
    }

    public TransactionService getService() {
        return service;
    }

    public void setService(TransactionService service) {
        this.service = service;
    }
}
