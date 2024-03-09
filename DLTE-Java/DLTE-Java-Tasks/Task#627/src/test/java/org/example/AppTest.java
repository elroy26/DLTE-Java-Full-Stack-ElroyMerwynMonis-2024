package org.example;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;


public class AppTest 
{
    static String testFilePath="test-credit.doc";
    static List<Loans> loansList;
    @BeforeClass
    public static void initialize(){
    loansList= Stream.of(
            new Loans(123453678L,87653.0,"02/02/2024","open","elroy",2452678L),
            new Loans(123423335L,23423.0,"03/02/2024","closed","arun",1224678L),
            new Loans(234545678L,13253.0,"04/02/2024","open","arjun",12235678L),
            new Loans(123452438L,12353.0,"05/02/2024","closed","ajay",1254578L)
            ).collect(Collectors.toList());
        try{
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(testFilePath));
            objectOutputStream.writeObject(loansList);
            objectOutputStream.close();
        }
        catch (IOException ioException){}
    }

    @Test
    public void testLoanListSize() {
        assertNotNull(loansList);
        assertEquals(4, loansList.size());
    }

    @Test
    public void testLoanDetails() {
        Loans loan = loansList.get(0);
        assertEquals(123453678L, loan.getLoanNumber().longValue());
        assertEquals(87653.0, loan.getLoanAmount(), 0.01); // tolerance for floating-point comparison
        assertEquals("02/02/2024", loan.getLoanDate());
        assertEquals("open", loan.getLoanStatus());
        assertEquals("elroy", loan.getBorrowerName());
        assertEquals(2452678L, loan.getBorrowerContact().longValue());
    }
    @Test
    public void testLoanAvailability() {
        List<Loans> availableLoans = loansList.stream()
                .filter(loan -> loan.getLoanStatus().equalsIgnoreCase("open"))
                .collect(Collectors.toList());

        assertEquals(loansList.size(), availableLoans.size());
        assertTrue(availableLoans.stream().allMatch(loan -> loan.getLoanStatus().equalsIgnoreCase("open")));
    }

    @Test
    public void testClosedLoans() {
        List<Loans> closedLoans = loansList.stream()
                .filter(loan -> loan.getLoanStatus().equalsIgnoreCase("closed"))
                .collect(Collectors.toList());

        assertEquals(loansList.size(), closedLoans.size());
        assertTrue(closedLoans.stream().allMatch(loan -> loan.getLoanStatus().equalsIgnoreCase("closed")));
    }


}
