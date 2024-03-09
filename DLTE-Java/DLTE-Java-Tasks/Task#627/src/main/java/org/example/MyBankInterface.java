package org.example;

import java.util.List;

public interface MyBankInterface {
    List<Loans> getLoans();
    void addNewLoan(Loans loan);
    List<Loans> getAvailableLoans();
    List<Loans> getClosedLoans();
}
