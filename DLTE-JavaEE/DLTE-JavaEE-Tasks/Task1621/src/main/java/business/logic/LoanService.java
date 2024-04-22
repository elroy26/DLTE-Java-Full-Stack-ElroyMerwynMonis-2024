package business.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanService {
    private List<Loans> loanList;

    public LoanService() {
        loanList = new ArrayList<>();
        loanList.add(new Loans(231324L, 3453.9, "03/03/2024", "open", "elroy", 2435435432L));
        loanList.add(new Loans(2324324L, 3454353.9, "05/03/2024", "closed", "monis", 2435435432L));
        loanList.add(new Loans(2312324L, 3453423.9, "06/03/2024", "closed", "monis", 2435435432L));
        loanList.add(new Loans(23743564L, 34523453.9, "07/03/2024", "open", "elroy", 2435435432L));
    }


    public void addLoan(Loans loan) {
        loanList.add(loan);
    }

    public List<Loans> getClosedLoans() {
        List<Loans> closedLoans = new ArrayList<>();
        for (Loans loan : loanList) {
            if (loan.getLoanStatus().equalsIgnoreCase("Closed")) {
                closedLoans.add(loan);
            }
        }
        return closedLoans;
    }

    public void deleteLoan(Long loanNumber) {
        loanList.removeIf(loan -> loan.getLoanNumber().equals(loanNumber));
    }
}
