package com.example.soapendpoints;
import com.example.soapendpoints.dao.TransactionService;
import com.example.soapendpoints.dao.Transactions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SoapServiceTesting {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TransactionService transactionService;

    private List<Transactions> getSampleTransactionEntities() {
        List<Transactions> testList = new ArrayList<>();
        Transactions transaction1 = new Transactions(123L, new Date("01/02/2024"), "elroy", "ram", 20000.0, "water");
        Transactions transaction2 = new Transactions(123L, new Date("03/23/2024"), "arjun", "sita", 40000.0, "loan");
        testList.add(transaction1);
        testList.add(transaction2);
        return testList;
    }

    @Test
    void testAddTransaction() {
        Transactions transaction1 = new Transactions(123L, new Date("01/02/2024"), "elroy", "ram", 20000.0, "water");
        Transactions transaction2 = new Transactions(123L, new Date("03/23/2024"), "arjun", "sita", 40000.0, "loan");

        when(jdbcTemplate.update(anyString(), anyLong(), anyDouble(), anyString(), anyString(), anyString(), any(Date.class))).thenReturn(1);
        Transactions result = transactionService.newTransaction(transaction1);
        assertEquals(transaction1, result);
    }

    @Test
    void testFilterSender() {
        Transactions transaction1 = new Transactions(123L, new Date("01/02/2024"), "elroy", "ram", 20000.0, "water");
        Transactions transaction2 = new Transactions(123L, new Date("03/23/2024"), "arjun", "sita", 40000.0, "loan");
        List<Transactions> expectedList = Stream.of(transaction1, transaction2).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class)))
                .thenReturn(getSampleTransactionEntities());

        List<Transactions> result = transactionService.findBySender("elroy");
        assertNotNull(result);
        assertNotEquals(expectedList, result);
    }

    //
    @Test
    void testFilterReceiver() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class)))
                .thenReturn(getSampleTransactionEntities());
        List<Transactions> result = transactionService.findByReceiver("ram");
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testFilterAmount() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class)))
                .thenReturn(getSampleTransactionEntities());

        List<Transactions> result = transactionService.findByAmount(100.0);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateRemarks() {
        Transactions transaction = new Transactions(123L, new Date("01/02/2024"), "elroy", "ram", 20000.0, "water");

        when(jdbcTemplate.update(any(String.class), any(), any())).thenReturn(1);

        Transactions result =  transactionService.updateRemarks(transaction);

        assertEquals(transaction.toString(), result.toString());


    }

    @Test
    void testRemoveTransactionBetweenDates() {
        Date startDate = new Date("01/01/2024");
        Date endDate = new Date("01/31/2024");

        when(jdbcTemplate.update(any(String.class), any(), any())).thenReturn(1);

        String result = transactionService.removeTransactionBetweenDates(startDate, endDate);

        assertEquals("removed", result);
        assertNotEquals("removed",result);
    }

}

