package com.example.jdbctemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/transact")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    Logger logger= LoggerFactory.getLogger(TransactionController.class);
    //http://localhost:8081/transact/new
//    {
//        "transactionId": 1234256,
//            "transactionDate": "2024-04-12",
//            "sentTo": "ramahesh",
//            "receivedBy": "elroy",
//            "amount": 2000.0,
//            "remarks": "water"
//    }
    @PostMapping("/new")
    public TransactionEntity saved(@RequestBody TransactionEntity transactionEntity){
        try {
            return transactionService.newTransaction(transactionEntity);
        } catch (TransactionException exception) {
            logger.error("Error occurred while saving transaction: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }
//http://localhost:8081/transact/viewBySender/ram
    @GetMapping("/viewBySender/{sender}")
    public List<TransactionEntity> fetchAllBySender(@PathVariable("sender") String sender){
        try {
            return transactionService.findBySender(sender);
        } catch (TransactionException exception) {
            logger.error("Error occurred while fetching transactions by sender: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
//http://localhost:8081/transact/viewByReceiver/elroy
    @GetMapping("/viewByReceiver/{receiver}")
    public List<TransactionEntity> fetchAllByReceiver(@PathVariable("receiver") String receiver){
        try {
            return transactionService.findByReceiver(receiver);
        } catch (TransactionException exception) {
            logger.error("Error occurred while fetching transactions by receiver: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
//http://localhost:8081/transact/viewByAmount/1000
    @GetMapping("/viewByAmount/{amount}")
    public List<TransactionEntity> fetchALLByAmount(@PathVariable("amount") Double amount){
        try {
            return transactionService.findByAmount(amount);
        } catch (TransactionException exception) {
            logger.error("Error occurred while fetching transactions by amount: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}
