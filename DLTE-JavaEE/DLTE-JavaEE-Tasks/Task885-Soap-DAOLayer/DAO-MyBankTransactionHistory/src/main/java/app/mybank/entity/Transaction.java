package app.mybank.entity;

import sun.util.calendar.LocalGregorianCalendar;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Transaction implements Serializable {
    private String userName;
    private String transactionType;
    private Double transactionAmount;
    private Date transactionDate;

    public Transaction(String userName, String transactionType, Double transactionAmount, Date transactionDate) {
        this.userName = userName;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }

    public Transaction() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public java.util.Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "userName='" + userName + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionDate=" +transactionDate +
                '}';
    }
}
