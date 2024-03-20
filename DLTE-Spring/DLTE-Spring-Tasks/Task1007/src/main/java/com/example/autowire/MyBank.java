package com.example.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBank {
    @Autowired
    @Qualifier("HomeLoanOperation")
    private LoansInterface loansInterface;

    public List<Loans> execute() {
        List<Loans> homeLoans = loansInterface.findAllLoans();
        return homeLoans;
    }
}
