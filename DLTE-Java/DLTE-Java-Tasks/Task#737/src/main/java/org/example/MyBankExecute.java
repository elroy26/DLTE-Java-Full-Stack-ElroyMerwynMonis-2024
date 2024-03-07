package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MyBankExecute{

    private static ArrayList<Loans> myLoans;
    static List<Loans> filteredLoans = new ArrayList<>();

    public MyBankExecute(){
        myLoans=new ArrayList<>();
        myLoans.add(new Loans(1234567809L, 1232432.0, new Date("03/12/2024"), "open", "elroy", 8746372378L));
        myLoans.add(new Loans(3454567809L, 3002432.0, new Date("03/16/2024"), "closed", "razak", 8746372378L));
        myLoans.add(new Loans(6345567809L, 4002432.0, new Date("04/12/2024"), "open", "arun", 8746372378L));
        myLoans.add(new Loans(3534567809L, 5002432.0, new Date("05/23/2024"), "closed", "varun", 8746372378L));
        myLoans.add(new Loans(7655567809L, 2002432.0, new Date("06/12/2024"), "closed", "ajay", 8746372378L));
    }

    public static void main(String[] args) {
        MyBankExecute myBankExecute = new MyBankExecute();

        myLoans.forEach(loan -> {
            Date startDate = new Date("03/03/2024");
            Date endDate = new Date("05/23/2024");

            if (loan.getLoanDate().after(startDate) && loan.getLoanDate().before(endDate)) {
                filteredLoans.add(loan);
            }
        });

        for (Loans loan : filteredLoans) {
            System.out.println(loan.getLoanNumber() + " " + loan.getLoanDate());
        }
    }
}
