package com.example.autowire;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("HomeLoanOperation")
public class HomeLoanImplementation implements LoansInterface {
    @Override
    public List<Loans> findAllLoans() {
        List<Loans> allLoans = initializeMyLoans();

        // Filter loans with "home loan" status
        List<Loans> homeLoans = allLoans.stream()
                .filter(loan -> "home loan".equalsIgnoreCase(loan.getLoanStatus()))
                .collect(Collectors.toList());

        return homeLoans;
    }
}
