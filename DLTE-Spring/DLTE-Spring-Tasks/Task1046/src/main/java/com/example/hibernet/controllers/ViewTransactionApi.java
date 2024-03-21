package com.example.hibernet.controllers;

import com.example.hibernet.model.ViewTransaction;
import com.example.hibernet.services.ViewTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class ViewTransactionApi {
    @Autowired
    ViewTransactionService transactionService;

//    http://localhost:8085/transaction/
//<viewTransaction>
//    <userName>weam</userName>
//    <transactionAmount>65500</transactionAmount>
//    <transactionDate>2023-09-06 </transactionDate>
//    <transactionType>deposit</transactionType>
//</viewTransaction>
    @PostMapping(value="/", consumes="application/xml")
    public ViewTransaction apiSave(@RequestBody ViewTransaction viewTransaction){
        return transactionService.callSave(viewTransaction);
    }
    //http://localhost:8085/transaction/10000/50000
    @GetMapping("/{amount1}/{amount2}")
    public List<ViewTransaction> apiAmountRange(@PathVariable("amount1") Double amount1,@PathVariable("amount2") Double amount2){
        return transactionService.callFindAllByRangeOfTransactionAmount(amount1,amount2);
    }
//    http://localhost:8085/transaction/filter/ram/deposit
    @GetMapping("/filter/{user}/{type}")
    public List<ViewTransaction> apiType(@PathVariable("user") String user,@PathVariable("type") String type){
        return transactionService.callFindAllByTypeOfTransaction(user, type);
    }
}
