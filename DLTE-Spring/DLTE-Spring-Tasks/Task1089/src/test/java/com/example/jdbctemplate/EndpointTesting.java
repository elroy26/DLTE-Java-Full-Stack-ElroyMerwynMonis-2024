package com.example.jdbctemplate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EndpointTesting {

    @MockBean
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testNewTransaction() throws Exception {
        String requestJson = "{\"transactionId\":1234256,\"transactionDate\":\"2024-04-12\",\"sentTo\":\"ramahesh\",\"receivedBy\":\"elroy\",\"amount\":2000.0,\"remarks\":\"water\"}";

        when(transactionService.newTransaction(any(TransactionEntity.class))).thenReturn(new TransactionEntity());

        mockMvc.perform(post("/transact/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    void testFetchAllBySender() throws Exception {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(1L);
        transactionEntity.setAmount(100.0);
        transactionEntity.setReceivedBy("elroy");
        transactionEntity.setRemarks("Test");
        transactionEntity.setSentTo("ramahesh");

        List<TransactionEntity> expectedTransactions = Collections.singletonList(transactionEntity);

        when(transactionService.findBySender(anyString())).thenReturn(expectedTransactions);

        mockMvc.perform(get("/transact/viewBySender/{sender}", "ramahesh"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transactionId").value(1L))
                .andExpect(jsonPath("$[0].amount").value(100.0))
                .andExpect(jsonPath("$[0].receivedBy").value("elroy"))
                .andExpect(jsonPath("$[0].remarks").value("Test"))
                .andExpect(jsonPath("$[0].sentTo").value("ramahesh"));
    }

    @Test
    void testFetchAllByReceiver() throws Exception {

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(1L);
        transactionEntity.setAmount(100.0);
        transactionEntity.setReceivedBy("elroy");
        transactionEntity.setRemarks("Test");
        transactionEntity.setSentTo("ramahesh");

        List<TransactionEntity> expectedTransactions = Collections.singletonList(transactionEntity);

        when(transactionService.findByReceiver(anyString())).thenReturn(expectedTransactions);

        mockMvc.perform(get("/transact/viewByReceiver/{receiver}", "elroy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transactionId").value(1L))
                .andExpect(jsonPath("$[0].amount").value(100.0))
                .andExpect(jsonPath("$[0].receivedBy").value("elroy"))
                .andExpect(jsonPath("$[0].remarks").value("Test"))
                .andExpect(jsonPath("$[0].sentTo").value("ramahesh"));    }

    @Test
    void testFetchAllByAmount() throws Exception {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(1L);
        transactionEntity.setAmount(100.0);
        transactionEntity.setReceivedBy("elroy");
        transactionEntity.setRemarks("Test");
        transactionEntity.setSentTo("ramahesh");

        List<TransactionEntity> expectedTransactions = Collections.singletonList(transactionEntity);

        when(transactionService.findByAmount(anyDouble())).thenReturn(expectedTransactions);

        mockMvc.perform(get("/transact/viewByAmount/{amount}", 100.0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transactionId").value(1L))
                .andExpect(jsonPath("$[0].amount").value(100.0))
                .andExpect(jsonPath("$[0].receivedBy").value("elroy"))
                .andExpect(jsonPath("$[0].remarks").value("Test"))
                .andExpect(jsonPath("$[0].sentTo").value("ramahesh"));    }

}
