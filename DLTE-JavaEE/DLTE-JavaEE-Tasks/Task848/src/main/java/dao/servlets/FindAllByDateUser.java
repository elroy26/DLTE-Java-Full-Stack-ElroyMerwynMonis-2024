package dao.servlets;

import app.mybank.entity.Transaction;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/app/findallbydateuser")
public class FindAllByDateUser extends HttpServlet {
    private TransactionService transactionService;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        transactionService=new TransactionService(storageTarget);
    }
//http://localhost:7001/Task848/app/findallbydateuser?user=elroy&startdate=01/02/2024&enddate=03/04/2024
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUser =req.getParameter("user");
        String requestStartDate=req.getParameter("startdate");
        String requestEndDate=req.getParameter("enddate");
        resp.setContentType("application/json");
        try {
//            Date startdate=Date.valueOf(requestStartDate);
//            Date enddate=Date.valueOf(requestEndDate);
            List<Transaction> transactions=transactionService.callFindByDate(requestUser,requestStartDate,requestEndDate);
            Gson gson=new Gson();
            String responseData= gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(responseData);
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
}
