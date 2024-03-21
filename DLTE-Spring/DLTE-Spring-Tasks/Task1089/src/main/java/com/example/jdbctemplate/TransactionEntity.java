package com.example.jdbctemplate;


import java.util.Date;

public class TransactionEntity {
    //Transaction Id, transaction date, transaction by(sender), transaction to(receiver), transaction amount, transaction for(remarks)

    private Long transactionId;
    private Date transactionDate;
    private String sentTo;
    private String receivedBy;
    private Double amount;
    private String remarks;

    public TransactionEntity(Long transactionId, Date transactionDate, String sentTo, String receivedBy, Double amount, String remarks) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.sentTo = sentTo;
        this.receivedBy = receivedBy;
        this.amount = amount;
        this.remarks = remarks;
    }

    public TransactionEntity() {
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", sentTo='" + sentTo + '\'' +
                ", receivedBy='" + receivedBy + '\'' +
                ", amount=" + amount +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
