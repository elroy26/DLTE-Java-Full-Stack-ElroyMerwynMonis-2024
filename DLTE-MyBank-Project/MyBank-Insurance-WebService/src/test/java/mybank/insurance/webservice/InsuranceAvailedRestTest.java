package mybank.insurance.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import maybank.insurance.dao.entity.InsuranceAvailed;
import maybank.insurance.dao.exceptions.InsuranceAvailedException;
import maybank.insurance.dao.remotes.InsuranceAvailedRepository;
import mybank.insurance.webservice.rest.controller.InsuranceController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.Validation;
import javax.validation.Validator;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class InsuranceAvailedRestTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InsuranceAvailedRepository availableDbRepo;

    @InjectMocks
    private InsuranceController insuranceController;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Test
    public void testSaveInsuranceAvailed_Success() throws SQLException, InsuranceAvailedException {
        // Mock repository to return a valid InsuranceAvailed object
        InsuranceAvailed availed = createValidInsuranceAvailed();
        when(availableDbRepo.callSaveInsuranceAvailed(any())).thenReturn(availed);

        // Send a valid request to the controller
        ResponseEntity<Object> response = insuranceController.save(availed);

        // Assert that the response status is OK and the body matches the returned InsuranceAvailed object
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(availed, response.getBody());
    }

    @Test//fail
    public void testSaveInsuranceAvailed_Failure_SQLException() throws SQLException {
        // Mock repository to throw an SQLException
        when(availableDbRepo.callSaveInsuranceAvailed(any())).thenThrow(SQLException.class);

        // Send a request to the controller
        ResponseEntity<Object> response = insuranceController.save(createValidInsuranceAvailed());

        // Assert that the response status is INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveInsuranceAvailed_Success_SQLException() throws SQLException {
        // Mock repository to throw an SQLException
        when(availableDbRepo.callSaveInsuranceAvailed(any())).thenThrow(SQLException.class);

        // Send a request to the controller
        ResponseEntity<Object> response = insuranceController.save(createValidInsuranceAvailed());

        // Assert that the response status is INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test//fail
    public void testSaveInsuranceAvailed_Failure_InsuranceAvailedException() throws SQLException, InsuranceAvailedException {
        // Mock repository to throw an InsuranceAvailedException
        InsuranceAvailed availed = createValidInsuranceAvailed();
        when(availableDbRepo.callSaveInsuranceAvailed(any())).thenReturn(availed);

        // Send a request to the controller
        ResponseEntity<Object> response = insuranceController.save(createValidInsuranceAvailed());

        // Assert that the response status is BAD_REQUEST
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }




    @Test
    @WithMockUser(username = "doe", password = "12345678")
    public void testInsuranceAvailed_Success() throws Exception {
        String request = "{\n" +
                "  \"insurancePremium\": \"2000.0\",\n" +
                "  \"insuranceKeyBenefits\": \"high interest rates\",\n" +
                "  \"insuranceLifetime\": 2,\n" +
                "  \"customerId\": 20012004,\n" +
                "  \"insuranceId\": 90019001,\n" +
                "  \"insuranceName\": \"Maxlife\",\n" +
                "  \"insuranceType\": \"Health\",\n" +
                "  \"insuranceCoverage\": \"10000.0\"\n" +
                "}\n";

        // Mock repository to return a success message
        InsuranceAvailed availed = createValidInsuranceAvailed();
        when(availableDbRepo.callSaveInsuranceAvailed(any())).thenReturn(availed);

        mockMvc.perform(post("/insurance/availed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andExpect(content().json((objectMapper.writeValueAsString(availed))));
    }

    @Test//fail
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
                "  \"insuranceCoverage\": \"10000.0\"\n" +
                "}\n";

        // Mock repository to throw an exception
        when(availableDbRepo.callSaveInsuranceAvailed(any())).thenThrow(new InsuranceAvailedException("Failed to avail insurance"));

        mockMvc.perform(post("/insurance/availed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Failed to avail insurance"));
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
