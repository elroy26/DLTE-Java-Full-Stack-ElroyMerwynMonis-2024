package first;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "calci",value="/calci/*")
public class SipCalculator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPrinciple = req.getParameter("principle");
        String requestInterest= req.getParameter("interestRate");
        String requestPeriod = req.getParameter("period");

        if(requestPrinciple!=null && requestInterest!=null && requestPeriod!=null){
            double principle= Double.parseDouble(requestPrinciple);
            double interestRate = Double.parseDouble(requestInterest);
            double period = Double.parseDouble(requestPeriod);

            interestRate/=100;
            interestRate/=(period*12);
            double totalReturns= (principle*((Math.pow((1+interestRate),(period*12))-1)/interestRate)*(1+interestRate));
            double estimatedAmount= (totalReturns-principle);

            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Total Returns: "+totalReturns+"Estimated Returns"+estimatedAmount);
        }
        else{
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("Bad Request!!!!");
        }
    }
}
