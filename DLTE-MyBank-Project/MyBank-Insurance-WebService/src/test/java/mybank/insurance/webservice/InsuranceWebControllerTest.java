package mybank.insurance.webservice;

import maybank.insurance.dao.entity.Customer;
import maybank.insurance.dao.services.CustomerDbRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.RequestDispatcher;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class InsuranceWebControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerDbRepo customerRepository;

    @Test
    @WithMockUser(username = "testUser")
    public void testCustomerName() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerName("Test Customer");
        when(customerRepository.findByUserName("testUser")).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/ui/name"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Test Customer"));
    }

    @Test
    @WithMockUser(username = "testUser")
    public void testCustomerId() throws Exception {
        when(customerRepository.findByCustomerId("testUser")).thenReturn(123);

        mockMvc.perform(MockMvcRequestBuilders.get("/ui/id"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("123"));
    }

    @Test
    public void testHandleError() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, 404);
        request.setAttribute(RequestDispatcher.ERROR_MESSAGE, "Not Found");

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/error").requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 404)
                .requestAttr(RequestDispatcher.ERROR_MESSAGE, "Not Found"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
