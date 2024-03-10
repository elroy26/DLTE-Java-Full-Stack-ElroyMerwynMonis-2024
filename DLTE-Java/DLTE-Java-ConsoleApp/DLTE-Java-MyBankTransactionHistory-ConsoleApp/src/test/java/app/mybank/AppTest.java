package app.mybank;

import app.mybank.entity.Account;
import app.mybank.middleware.TransactionFileRepository;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    static String testFilePath= "test-transaction.txt";
    static List<Account> accountList;
    static String expectedTransaction;

    static TransactionFileRepository repository=new TransactionFileRepository(testFilePath);
    @BeforeClass
    public static void initialize(){
        ArrayList<String> transactions = new ArrayList<>();
        transactions.add("deposit,50000,03/24/2024,elroy");
        transactions.add("withdrawal,60000,12/03/2023,elroy");
        transactions.add("transfer,40000,04/03/2024,elroy");

        ArrayList<String> transactions1 = new ArrayList<>();
        transactions1.add("deposit,50000,03/24/2024,arjun");
        transactions1.add("withdrawal,60700,12/03/2023,arjun");
        transactions1.add("transfer,43000,04/03/2024,arjun");

        ArrayList<String> transactions2 = new ArrayList<>();
        transactions2.add("deposit,50050,03/24/2024,ajay");
        transactions2.add("withdrawal,60700,12/03/2023,ajay");
        transactions2.add("transfer,43300,04/03/2024,ajay");

        ArrayList<String> transactions3 = new ArrayList<>();
        transactions3.add("deposit,50050,03/24/2024,aman");
        transactions3.add("withdrawal,60700,12/03/2023,aman");
        transactions3.add("transfer,43300,04/03/2024,aman");
        accountList = Stream.of(
                new Account("elroy","1234","123@123.com",12413241324L,transactions),
                new Account("arjun","1234","123@123.com",12413241324L,transactions1),
                new Account("ajay","1234","123@123.com",12413241324L,transactions2),
                new Account("aman","1234","123@123.com",12413241324L,transactions3)
        ).collect(Collectors.toList());
        try{
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(testFilePath));
            objectOutputStream.writeObject(accountList);
            objectOutputStream.close();
        }
        catch (
                IOException ioException){}
        expectedTransaction =  accountList.get(0).getTransactions().get(0);

    }
    @Test
    public void testVerifyAccount() {
        assertTrue(repository.verifyAccount("elroy", "1234"));
        assertTrue(repository.verifyAccount("arjun", "1234"));
        assertTrue(repository.verifyAccount("ajay", "1234"));
        assertTrue(repository.verifyAccount("aman", "1234"));
        assertFalse(repository.verifyAccount("invalidUser", "invalidPassword"));
    }
    @Test
    public void testFindByDate() {
        List<Account> result = repository.findByDate("02/01/2024", "05/24/2024");
        int expectedTransactionCount = 3;
        assertFalse(result.size() == expectedTransactionCount);
        assertNotNull(result);
        assertTrue(result.get(0).getTransactions().contains(expectedTransaction));
    }
    @Test
    public void testFindByAmount() {
        List<Account> result = repository.findByAmount(60000.0);
        int expectedTransactionCount = 3;
        assertFalse(result.size() == expectedTransactionCount);
        assertEquals(expectedTransactionCount, result.size());
        assertTrue(result.contains(expectedTransaction));
    }

    @Test
    public void testFindByType() {
        List<Account> result = repository.findByType("deposit");
        int expectedTransactionCount = 3;
        assertFalse(result.size() == expectedTransactionCount);
        assertEquals(expectedTransactionCount, result.size());
        assertTrue(result.contains(expectedTransaction));
    }

}
