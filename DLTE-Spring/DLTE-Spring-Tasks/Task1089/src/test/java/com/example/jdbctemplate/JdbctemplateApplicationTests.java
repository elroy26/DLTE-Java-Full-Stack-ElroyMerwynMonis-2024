package com.example.jdbctemplate;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class JdbctemplateApplicationTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TransactionService transactionService;
    @Test
    void testAddTransaction() {
        // Create two TransactionEntity objects
        TransactionEntity transaction1 = new TransactionEntity(123L, new Date("01/02/2024"), "elroy", "ram", 20000.0,"water");
        TransactionEntity transaction2 = new TransactionEntity(123L, new Date("03/23/2024"),"arjun", "sita", 40000.0,"loan");

        // Mock the jdbcTemplate's update method to return 1, indicating success
        when(jdbcTemplate.update(anyString(), anyLong(), anyDouble(), anyString(), anyString(), anyString(), any(Date.class))).thenReturn(1);
        // Call the newTransaction method and assert the returned transaction
        TransactionEntity result = transactionService.newTransaction(transaction1);
        assertEquals(transaction1, result);
    }



    @Test
    void testFilterSender() {
        TransactionEntity transaction1 = new TransactionEntity(123L, new Date("01/02/2024"), "elroy", "ram", 20000.0,"water");
        TransactionEntity transaction2 = new TransactionEntity(123L, new Date("03/23/2024"),"arjun", "sita", 40000.0,"loan");
        List<TransactionEntity> expectedList = Stream.of(transaction1, transaction2).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class)))
                .thenReturn(getSampleTransactionEntities());

        List<TransactionEntity> result = transactionService.findBySender("elroy");
        assertNotNull(result);
        assertNotEquals(expectedList, result);
    }
//
    @Test
    void testFilterReceiver() {
        // Mock the jdbcTemplate's query method to return a list of TransactionEntities
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class)))
                .thenReturn(getSampleTransactionEntities());

        // Call the findByReceiver method and assert the returned list
        List<TransactionEntity> result = transactionService.findByReceiver("ram");
        assertNotNull(result);
        assertEquals(2,result.size());
    }

    @Test
    void testFilterAmount() {
        // Mock the jdbcTemplate's query method to return a list of TransactionEntities
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class)))
                .thenReturn(getSampleTransactionEntities());

        // Call the findByAmount method and assert the returned list
        List<TransactionEntity> result = transactionService.findByAmount(100.0);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    // Helper method to generate sample TransactionEntities
    private List<TransactionEntity> getSampleTransactionEntities() {
        List<TransactionEntity> testList=new ArrayList<>();
        TransactionEntity transaction1 = new TransactionEntity(123L, new Date("01/02/2024"), "elroy", "ram", 20000.0,"water");
        TransactionEntity transaction2 = new TransactionEntity(123L, new Date("03/23/2024"),"arjun", "sita", 40000.0,"loan");
        testList.add(transaction1);
        testList.add(transaction2);
        return testList;
    }
}
