package maybank.insurance.dao;

import maybank.insurance.dao.entity.Customer;
import maybank.insurance.dao.exceptions.CustomerException;
import maybank.insurance.dao.services.CustomerDbRepo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
public class CustomerTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CustomerDbRepo repo;

    private List<Customer> customers;

    @BeforeEach
    void setUp() {
        customers = new ArrayList<>();
        // Add sample customers
        customers.add(new Customer( "John Doe", "Address", "open", 1234567890L, "johndoe", "password"));
        customers.add(new Customer(2, "Alice Smith", "Another Address", "closed", 9876543210L, "alicesmith", "password123",1));
        customers.add(new Customer( 1,"Bob Johnson", "Yet Another Address", "open", 5555555555L, "bobjohnson", "securePassword",1));
    }

    @Test
    void testSetterMethods() {
        // Create a customer object
        Customer customer = new Customer();

        // Set values using setter methods
        customer.setCustomerId(123);
        customer.setCustomerName("John Doe");
        customer.setCustomerAddress("123 Main St");
        customer.setCustomerStatus("open");
        customer.setCustomerContact(9876543210L);
        customer.setUsername("johndoe");
        customer.setPassword("password");

        // Verify that values are set correctly
        assertEquals(123, customer.getCustomerId());
        assertEquals("John Doe", customer.getCustomerName());
        assertEquals("123 Main St", customer.getCustomerAddress());
        assertEquals("open", customer.getCustomerStatus());
        assertEquals(9876543210L, customer.getCustomerContact());
        assertEquals("johndoe", customer.getUsername());
        assertEquals("password", customer.getPassword());
    }

    @Test
    void testGetterMethods() {
        // Create a customer object
        Customer customer = new Customer();

        // Set values manually
        customer.setCustomerId(123);
        customer.setCustomerName("John Doe");
        customer.setCustomerAddress("123 Main St");
        customer.setCustomerStatus("open");
        customer.setCustomerContact(9876543210L);
        customer.setUsername("johndoe");
        customer.setPassword("password");

        // Verify that getter methods return the correct values
        assertEquals(123, customer.getCustomerId());
        assertEquals("John Doe", customer.getCustomerName());
        assertEquals("123 Main St", customer.getCustomerAddress());
        assertEquals("open", customer.getCustomerStatus());
        assertEquals(9876543210L, customer.getCustomerContact());
        assertEquals("johndoe", customer.getUsername());
        assertEquals("password", customer.getPassword());
    }
    @Test
    void testToStringMethod() {
        // Create a customer object
        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerName("John Doe");
        customer.setCustomerAddress("123 Main St");
        customer.setCustomerStatus("open");
        customer.setCustomerContact(9876543210L);
        customer.setUsername("johndoe");
        customer.setPassword("password");

        // Verify that toString() method returns the correct string representation
        assertEquals("Customer{, customerName='John Doe', customerAddress='123 Main St', customerStatus='open', customerContact=9876543210, username='johndoe', password='password'}", customer.toString());
    }

    @Test
    void signingUp_Failure_DataAccessException() {
        // Create a sample customer
        Customer customer = customers.get(0);

        // Mock the jdbcTemplate to throw DataAccessException
        when(jdbcTemplate.update(anyString(), any(Object[].class)))
                .thenThrow(new DataAccessException("Mocked DataAccessException") {});

        // Call the method under test and assert that it throws CustomerException
        assertThrows(CustomerException.class, () -> repo.signingUp(customer));
    }

    @Test
    void findByUserName_Success() {
        // Mock the jdbcTemplate to return the sample customer list
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                .thenReturn(customers);

        // Call the method under test
        Customer foundCustomer = repo.findByUserName("johndoe");

        // Verify the result
        assertNotNull(foundCustomer);
        assertEquals("John Doe", foundCustomer.getCustomerName());
    }

    @Test
    void findByUserName_Failure_EmptyResultDataAccessException() {
        // Mock the jdbcTemplate to throw EmptyResultDataAccessException
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                .thenThrow(EmptyResultDataAccessException.class);

        // Call the method under test and assert that it throws CustomerException
        assertThrows(CustomerException.class, () -> repo.findByUserName("johndoe"));
    }

    @Test
    void findByUserName_Failure_DataAccessException() {
        // Mock the jdbcTemplate to throw DataAccessException
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                .thenThrow(CustomerException.class);

        // Call the method under test and assert that it throws CustomerException
        assertThrows(CustomerException.class, () -> repo.findByUserName("johndoe"));
    }
    @Test
    void signingUp_InvalidCustomerData() {
        // Create a sample customer with invalid data
        Customer invalidCustomer = new Customer();
        invalidCustomer.setCustomerName(null); // Missing required field
        // Set other properties as needed

        // Call the method under test and assert that it throws CustomerException
        assertThrows(CustomerException.class, () -> repo.signingUp(invalidCustomer));
    }


    @Test
    void testFindByCustomerId_CustomerDoesNotExist() {
        // Mock listAllCustomer to return an empty list
        when(repo.listAllCustomer()).thenReturn(new ArrayList<>());

        // Call the method under test and assert that it throws EmptyResultDataAccessException
        assertThrows(CustomerException.class, () -> repo.findByCustomerId("nonexistinguser"));
    }

    @Test
    void testFindByCustomerId_DataAccessException() {
        // Mock listAllCustomer to throw DataAccessException
        when(repo.listAllCustomer()).thenThrow(new DataAccessException("Mocked DataAccessException") {});

        // Call the method under test and assert that it throws CustomerException
        assertThrows(CustomerException.class, () -> repo.findByCustomerId("john123"));
    }

    @Test
    void testUpdateAttempts_Success() {
        // Create a sample customer
        Customer customer = new Customer();
        customer.setUsername("john123");
        customer.setAttempts(2);
        customer.getMaxAttempt();

        // Call the method under test
        repo.updateAttempts(customer);

        // Verify that jdbcTemplate.update() was called with the correct parameters
        verify(jdbcTemplate).update("update MYBANK_WEB_CUSTOMER set attempts=? where username=?",
                customer.getAttempts(), customer.getUsername());
    }
    @Test
    void testUpdateStatus_Success() {
        // Create a sample customer
        Customer customer = new Customer();
        customer.setUsername("john123");

        // Call the method under test
        repo.updateStatus(customer);

        // Verify that jdbcTemplate.update() was called with the correct parameters
        verify(jdbcTemplate).update("update MYBANK_WEB_CUSTOMER set customer_status=? where username=?",
                "closed", customer.getUsername());
    }



}
