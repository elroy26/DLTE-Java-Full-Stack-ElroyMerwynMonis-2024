package mybank.insurance.webservice.security.loginhandler;

import maybank.insurance.dao.entity.Customer;
import maybank.insurance.dao.remotes.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        try {
            Customer customer=service.findByUserName(username);
            if(customer!=null){
                if (!customer.getCustomerStatus().equals("closed")) {
                    if (customer.getAttempts()<customer.getMaxAttempt()){
                        customer.setAttempts(customer.getAttempts()+1);
                        service.updateAttempts(customer);
                        logger.warn(resourceBundle.getString("security.invalid"));
                        exception=new LockedException((4 - customer.getAttempts())+resourceBundle.getString("security.invalid"));
                        String err = customer.getAttempts() + " " + exception.getMessage();
                        logger.warn(err);
                        super.setDefaultFailureUrl("/ui/?error=" + err);
                    }else {
                        service.updateStatus(customer);
                        logger.warn(resourceBundle.getString("security.max"));
                        exception=new LockedException(resourceBundle.getString("security.max"));
                        super.setDefaultFailureUrl("/ui/?error=" + exception.getMessage());
                    }
                }else{
                    super.setDefaultFailureUrl("/ui/?error="+resourceBundle.getString("security.suspend"));
                }
            }
        }catch (UsernameNotFoundException e){
            logger.info(e.toString());
            logger.warn(resourceBundle.getString("security.suspend"));
            exception = new LockedException(resourceBundle.getString("customer.null"));
            super.setDefaultFailureUrl("/ui/?error=true"+exception.getMessage());
        }

        super.onAuthenticationFailure(request, response, exception);
    }
}
