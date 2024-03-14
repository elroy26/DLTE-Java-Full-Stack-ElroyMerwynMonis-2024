package dao.servlets;

import app.mybank.entity.Transaction;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/findAllByTypeUser")
public class FindAllByTypeUser extends HttpServlet {
    private TransactionService transactionService;
    private Logger logger= LoggerFactory.getLogger("FindAllByTypeUser");

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        transactionService=new TransactionService(storageTarget);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUser=req.getParameter("user");
        String requestType=req.getParameter("type");
        resp.setContentType("application/json");
        try{
            List<Transaction> transactions = transactionService.callFindByType(requestUser,requestType);
            Gson gson=new Gson();
            String responseData= gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(responseData);

        }catch (Exception e){
            logger.error(e.getMessage());
            System.out.println(e.getMessage());

        }
    }
}
