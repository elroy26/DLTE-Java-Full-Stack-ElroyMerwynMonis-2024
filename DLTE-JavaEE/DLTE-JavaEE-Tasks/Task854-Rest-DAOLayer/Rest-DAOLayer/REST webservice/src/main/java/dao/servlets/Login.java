package dao.servlets;

import app.mybank.entity.Account;
import app.mybank.middleware.DatabaseTarget;
import app.mybank.remotes.StorageTarget;
import app.mybank.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/app/login")
public class Login extends HttpServlet {
    private TransactionService transactionService;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private Logger logger = LoggerFactory.getLogger(Login.class);

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        transactionService=new TransactionService(storageTarget);

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUser=req.getParameter("user");
        String requestPassword=req.getParameter("password");
        resp.setContentType("application/json");
        try{
            Boolean account = transactionService.verifyAccount(requestUser,requestPassword);
            if(account==true){
                resp.setStatus(HttpServletResponse.SC_OK);
                logger.info(resourceBundle.getString("login.success"));
                resp.getWriter().println(resourceBundle.getString("login.success"));
            }
            else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                logger.warn("account.not.ok");
                resp.getWriter().println(resourceBundle.getString("account.not.ok"));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            logger.error(e.getMessage());

        }
    }
}
