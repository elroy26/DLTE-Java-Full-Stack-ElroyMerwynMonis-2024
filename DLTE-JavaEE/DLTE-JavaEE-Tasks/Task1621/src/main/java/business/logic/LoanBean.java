package business.logic;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class LoanBean {
    @ManagedProperty("#{loanService}")
    private LoanService loanService;

    private Loans newLoan;
    private Long loanNumberToDelete;

    // Constructor (optional)
    public LoanBean() {
        newLoan = new Loans();
    }

    public void addNewLoan() {
        try {
            loanService.addLoan(newLoan);
            // Reset newLoan for next entry
            newLoan = new Loans();
        } catch (Exception e) {
            // Handle error (log it, display message, etc.)
        }
    }

    public List<Loans> getClosedLoans() {
        return loanService.getClosedLoans();
    }

    public void deleteLoan() {
        try {
            loanService.deleteLoan(loanNumberToDelete);
        } catch (Exception e) {
            // Handle error (log it, display message, etc.)
        }
    }

    // Getters and setters

    public Loans getNewLoan() {
        return newLoan;
    }

    public void setNewLoan(Loans newLoan) {
        this.newLoan = newLoan;
    }

    public Long getLoanNumberToDelete() {
        return loanNumberToDelete;
    }

    public void setLoanNumberToDelete(Long loanNumberToDelete) {
        this.loanNumberToDelete = loanNumberToDelete;
    }

    public LoanService getLoanService() {
        return loanService;
    }

    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }
}
