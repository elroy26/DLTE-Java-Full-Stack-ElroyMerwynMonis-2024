package app.mybank.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Account implements Serializable {
    private String userName;
    private String password;
    private String email;
    private Long phoneNumber;
//    private String transactionType;
//    private Double transactionAmount;
//    private Date transactionDate;

    public Account() {
    }

    public Account(String userName, String password, String email, Long phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
//        this.transactionType = transactionType;
//        this.transactionAmount = transactionAmount;
//        this.transactionDate = transactionDate;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//    public String getTransactionType(String string) {
//        return transactionType;
//    }
//
//    public void setTransactionType(String transactionType) {
//        this.transactionType = transactionType;
//    }
//
//    public Double getTransactionAmount(double aDouble) {
//        return transactionAmount;
//    }

//    public void setTransactionAmount(Double transactionAmount) {
//        this.transactionAmount = transactionAmount;
//    }
//
//    public Date getTransactionDate(Date date) {
//        return transactionDate;
//    }
//
//    public void setTransactionDate(Date transactionDate) {
//        this.transactionDate = transactionDate;
//    }

    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                "\n"+
                '}';
    }
}
