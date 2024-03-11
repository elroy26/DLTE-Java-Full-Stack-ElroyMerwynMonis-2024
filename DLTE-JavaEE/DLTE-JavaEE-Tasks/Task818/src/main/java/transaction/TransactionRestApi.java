package transaction;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet (name = "Transaction History",value="/view")
public class TransactionRestApi extends HttpServlet {
    List<TransactionHistory> transactionHistoryList= Stream.of(
            new TransactionHistory("elroy",123456.0,"withdraw",new Date("03/13/2024")),
            new TransactionHistory("arjun",65234.0,"transfer",new Date("04/09/2024")),
            new TransactionHistory("aman",734345.0,"deposit",new Date("05/25/2024"))
    ).collect(Collectors.toList());
//http://localhost:7001/Task818/view
//http://localhost:7001/Task818/view?minAmount=1000&maxAmount=80000
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String minAmountParam = req.getParameter("minAmount");
        String maxAmountParam = req.getParameter("maxAmount");

        if (minAmountParam != null && maxAmountParam != null) {
            double minAmount = Double.parseDouble(minAmountParam);
            double maxAmount = Double.parseDouble(maxAmountParam);

            List<TransactionHistory> filteredTransactions = transactionHistoryList.stream()
                    .filter(transaction -> transaction.getTransactionAmount() >= minAmount && transaction.getTransactionAmount() <= maxAmount)
                    .collect(Collectors.toList());

            Gson gson = new Gson();
            resp.setContentType("application/json");
            String json = gson.toJson(filteredTransactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(json);
        } else {
            // No minAmount or maxAmount provided, return all transactions
            Gson gson = new Gson();
            resp.setContentType("application/json");
            String json = gson.toJson(transactionHistoryList);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(json);
        }
    }



//http://localhost:7001/Task818/view
//{
//    "sentTo": "ajay",
//        "transactionAmount": 7357345,
//        "transactionType": "deposit",
//        "transactionDate": "May 25, 2024 12:00:00 AM"
//}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();

        TransactionHistory history = gson.fromJson(req.getReader(),TransactionHistory.class);
        transactionHistoryList.add(history);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(history.getSentTo()+" has been sent successfully");
    }



}
