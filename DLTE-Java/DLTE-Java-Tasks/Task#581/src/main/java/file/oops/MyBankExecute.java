package file.oops;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyBankExecute implements MyBank {
    public static String FILE_NAME = "loans.txt";
    public List<Loan> loans;
    public MyBankExecute() {
        loans= new ArrayList<>();
        readLoansFile();
    }

    private void readLoansFile() {
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(FILE_NAME))){
            loans=(List<Loan>) objectInputStream.readObject();
        }catch (IOException|ClassNotFoundException e){
            System.out.println("error reading the loans"+e.getMessage());
        }
    }

    @Override
    public List<Loan> getLoans() {
        return loans;
    }

    @Override
    public void addNewLoan(Loan loan) {
        loans.add(loan);
        writeLoansFile();
    }

    private void writeLoansFile() {
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            objectOutputStream.writeObject(loans);
        }catch (IOException e){
            System.out.println("Error writing the loans"+ e.getMessage());
        }
    }

    @Override
    public List<Loan> getAvailableLoans() {
        List<Loan> availableLoan=new ArrayList<>();
        for (Loan loan: loans){
            if (loan.getLoanStatus().equalsIgnoreCase("Open")){
                availableLoan.add(loan);
            }
        }
        return availableLoan;
    }

    @Override
    public List<Loan> getClosedLoans() {
        List<Loan> closedLoan=new ArrayList<>();
        for (Loan loan: loans){
            if (loan.getLoanStatus().equalsIgnoreCase("Closed")){
                closedLoan.add(loan);
            }
        }
        return closedLoan;
    }
}
