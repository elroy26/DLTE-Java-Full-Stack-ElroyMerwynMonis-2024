package business.logic;
import business.logic.LoanService;
import business.logic.Loans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class LoanBean {
    @ManagedProperty("#{loanService}")
    private LoanService loanService;

    private Loans newLoan = new Loans();
    private Long loanNumberToDelete;

    public void addNewLoan() {
        loanService.addLoan(newLoan);
        newLoan = new Loans();
    }

    public List<Loans> getClosedLoans() {
        return loanService.getClosedLoans();
    }

    public void deleteLoan() {
        loanService.deleteLoan(loanNumberToDelete);
    }

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

