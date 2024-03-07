package org.example;

import java.util.List;

public interface MyBankInterface {
    List<Loans> filterLoansByDate(String startDate, String endDate);
}
