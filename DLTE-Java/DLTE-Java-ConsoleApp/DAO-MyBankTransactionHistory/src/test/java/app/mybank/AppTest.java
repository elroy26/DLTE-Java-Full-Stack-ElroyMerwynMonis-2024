package app.mybank;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import app.mybank.remotes.StorageTarget;
import app.mybank.remotes.TransactionRepository;
import app.mybank.services.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit test for simple App.
 */
//@RunWith(MockitoJUnitRunner.class)
public class AppTest {
//    @Mock
//    private StorageTarget mockStorageTarget;
//    @Mock
//    private TransactionRepository mockTransactionRepository;
//    private TransactionService services;
//    @Before
//    public void prepareStore(){
//        when(mockStorageTarget.getTransactionRepository()).thenReturn(mockTransactionRepository);
//        services=new TransactionService(mockStorageTarget);
//    }
//    @Test
//    public void testVerifyAccount_ValidCredentials_ReturnsTrue() {
//        String validUserName = "validUser";
//        String validPassword = "validPassword";
//        when(mockTransactionRepository.verifyAccount(validUserName, validPassword)).thenReturn(true);
//        boolean result = services.verifyAccount(validUserName, validPassword);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testVerifyAccount_InvalidCredentials_ReturnsFalse() {
//        String invalidUserName = "invalidUser";
//        String invalidPassword = "invalidPassword";
//        when(mockTransactionRepository.verifyAccount(invalidUserName, invalidPassword)).thenReturn(false);
//        boolean result = services.verifyAccount(invalidUserName, invalidPassword);
//        assertFalse(result);
//    }
//    @Test
//    public void testFindByAmount_PositiveAmount_CallsRepositoryMethod() {
//
//        Double amount = 50000.0;
//        services.callFindByAmount(amount);
//        verify(mockTransactionRepository, times(1)).findByAmount(amount);
//    }
//    @Test
//    public void testFindByType_ValidType_CallsRepositoryMethod() {
//        String validType = "withdrawal";
//        services.callFindByType(validType);
//        verify(mockTransactionRepository, times(1)).findByType(validType);
//    }
//    @Test
//    public void testFindByDate_ValidDates_CallsRepositoryMethod() {
//        String startDate = "01/01/2024";
//        String endDate = "01/31/2024";
//        services.callFindByDate(startDate, endDate);
//        verify(mockTransactionRepository, times(1)).findByDate(startDate, endDate);
//    }

}
