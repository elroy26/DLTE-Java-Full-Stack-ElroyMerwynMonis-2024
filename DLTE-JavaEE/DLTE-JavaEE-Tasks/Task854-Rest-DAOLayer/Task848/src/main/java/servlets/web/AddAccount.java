package servlets.web;

import app.mybank.entity.Account;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

public class AddAccount extends HttpServlet {
    private TransactionService transactionService;
    public ResourceBundle resourceBundle;
    public Logger logger;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        transactionService=new TransactionService(storageTarget);
        resourceBundle= ResourceBundle.getBundle("app");
        logger= LoggerFactory.getLogger(AddAccount.class);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        long password1=Long.parseLong(req.getParameter("password"));
        long phoneNumber=Long.parseLong(req.getParameter("phoneNumber"));
        String email=req.getParameter("email");
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        Account account=new Account(username,password,email,phoneNumber);
        RequestDispatcher dispatcher=req.getRequestDispatcher("createAccount.jsp");
        transactionService.callSaveAccount(account);
        req.setAttribute("info","account is created");
        dispatcher.forward(req, resp);
    }
}
