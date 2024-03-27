package com.example.soapendpoints.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public com.example.soapendpoints.dao.Transactions newTransaction(com.example.soapendpoints.dao.Transactions transaction) {
        jdbcTemplate.update("INSERT INTO transaction_entity (transaction_id, amount, received_by, remarks, sent_to, transaction_date ) VALUES (?, ?, ?, ?, ?, ?)",
                new Object[]{
                        transaction.getTransactionId(),
                        transaction.getAmount(),
                        transaction.getReceivedBy(),
                        transaction.getRemarks(),
                        transaction.getSentTo(),
                        transaction.getTransactionDate()});
        return transaction;
    }

    public List<com.example.soapendpoints.dao.Transactions> findBySender(String sender) {
        return jdbcTemplate.query("SELECT * FROM transaction_entity WHERE sent_to = ?",
                new Object[]{sender},
                new TransactionMapper());
    }

    public List<com.example.soapendpoints.dao.Transactions> findByReceiver(String receiver) {
        return jdbcTemplate.query("SELECT * FROM transaction_entity WHERE received_by = ?",
                new Object[]{receiver},
                new TransactionMapper());
    }

    public List<com.example.soapendpoints.dao.Transactions> findByAmount(Double amount) {
        return jdbcTemplate.query("SELECT * FROM transaction_entity WHERE amount = ?",
                new Object[]{amount},
                new TransactionMapper());
    }

    public Transactions updateRemarks(Transactions transactions){
        jdbcTemplate.update("UPDATE transaction_entity SET remarks = ? WHERE transaction_id = ?",
                new Object[]{transactions.getRemarks(), transactions.getTransactionId()});
        return transactions;
    }

    public String removeTransactionBetweenDates(XMLGregorianCalendar startDate, XMLGregorianCalendar endDate) {
        int acknowledge =jdbcTemplate.update("DELETE FROM transaction_entity WHERE transaction_date BETWEEN ? AND ?",
                 new Object[]{startDate, endDate});
        if(acknowledge!=0)
            return "removed";
        else
            return "not removed";
    }


    class TransactionMapper implements RowMapper<com.example.soapendpoints.dao.Transactions> {

        @Override
        public com.example.soapendpoints.dao.Transactions mapRow(ResultSet rs, int rowNum) throws SQLException {
            com.example.soapendpoints.dao.Transactions transactionEntity=new Transactions();
            transactionEntity.setTransactionId(rs.getLong(1));
            transactionEntity.setAmount(rs.getDouble(2));
            transactionEntity.setReceivedBy(rs.getString(3));
            transactionEntity.setRemarks(rs.getString(4));
            transactionEntity.setSentTo(rs.getString(5));
            transactionEntity.setTransactionDate(rs.getDate(6));
            return transactionEntity;
        }
    }
}
