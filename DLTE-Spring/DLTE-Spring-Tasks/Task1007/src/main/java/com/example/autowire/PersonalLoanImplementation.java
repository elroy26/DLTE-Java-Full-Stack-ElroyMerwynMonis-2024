package com.example.autowire;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("PersonalLoanOperation")
public class PersonalLoanImplementation implements LoansInterface {
    @Override
    public List<Loans> findAllLoans() {

        List<Loans> allLoans = initializeMyLoans();

        // Filter loans with "personal loan" status
        List<Loans> personalLoans = allLoans.stream()
                .filter(loan -> "personal loan".equalsIgnoreCase(loan.getLoanStatus()))
                .collect(Collectors.toList());

        return personalLoans;
    }
}
