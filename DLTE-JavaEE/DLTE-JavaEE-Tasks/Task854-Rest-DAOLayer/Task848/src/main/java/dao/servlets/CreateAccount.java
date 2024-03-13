package dao.servlets;

import app.mybank.entity.Account;
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
import java.util.List;

@WebServlet("/app/createAccount")
public class CreateAccount extends HttpServlet {
    private TransactionService transactionService;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        transactionService=new TransactionService(storageTarget);
    }
    //localhost:7001/Task848/app/createAccount
//    {
//        "userName": "roy",
//            "password": "1234",
//            "email": "roy@gmail.com",
//            "phoneNumber": 1234356789
//    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            Gson gson=new Gson();
            Account account=gson.fromJson(req.getReader(),Account.class);
            transactionService.callSaveAccount(account);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Account is successfully created");
        }     catch(NumberFormatException numberFormatException){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println(numberFormatException);
        }
    }
}
