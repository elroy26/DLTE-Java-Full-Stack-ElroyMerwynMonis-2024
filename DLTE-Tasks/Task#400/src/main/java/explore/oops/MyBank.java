package explore.oops;

import java.util.Date;

public interface MyBank {
    //loanNumber, loanAmount, loanDate, loanStatus(Open/Closed), borrowerName, borrowerContact;

    void addNewLoan();
    void availableLoans();
    void closedLoans();
}
