package com.example.autowire;

import java.util.ArrayList;
import java.util.List;

public interface LoansInterface {
    default List<Loans> initializeMyLoans() {
        List<Loans> myLoans = new ArrayList<>();
        myLoans.add(new Loans(12341324L, 13244.0, "12/02/2024", "personal Loan", "elroy", 12341324L));
        myLoans.add(new Loans(12351324L, 1324324.0, "12/03/2024", "home loan", "elroy", 12341324L));
        myLoans.add(new Loans(1841324L, 132432524.0, "12/04/2024", "personal loan", "elroy", 12432324L));
        myLoans.add(new Loans(14571324L, 13233334.0, "12/05/2024", "home loan", "elroy", 121341324L));
        return myLoans;
    }
    List<Loans> findAllLoans();
}
