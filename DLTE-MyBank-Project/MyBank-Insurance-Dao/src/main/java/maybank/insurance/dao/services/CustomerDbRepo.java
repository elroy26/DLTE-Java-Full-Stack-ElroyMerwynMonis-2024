package maybank.insurance.dao.services;

import maybank.insurance.dao.entity.Customer;
import maybank.insurance.dao.exceptions.CustomerException;
import maybank.insurance.dao.remotes.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ResourceBundle;

@Service
public class CustomerDbRepo implements CustomerRepository,UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger= LoggerFactory.getLogger(CustomerDbRepo.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");


    @Override
    public Customer signingUp(Customer customer) {
        customer.setCustomerStatus("open");
        try {
            String sql = "INSERT INTO MYBANK_WEB_CUSTOMER (CUSTOMER_ID,CUSTOMER_NAME, CUSTOMER_ADDRESS,CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD) VALUES (CUSTOMER_SEQ.nextval,?, ?, ?, ?, ?, ?)";
            int rowCount = jdbcTemplate.update(sql,
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerStatus(),
                    customer.getCustomerContact(),
                    customer.getUsername(),
                    customer.getPassword());
            if (rowCount == 1) {
                return customer;
            } else {
                // Handle failure to insert customer
                throw new CustomerException(resourceBundle.getString("customer.fail.signup"));
            }
        } catch (DataAccessException e) {
            // Handle database access exception
            throw new CustomerException(resourceBundle.getString("customer.database.error")+ e);
        }
    }

    @Override
    public Customer findByUserName(String username) {
        try {
            Customer customer = listAllCustomer().stream()
                    .filter(each -> each.getUsername().equals(username))
                    .findFirst()
                    .orElseThrow(() -> new EmptyResultDataAccessException(resourceBundle.getString("customer.id.error"), 1));
            return customer;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where no customer is found with the given username
            logger.error(resourceBundle.getString("customer.null")+username+ e);
            throw new CustomerException(resourceBundle.getString("customer.null") + username);
        } catch (DataAccessException e) {
            // Handle other database access exceptions
            logger.error(resourceBundle.getString("customer.find.error")+username+ e);
            throw new CustomerException(resourceBundle.getString("customer.find.error")+username);
        }
    }

    @Override
    public Integer findByCustomerId(String userName) {
        try {
            Customer customer = listAllCustomer().stream()
                    .filter(each -> each.getUsername().equals(userName))
                    .findFirst()
                    .orElseThrow(() -> new EmptyResultDataAccessException(resourceBundle.getString("customer.id.error")+userName, 1));
            return customer.getCustomerId();
        } catch (DataAccessException sqlException) {
            logger.error(resourceBundle.getString("customer.id.error") + sqlException.getMessage());
            throw new CustomerException(resourceBundle.getString("customer.id.error"));
        }
    }

    public List<Customer> listAllCustomer(){
        return jdbcTemplate.query("select * from MYBANK_WEB_CUSTOMER",new BeanPropertyRowMapper<>(Customer.class));
    }


    @Override
    public void updateAttempts(Customer customer) {
        jdbcTemplate.update("update MYBANK_WEB_CUSTOMER set attempts=? where username=?",
                new Object[]{customer.getAttempts(),customer.getUsername()});
        logger.info(resourceBundle.getString("customer.uodate"));
    }

    @Override
    public void updateStatus(Customer customer) {
        String status = "closed";
        jdbcTemplate.update("update MYBANK_WEB_CUSTOMER set customer_status=? where username=?",
                status, customer.getUsername());
        logger.info(resourceBundle.getString("customer.status"));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = findByUserName(username);
        if(customer==null)
            throw new UsernameNotFoundException(username);
        return customer;
    }
}
