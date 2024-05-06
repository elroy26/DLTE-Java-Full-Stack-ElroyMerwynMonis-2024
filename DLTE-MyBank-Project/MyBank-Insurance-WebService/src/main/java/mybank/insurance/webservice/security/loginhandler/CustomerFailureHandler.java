package mybank.insurance.webservice.security.loginhandler;

import maybank.insurance.dao.entity.Customer;
import maybank.insurance.dao.exceptions.CustomerException;
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

    Logger logger = LoggerFactory.getLogger(CustomerSuccessHandler.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("insurance");

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        try {
            Customer customer = service.findByUserName(username);
            if (customer != null) {
                if (!customer.getCustomerStatus().equals("closed")) {
                    if (customer.getAttempts() < customer.getMaxAttempt()) {
                        customer.setAttempts(customer.getAttempts() + 1);
                        service.updateAttempts(customer);
                        logger.warn(resourceBundle.getString("security.invalid"));
                        exception = new LockedException((4 - customer.getAttempts()) + " " + resourceBundle.getString("security.invalid"));
                        String err = customer.getAttempts() + " " + exception.getMessage();
                        logger.warn(err);
                        super.setDefaultFailureUrl(resourceBundle.getString("error.url") + exception.getMessage());
                    } else {
                        service.updateStatus(customer);
                        logger.warn(resourceBundle.getString("security.max"));
                        exception = new LockedException(resourceBundle.getString("security.max"));
                        super.setDefaultFailureUrl(resourceBundle.getString("error.url") + exception.getMessage());
                    }
                } else {
                    logger.warn(resourceBundle.getString("security.suspend"));
                    exception = new LockedException(resourceBundle.getString("security.suspend"));
                    super.setDefaultFailureUrl(resourceBundle.getString("error.url") + exception.getMessage());
                }
            }

        } catch (CustomerException e) {
            logger.warn(resourceBundle.getString("customer.null") + e.getMessage());
            exception = new LockedException(resourceBundle.getString("customer.null"));
            super.setDefaultFailureUrl(resourceBundle.getString("error.url") + exception.getMessage());
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}
