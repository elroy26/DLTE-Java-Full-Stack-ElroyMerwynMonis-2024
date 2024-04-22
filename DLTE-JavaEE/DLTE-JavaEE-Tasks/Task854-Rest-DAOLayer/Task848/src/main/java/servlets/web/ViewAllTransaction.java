package servlets.web;

import app.mybank.entity.Transaction;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

@WebServlet("/view")
public class ViewAllTransaction extends HttpServlet {
    private TransactionService transactionService;
    public ResourceBundle resourceBundle;
    public Logger logger;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        transactionService=new TransactionService(storageTarget);
        resourceBundle= ResourceBundle.getBundle("app");
        logger= LoggerFactory.getLogger(ViewAllTransaction.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Transaction> transactionList = transactionService.callViewAllTransaction();
        RequestDispatcher dispatcher=req.getRequestDispatcher("viewTransaction.jsp");
        req.setAttribute("transactionList",transactionList);
        dispatcher.include(req, resp);
    }
}
