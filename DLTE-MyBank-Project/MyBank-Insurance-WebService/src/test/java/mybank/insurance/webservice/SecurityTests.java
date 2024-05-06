package mybank.insurance.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import maybank.insurance.dao.entity.Customer;
import maybank.insurance.dao.exceptions.CustomerException;
import maybank.insurance.dao.remotes.CustomerRepository;
import mybank.insurance.webservice.security.controller.CustomerSignupAPI;
import mybank.insurance.webservice.security.loginhandler.CustomerFailureHandler;
import mybank.insurance.webservice.security.loginhandler.CustomerSuccessHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class SecurityTests {
    @Mock
    private CustomerRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomerSignupAPI controller;
    @InjectMocks
    private CustomerFailureHandler failureHandler;
    @InjectMocks
    private CustomerSuccessHandler successHandler;

    private MockMvc mockMvc;

    @Test
    public void testSave() throws Exception {
        // Mock customer data
        Customer customer = new Customer();
        customer.setUsername("testUser");
        customer.setPassword("testPassword");

        // Mock the repository response
        when(repository.signingUp(ArgumentMatchers.any(Customer.class))).thenReturn(customer);

        // Mock the password encoder response
        when(passwordEncoder.encode(ArgumentMatchers.anyString())).thenReturn("encodedPassword");

        // Set up mockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/profile/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testUser"));
    }
    @Test
    public void testSaveProfile() {
        // Mock customer data
        Customer customer = new Customer();
        customer.setUsername("testUser");
        customer.setPassword("testPassword");

        // Mock repository response
        Mockito.when(repository.signingUp(ArgumentMatchers.any(Customer.class))).thenReturn(customer);

        // Mock password encoder response
        Mockito.when(passwordEncoder.encode(ArgumentMatchers.anyString())).thenReturn("encodedPassword");

        // Perform save operation
        Customer savedCustomer = controller.save(customer);

        // Verify that repository method is called with the correct argument
        Mockito.verify(repository).signingUp(customer);

        // Verify that password encoder is called with the correct argument
        Mockito.verify(passwordEncoder).encode("testPassword");

        // Verify the returned customer object
        assertEquals(customer.getUsername(), savedCustomer.getUsername());
        assertEquals("encodedPassword", savedCustomer.getPassword()); // Assuming password was encoded correctly
    }


    // Helper method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void onAuthenticationFailure_ValidCustomer() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        String username = "testUser";
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setAttempts(2);


        when(repository.findByUserName(username)).thenReturn(customer);

        AuthenticationException exception = new LockedException("Invalid credentials");

        failureHandler.onAuthenticationFailure(request, response, exception);

        verify(repository, times(1)).updateAttempts(any(Customer.class));
        verify(repository, never()).updateStatus(any(Customer.class));
        verify(response).sendRedirect("/ui/?error=1 Invalid credentials");
    }

    @Test
    void onAuthenticationFailure_MaxAttemptsReached() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        String username = "testUser";
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setAttempts(3); // Max attempts reached


        when(repository.findByUserName(username)).thenReturn(customer);

        AuthenticationException exception = new LockedException("Invalid credentials");

        failureHandler.onAuthenticationFailure(request, response, exception);

        verify(repository, never()).updateAttempts(any(Customer.class));
        verify(repository, times(1)).updateStatus(any(Customer.class));
        verify(response).sendRedirect("/ui/?error=Invalid credentials");
    }

    @Test
    void onAuthenticationFailure_NullCustomer() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        String username = "testUser";

        when(repository.findByUserName(anyString())).thenThrow(new CustomerException("Customer not found")); // Stubbing to handle any string argument

        AuthenticationException exception = new LockedException("Invalid credentials");

        failureHandler.onAuthenticationFailure(request, response, exception);

        verify(repository, never()).updateAttempts(any(Customer.class));
        verify(repository, never()).updateStatus(any(Customer.class));
        verify(response).sendRedirect("/ui/?error=Customer not found");
    }



    @Test
    void onAuthenticationSuccess_CustomerClosed() throws ServletException, IOException {
        // Mock authentication
        Customer customer = new Customer();
        customer.setCustomerStatus("closed");
        Authentication authentication = new UsernamePasswordAuthenticationToken(customer, null);

        // Mock request and response
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Call the method
        successHandler.onAuthenticationSuccess(request, response, authentication);


    }

}
