package file.oops;

import java.util.List;

public interface MyBank {
    List<Loan> getLoans();
    void addNewLoan(Loan loan);
    List<Loan> getAvailableLoans();
    List<Loan> getClosedLoans();
}
