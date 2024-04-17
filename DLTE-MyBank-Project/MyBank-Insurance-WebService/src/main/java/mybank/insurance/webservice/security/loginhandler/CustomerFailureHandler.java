package mybank.insurance.webservice.security.loginhandler;

import maybank.insurance.dao.entity.Customer;
import maybank.insurance.dao.remotes.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
@ComponentScan("maybank.insurance.dao")
public class CustomerFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    CustomerRepository service;

    Logger logger= LoggerFactory.getLogger(CustomerSuccessHandler.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username= request.getParameter("username");
        Customer customer=service.findByUserName(username);
        if(customer!=null){
            if (!customer.getCustomerStatus().equals("closed")) {
                if (customer.getAttempts()<customer.getMaxAttempt()){
                    customer.setAttempts(customer.getAttempts()+1);
                    service.updateAttempts(customer);
                    logger.warn(resourceBundle.getString("security.invalid"));
                    exception=new LockedException(resourceBundle.getString("security.invalid"));
                }else {
                    service.updateStatus(customer);
                    logger.warn(resourceBundle.getString("security.max"));
                    exception=new LockedException(resourceBundle.getString("security.max"));
                }
            }else{
                logger.warn(resourceBundle.getString("security.suspend"));
            }
        }else{
            logger.warn(resourceBundle.getString("security.null"));
        }
        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }
}
