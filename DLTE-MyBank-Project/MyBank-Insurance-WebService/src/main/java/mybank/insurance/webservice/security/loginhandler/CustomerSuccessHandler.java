package mybank.insurance.webservice.security.loginhandler;

import maybank.insurance.dao.entity.Customer;
import maybank.insurance.dao.remotes.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
@ComponentScan("maybank.insurance.dao")
public class CustomerSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    CustomerRepository service;

    Logger logger= LoggerFactory.getLogger(CustomerSuccessHandler.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("insurance");

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Customer customer = (Customer) authentication.getPrincipal();
        if (!customer.getCustomerStatus().equals("closed")) {
            if(customer.getAttempts()>1)
            {
                customer.setAttempts(1);
                logger.warn(resourceBundle.getString("security.update"));
                service.updateAttempts(customer);
            }
            logger.warn(customer.getCustomerStatus());
            super.setDefaultTargetUrl("/ui/dash");

        } else {
            logger.warn(resourceBundle.getString("security.max"));
            super.setDefaultTargetUrl("/ui/?errors="+resourceBundle.getString("security.max"));
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
