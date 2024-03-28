package com.example.soapendpoints;

import com.example.soapendpoints.configs.SoapPhase;
import com.example.soapendpoints.dao.TransactionService;
import com.example.soapendpoints.dao.Transactions;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import services.transactions.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SoapendpointsApplicationTests {

    @MockBean
    private TransactionService transactionService;

    @InjectMocks
    private SoapPhase soapPhase;

    @Test
    public void testFilterAmount(){
        List<Transactions> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transactions(1L, new Date(), "Sender", "Receiver", 1000.0, "Test"));
        when(transactionService.findByAmount(1000.0)).thenReturn(mockTransactions);

        FindByAmountRequest request = new FindByAmountRequest();

//        request.setAmount(1000.0);
        request.setAmount(2000.0);

        FindByAmountResponse response = soapPhase.findByAmountResponse(request);

        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transactions available", response.getServiceStatus().getMessage());
        assertEquals(1, response.getTransactions().size());

    }
    @Test
    public void testFilterSender(){

        List<com.example.soapendpoints.dao.Transactions> mockTransactions = new ArrayList<>();
        mockTransactions.add(new com.example.soapendpoints.dao.Transactions(1L, new Date(), "Sender", "Receiver", 1000.0, "Test"));
        when(transactionService.findBySender("Sender")).thenReturn(mockTransactions);

        FindBySenderRequest request = new FindBySenderRequest();

//        request.setSentTo("Sender");
        request.setSentTo("Sendsdfer");

        FindBySenderResponse response = soapPhase.findBySenderResponse(request);

        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transactions available", response.getServiceStatus().getMessage());
        assertEquals(1, response.getTransactions().size());

    }
    @Test
    public void testFilterReceiver(){
        List<com.example.soapendpoints.dao.Transactions> mockTransactions = new ArrayList<>();
        mockTransactions.add(new com.example.soapendpoints.dao.Transactions(1L, new Date(), "Sender", "Receiver", 1000.0, "Test"));
        when(transactionService.findByReceiver("Receiver")).thenReturn(mockTransactions);

        FindByReceiverRequest request = new FindByReceiverRequest();
//        request.setReceiver("Receiver");
        request.setReceiver("Receisadfver");

        FindByReceiverResponse response = soapPhase.findByReceiverResponse(request);

        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transactions available", response.getServiceStatus().getMessage());
        assertEquals(1, response.getTransactions().size());
    }
    @Test
    public void testAddTransaction(){

        com.example.soapendpoints.dao.Transactions mockTransaction = new com.example.soapendpoints.dao.Transactions(1L, new Date(), "Sender", "Receiver", 1000.0, "Test");
        when(transactionService.newTransaction(any(com.example.soapendpoints.dao.Transactions.class))).thenReturn(mockTransaction);

        NewTransactionRequest request = new NewTransactionRequest();
        services.transactions.Transactions actual = new services.transactions.Transactions();
        request.setTransaction(actual);

        actual.setTransactionId(1L);
        actual.setTransactionDate(XMLGregorianCalendarImpl.createDate(2024, 3, 28, 0));
        actual.setSentTo("Sender");
        actual.setReceivedBy("Receiver");
        actual.setAmount(1000.0);
        actual.setRemarks("Test");

        NewTransactionResponse response = soapPhase.newTransactionResponse(request);

        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals(1L, response.getTransaction().getTransactionId());
        assertEquals("Sender", response.getTransaction().getSentTo());
        assertEquals("Receiver", response.getTransaction().getReceivedBy());
        assertEquals(1000.0, response.getTransaction().getAmount());
        assertEquals("Test", response.getTransaction().getRemarks());
    }

    @Test
    public void testUpdatingTransaction() {
        com.example.soapendpoints.dao.Transactions updatedTransaction = new com.example.soapendpoints.dao.Transactions();

        updatedTransaction.setTransactionId(1L);
        updatedTransaction.setTransactionDate(new Date());
        updatedTransaction.setSentTo("Sender");
        updatedTransaction.setReceivedBy("Receiver");
        updatedTransaction.setAmount(1000.0);
        updatedTransaction.setRemarks("Updated remarks");
        when(transactionService.updateRemarks(any(com.example.soapendpoints.dao.Transactions.class))).thenReturn(updatedTransaction);

        UpdateRemarksRequest request = new UpdateRemarksRequest();
        services.transactions.Transactions transaction = new services.transactions.Transactions();
        transaction.setTransactionId(1L);
        transaction.setTransactionDate(XMLGregorianCalendarImpl.createDate(2024, 3, 28, 0));
        transaction.setSentTo("Sender");
        transaction.setReceivedBy("Receiver");
        transaction.setAmount(1000.0);
        transaction.setRemarks("Original remarks");
        request.setTransactions(transaction);

        UpdateRemarksResponse response = soapPhase.updatingTransaction(request);

        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("1 has been updated", response.getServiceStatus().getMessage());
        assertEquals(1L, response.getTransactions().getTransactionId());
        assertEquals("Sender", response.getTransactions().getSentTo());
        assertEquals("Receiver", response.getTransactions().getReceivedBy());
        assertEquals(1000.0, response.getTransactions().getAmount());
        assertEquals("Updated remarks", response.getTransactions().getRemarks());
    }

    @Test
    public void testRemoveTransactionBetweenDates() {
        Date startDate = Date.from(Instant.parse("2024-01-01T00:00:00Z"));
        Date endDate = Date.from(Instant.parse("2024-01-15T23:59:59Z"));
        when(transactionService.removeTransactionBetweenDates(startDate, endDate)).thenReturn("removed");

        RemoveTransactionBetweenDatesRequest request = new RemoveTransactionBetweenDatesRequest();
        XMLGregorianCalendar startCal = XMLGregorianCalendarImpl.createDateTime(2024, 1, 1, 0, 0, 0, 0, 0);
        XMLGregorianCalendar endCal = XMLGregorianCalendarImpl.createDateTime(2024, 1, 15, 23, 59, 59, 0, 0);
        request.setStartDate(startCal);
        request.setEndDate(endCal);

        RemoveTransactionBetweenDatesResponse response = soapPhase.datesResponse(request);

        assertEquals("removed", response.getServiceStatus().getStatus());
        assertEquals("removed", response.getServiceStatus().getMessage());
    }

}
