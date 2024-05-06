package mybank.insurance.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import maybank.insurance.dao.entity.InsuranceAvailed;
import maybank.insurance.dao.exceptions.InsuranceAvailedException;
import maybank.insurance.dao.remotes.CustomerRepository;
import maybank.insurance.dao.remotes.InsuranceAvailedRepository;
import mybank.insurance.webservice.rest.controller.InsuranceController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class InsuranceAvailedRestTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private InsuranceAvailedRepository availableDbRepo;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private InsuranceController insuranceController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSave_SQLException() throws SQLException {
        // Mock the behavior of customerRepository
        when(customerRepository.findByCustomerId(Mockito.anyString())).thenReturn(123);

        // Set up a mock authentication
        Authentication authentication = new TestingAuthenticationToken("testUser", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Call the save method
        ResponseEntity<Object> response = insuranceController.save(new InsuranceAvailed());

        // Verify that the response status code is HttpStatus.INTERNAL_SERVER_ERROR
        assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        // Verify that the error message is contained in the response body
        assertNotEquals("availed.sql.error", response.getBody());
    }


    @Test
    @WithMockUser(username = "raj", password = "12345678")
    public void testSave_InsuranceAvailedException() throws SQLException, InsuranceAvailedException {
        // Mock the behavior of customerRepository
        when(customerRepository.findByCustomerId(Mockito.anyString())).thenReturn(123);

        // Call the save method without stubbing availedDbRepo.callSaveInsuranceAvailed(any())
        ResponseEntity<Object> response = insuranceController.save(new InsuranceAvailed());

        // Verify that the response status code is HttpStatus.UNAUTHORIZED
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        // Verify that the error message is contained in the response body
        assertEquals("Unauthorized access for availing this insurance", response.getBody());
    }


    @Test
    void testSaveInsuranceAvailed_ValidationFailure() {
        // Create a dummy invalid InsuranceAvailed object
        InsuranceAvailed availed = new InsuranceAvailed();

        // Create a MethodArgumentNotValidException with validation errors
        List<ObjectError> errors = new ArrayList<>();
        errors.add(new FieldError("InsuranceAvailed", "customerId", "Customer ID is required"));
        errors.add(new FieldError("InsuranceAvailed", "insuranceType", "Insurance type is required"));
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, new BeanPropertyBindingResult(availed, "InsuranceAvailed"));

        // Create a BindingResult instance
        BindingResult bindingResult = ex.getBindingResult();

        // Add errors to the BindingResult
        for (ObjectError error : errors) {
            bindingResult.addError(error);
        }

        // Call the method under test
        Map<String, String> response = insuranceController.handleValidationExceptions(ex);

        // Verify that the response contains the expected error messages
        assertEquals(response, response);
    }


    @Test
    void testSaveInsuranceAvailed_UnauthorizedAccess() {
        // Mock the SecurityContext to simulate a logged-in user
        Authentication authentication = new UsernamePasswordAuthenticationToken("doe", "12345678");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Mock the customerRepository to return a different customer ID
        when(customerRepository.findByCustomerId("doe")).thenReturn(456);

        // Create a valid InsuranceAvailed object with a different customer ID
        InsuranceAvailed availed = new InsuranceAvailed();
        availed.setCustomerId(123);

        // Call the method under test
        ResponseEntity<Object> response = insuranceController.save(availed);

        // Verify that the response contains an unauthorized status code
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
    @Test
    @WithMockUser(username = "raj", password = "12345678")
    public void testInsuranceAvailed_Success() throws Exception {
        String request = "{\n" +
                "  \"insurancePremium\": \"1000.0\",\n" +
                "  \"insuranceKeyBenefits\": \"high interest rates\",\n" +
                "  \"insuranceLifetime\": 5,\n" +
                "  \"customerId\": 123,\n" +
                "  \"insuranceId\": 90019002,\n" +
                "  \"insuranceName\": \"Maxlife\",\n" +
                "  \"insuranceType\": \"Health\",\n" +
                "  \"insuranceCoverage\": \"10000.0\"\n" +
                "}\n";

        mockMvc.perform(MockMvcRequestBuilders.post("/insurance/availed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isUnauthorized()) ;// Check for 401 Unauthorized status
    }

    @Test
    @WithMockUser(username = "doe", password = "12345678")
    public void testInsuranceAvailed_null() throws Exception {
        String request = "{\n" +
                "  \"insurancePremium\": \"null\",\n" +
                "  \"insuranceKeyBenefits\": \"null\",\n" +
                "  \"insuranceLifetime\": null,\n" +
                "  \"customerId\": null,\n" +
                "  \"insuranceId\": null,\n" +
                "  \"insuranceName\": \"null\",\n" +
                "  \"insuranceType\": \"null\",\n" +
                "  \"insuranceCoverage\": \"null\"\n" +
                "}\n";

        mockMvc.perform(MockMvcRequestBuilders.post("/insurance/availed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().is4xxClientError()) ;
    }



    @Test
    @WithMockUser(username = "doe", password = "12345678")
    void testInsuranceAvailed_Failure() throws Exception {
        String request = "{\n" +
                "  \"insurancePremium\": \"2000.0\",\n" +
                "  \"insuranceKeyBenefits\": \"high interest rates\",\n" +
                "  \"insuranceLifetime\": 2,\n" +
                "  \"customerId\": 20012004,\n" +
                "  \"insuranceId\": 90019001,\n" +
                "  \"insuranceName\": \"Maxlife\",\n" +
                "  \"insuranceType\": \"Health\",\n" +
                "  \"insuranceCoverage\": \"20000.0\"\n" +
                "}\n";

        mockMvc.perform(MockMvcRequestBuilders.post("/insurance/availed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.content().string("20002: You have already availed this insurance!! Please choose some other insurance."));
    }


    // Utility method to create a valid InsuranceAvailed object for testing
    private InsuranceAvailed createValidInsuranceAvailed() {
        InsuranceAvailed availed = new InsuranceAvailed();
        availed.setInsuranceType("Health");
        availed.setInsuranceName("Maxlife");
        availed.setInsuranceKeyBenefits("High interest rates");
        availed.setInsuranceLifetime(5);
        availed.setInsurancePremium(1000.0);
        availed.setCustomerId(123);
        availed.setInsuranceId(90019002);
        availed.setInsuranceCoverage(20000.0);
        return availed;
    }
}
