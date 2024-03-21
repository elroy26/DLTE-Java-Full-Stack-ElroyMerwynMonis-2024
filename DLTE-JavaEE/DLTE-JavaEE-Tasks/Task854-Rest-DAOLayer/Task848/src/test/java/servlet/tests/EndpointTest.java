package servlet.tests;

//import app.mybank.entity.Transaction;
//import app.mybank.services.TransactionService;
//import dao.servlets.FindAll;
//import dao.servlets.FindAllByDateUser;
//import dao.servlets.FindAllByUserService;
//import org.junit.Before;
//import org.junit.Test;
import org.junit.runner.RunWith;
//import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EndpointTest {
//    @Mock
//    private TransactionService service;
//    @Mock
//    private HttpServletResponse response;
//    @Mock
//    private HttpServletRequest request;
//    @Mock
//    private PrintWriter printWriter;
//
//    private StringWriter stringWriter;
//    List<Transaction> transactions = new ArrayList<>();
//
//    @Before
//    public void initiate() throws IOException {
//        stringWriter = new StringWriter();
//        printWriter = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(printWriter);
//        transactions.add(new Transaction("elroy", "deposit", 502340.0, new Date("01/13/2024")));
//        transactions.add(new Transaction("ajay", "withdrawal", 202340.0, new Date("02/02/2024")));
//        transactions.add(new Transaction("aman", "transfer", 202340.0, new Date("03/03/2024")));
////        System.out.println(transactions.toString());
//    }
//
//    @Test
//    public void testFindAll() throws IOException, ServletException {
//        // Arrange
//        FindAll findAllServlet = new FindAll();
//        findAllServlet.transactionService=service;
//        System.out.println(transactions.toString());
//        when(service.callViewAllTransaction()).thenReturn(transactions);
//
//        findAllServlet.doGet(request, response);
//        verify(response).setContentType("application/json");
//        verify(service).callViewAllTransaction();
//        String expected="[{\"userName\":\"elroy\",\"transactionType\":\"deposit\",\"transactionAmount\":502340.0,\"transactionDate\":\"Jan 13, 2024 12:00:00 AM\"},{\"userName\":\"ajay\",\"transactionType\":\"withdrawal\",\"transactionAmount\":202340.0,\"transactionDate\":\"Feb 2, 2024 12:00:00 AM\"},{\"userName\":\"aman\",\"transactionType\":\"transfer\",\"transactionAmount\":202340.0,\"transactionDate\":\"Mar 3, 2024 12:00:00 AM\"}]";
//
//        assertEquals("Response content should match",expected, stringWriter.toString().trim());
//    }
//
//
//    @Test
//    public void testFindAllByUsername() throws IOException, ServletException {
//
//        FindAllByUserService findAllByUserService = new FindAllByUserService();
//        findAllByUserService.transactionService = service;
//
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(new Transaction("elroy", "deposit", 502340.0, new Date("01/01/2024")));
//        when(request.getParameter("user")).thenReturn("elroy");
//        when(service.callViewTransaction(anyString())).thenReturn(transactions);
//
//        findAllByUserService.doGet(request, response);
//
//        verify(response).setContentType("application/json");
//        verify(service).callViewTransaction(anyString());
//        String expected = "[{\"userName\":\"elroy\",\"transactionType\":\"deposit\",\"transactionAmount\":502340.0,\"transactionDate\":\"Jan 1, 2024 12:00:00 AM\"}]";
//        assertEquals("Response content should match", expected, stringWriter.toString().trim());
//    }
//
//
//    @Test
//    public void testFindAllByDateAndUsername() throws IOException, ServletException {
//
//        FindAllByDateUser findAllByDateUser = new FindAllByDateUser();
//        findAllByDateUser.transactionService=service;
//        when(request.getParameter("user")).thenReturn("elroy");
//        when(request.getParameter("startdate")).thenReturn("01/01/2024");
//        when(request.getParameter("enddate")).thenReturn("02/27/2024");
//        when(service.callFindByDate(anyString(),anyString(),anyString())).thenReturn(transactions);
//
//        findAllByDateUser.doGet(request, response);
//
//        verify(response).setContentType("application/json");
//        verify(service).callFindByDate(anyString(),anyString(),anyString());
//        String expected="[{\"userName\":\"elroy\",\"transactionType\":\"deposit\",\"transactionAmount\":502340.0,\"transactionDate\":\"Jan 13, 2024 12:00:00 AM\"},{\"userName\":\"ajay\",\"transactionType\":\"withdrawal\",\"transactionAmount\":202340.0,\"transactionDate\":\"Feb 2, 2024 12:00:00 AM\"},{\"userName\":\"aman\",\"transactionType\":\"transfer\",\"transactionAmount\":202340.0,\"transactionDate\":\"Mar 3, 2024 12:00:00 AM\"}]";
//        assertEquals("Response content should match", expected, stringWriter.toString().trim());
//    }
}
