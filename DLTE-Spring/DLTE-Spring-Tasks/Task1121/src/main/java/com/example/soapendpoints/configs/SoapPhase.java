package com.example.soapendpoints.configs;

import com.example.soapendpoints.dao.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.transactions.*;
//import services.transactions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class SoapPhase {
    private final String url="http://transactions.services";

    @Autowired
    private TransactionService transactionService;
    @PayloadRoot(namespace = url,localPart = "findByAmountRequest")
    @ResponsePayload
    public FindByAmountResponse findByAmountResponse(@RequestPayload FindByAmountRequest findByAmountRequest){
        FindByAmountResponse findByAmountResponse=new FindByAmountResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<Transactions> returnTransactions = new ArrayList<>();

        List<com.example.soapendpoints.dao.Transactions> transactionsList=transactionService.findByAmount(findByAmountRequest.getAmount());

        Iterator<com.example.soapendpoints.dao.Transactions> iterator=transactionsList.iterator();
        while(iterator.hasNext()){
            Transactions currentTransactions=new Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            returnTransactions.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions available");

        findByAmountResponse.setServiceStatus(serviceStatus);
        findByAmountResponse.getTransactions().addAll(returnTransactions);

//        System.out.println(findByAmountResponse.getTransactions().toString());

        return findByAmountResponse;
    }

    @PayloadRoot(namespace = url,localPart = "findBySenderRequest")
    @ResponsePayload
    public FindBySenderResponse findBySenderResponse(@RequestPayload FindBySenderRequest findBySenderRequest){
        FindBySenderResponse findBySenderResponse=new FindBySenderResponse();
        ServiceStatus serviceStatus=new ServiceStatus();

        List<Transactions> returnTransactions = new ArrayList<>();
        List<com.example.soapendpoints.dao.Transactions> transactionsList=transactionService.findBySender(findBySenderRequest.getSentTo());

        Iterator<com.example.soapendpoints.dao.Transactions> iterator=transactionsList.iterator();
        while(iterator.hasNext()){
            Transactions currentTransactions=new Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            returnTransactions.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions available");

        findBySenderResponse.setServiceStatus(serviceStatus);
        findBySenderResponse.getTransactions().addAll(returnTransactions);

//        System.out.println(findBySenderResponse.getTransactions().toString());

        return findBySenderResponse;
    }

    @PayloadRoot(namespace = url,localPart = "findByReceiverRequest")
    @ResponsePayload
    public FindByReceiverResponse findByReceiverResponse(@RequestPayload FindByReceiverRequest findByReceiverRequest){
        FindByReceiverResponse findByReceiverResponse=new FindByReceiverResponse();
        ServiceStatus serviceStatus=new ServiceStatus();

        List<Transactions> returnTransactions = new ArrayList<>();
        List<com.example.soapendpoints.dao.Transactions> transactionsList=transactionService.findByReceiver(findByReceiverRequest.getReceiver());

        Iterator<com.example.soapendpoints.dao.Transactions> iterator=transactionsList.iterator();
        while(iterator.hasNext()){
            Transactions currentTransactions=new Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            returnTransactions.add(currentTransactions);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions available");

        findByReceiverResponse.setServiceStatus(serviceStatus);
        findByReceiverResponse.getTransactions().addAll(returnTransactions);

        System.out.println(findByReceiverResponse.getTransactions().toString());

        return findByReceiverResponse;
    }

    @PayloadRoot(namespace = url,localPart = "newTransactionRequest")
    @ResponsePayload
    public NewTransactionResponse newTransactionResponse(@RequestPayload NewTransactionRequest newTransactionRequest){
        NewTransactionResponse newTransactionResponse=new NewTransactionResponse();
        ServiceStatus serviceStatus=new ServiceStatus();

        Transactions actual=newTransactionRequest.getTransaction();
        com.example.soapendpoints.dao.Transactions daoTransactions= new com.example.soapendpoints.dao.Transactions();
        BeanUtils.copyProperties(actual,daoTransactions);
        daoTransactions=transactionService.newTransaction(daoTransactions);

        if (daoTransactions!=null){
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(daoTransactions,actual);
            newTransactionResponse.setTransaction(actual);
            serviceStatus.setMessage(actual.getTransactionId()+"Transactions added");
        }
        else {
            serviceStatus.setStatus("Failure");
            serviceStatus.setMessage(actual.getTransactionId()+"hasnt insserted");
        }

        newTransactionResponse.setServiceStatus(serviceStatus);

//        System.out.println(newTransactionResponse.getServiceStatus().toString());

        return newTransactionResponse;
    }
}
