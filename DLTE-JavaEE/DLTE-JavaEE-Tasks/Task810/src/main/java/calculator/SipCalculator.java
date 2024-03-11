package calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Calculator",value="/calci/*")
public class SipCalculator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPrinciple = req.getParameter("principle");
        String requestInterest= req.getParameter("interestRate");
        String requestPeriod = req.getParameter("period");
        String requestTaxSlabType = req.getParameter("slabType");
        String requestSalary= req.getParameter("salary");

//http://localhost:7001/Task810/calci?principle=7000&interestRate=11&period=12
        if(requestPrinciple!=null && requestInterest!=null && requestPeriod!=null){
            double principle= Double.parseDouble(requestPrinciple);
            double interestRate = Double.parseDouble(requestInterest);
            double period = Double.parseDouble(requestPeriod);

            interestRate/=100;
            interestRate/=(period*12);
            double totalReturns=principle*((Math.pow((1+interestRate),(period*12))-1)/interestRate)*(1+interestRate);
            double estimatedAmount=totalReturns-principle;

            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Total Returns: "+totalReturns+"Estimated Returns"+estimatedAmount);
        }
//http://localhost:7001/Task810/calci?slabType=1&salary=1000000
        if (requestTaxSlabType!=null&& requestSalary!=null){
            int slabType=Integer.parseInt(requestTaxSlabType);
            int salary=Integer.parseInt(requestSalary);
            double tax;
            if (slabType==1){
                if(salary>0 && salary<=300000){
                    resp.getWriter().println("no deduction on the salary");
                }else if(salary>300000 && salary<=600000){
                    tax= salary*0.05;
                    resp.getWriter().println("salary deducted after applying tax is"+tax);
                }else if(salary>600000 && salary<=900000){
                    tax= salary*0.1;
                    resp.getWriter().println("salary deducted after applying tax is"+tax);
                }else if(salary>900000 && salary<=1200000){
                    tax= salary*0.15;
                    resp.getWriter().println("salary deducted after applying tax is"+tax);
                }else if(salary>1200000 && salary<=1500000){
                    tax= salary*0.2;
                    resp.getWriter().println("salary deducted after applying tax is"+tax);
                }else if(salary>1500000){
                    tax= salary*0.30;
                    resp.getWriter().println("salary deducted after applying tax is"+tax);
                }
            }else if(slabType==2){
                if(salary>0 && salary<=250000){
                    resp.getWriter().println("no deduction on the salary");
                }else if(salary>250000 && salary<=500000){
                    tax= salary*0.05;
                    resp.getWriter().println("salary deducted after applying tax is"+tax);
                }else if(salary>500000 && salary<=1000000){
                    tax= salary*0.1;
                    resp.getWriter().println("salary deducted after applying tax is"+tax);
                }else if(salary>1000000 && salary<=1500000){
                    tax= salary*0.15;
                    resp.getWriter().println("salary deducted after applying tax is"+tax);
                }else if(salary>1500000){
                    tax= salary*0.30;
                    resp.getWriter().println("salary deducted after applying tax is"+tax);
                }
            }
        }
    }
}
