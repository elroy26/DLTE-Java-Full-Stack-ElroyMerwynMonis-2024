package com.example.hibernet.services;

import com.example.hibernet.model.ViewTransaction;
import com.example.hibernet.remotes.JpaTransactionRepository;
import com.example.hibernet.remotes.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ViewTransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    JpaTransactionRepository jpaTransactionRepository;

    public ViewTransaction callSave(ViewTransaction transaction){
        return transactionRepository.save(transaction);
    }

    public List<ViewTransaction> callFindAllByRangeOfTransactionAmount(Double amount1,Double amount2){
        return jpaTransactionRepository.lookForAmountRange(amount1,amount2);
    }
    public List<ViewTransaction> callFindAllByTypeOfTransaction(String user,String type){
        return jpaTransactionRepository.lookForUserAndType(user,type);
    }
}
