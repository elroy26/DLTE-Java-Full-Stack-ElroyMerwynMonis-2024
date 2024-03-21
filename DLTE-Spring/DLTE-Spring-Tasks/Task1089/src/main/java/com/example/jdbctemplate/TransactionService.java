package com.example.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Service
public class TransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TransactionEntity newTransaction(TransactionEntity transaction) {
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

    public List<TransactionEntity> findBySender(String sender) {
        return jdbcTemplate.query("SELECT * FROM transaction_entity WHERE sent_to = ?",
                new Object[]{sender},
                new TransactionMapper());
    }

    public List<TransactionEntity> findByReceiver(String receiver) {
        return jdbcTemplate.query("SELECT * FROM transaction_entity WHERE received_by = ?",
                new Object[]{receiver},
               new TransactionMapper());
    }

    public List<TransactionEntity> findByAmount(Double amount) {
        return jdbcTemplate.query("SELECT * FROM transaction_entity WHERE amount = ?",
                new Object[]{amount},
                new TransactionMapper());
    }

    private class TransactionMapper implements RowMapper<TransactionEntity>{

        @Override
        public TransactionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            TransactionEntity transactionEntity=new TransactionEntity();
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
