package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyBankExecute implements MyBankInterface {
    public static String FILE_NAME = "loans.txt";
    public List<Loans> loans;
    public MyBankExecute() {
        loans= new ArrayList<>();
        readLoansFile();
    }

    private void readLoansFile() {
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(FILE_NAME))){
            loans=(List<Loans>) objectInputStream.readObject();
        }catch (IOException |ClassNotFoundException e){
            System.out.println("error reading the loans"+e.getMessage());
        }
    }

    @Override
    public List<Loans> getLoans() {
        return loans;
    }

    @Override
    public void addNewLoan(Loans loan) {
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
    public List<Loans> getAvailableLoans() {
        List<Loans> availableLoan=new ArrayList<>();
        for (Loans loan: loans){
            if (loan.getLoanStatus().equalsIgnoreCase("Open")){
                availableLoan.add(loan);
            }
        }
        return availableLoan;
    }

    @Override
    public List<Loans> getClosedLoans() {
        List<Loans> closedLoan=new ArrayList<>();
        for (Loans loan: loans){
            if (loan.getLoanStatus().equalsIgnoreCase("Closed")){
                closedLoan.add(loan);
            }
        }
        return closedLoan;
    }
}
