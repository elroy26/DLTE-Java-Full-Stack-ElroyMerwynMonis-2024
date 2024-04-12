package mybank.insurance.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import maybank.insurance.dao.entity.InsuranceAvailed;
import maybank.insurance.dao.exceptions.InsuranceAvailedException;
import maybank.insurance.dao.remotes.InsuranceAvailedRepository;
import mybank.insurance.webservice.rest.controller.InsuranceController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Validation;
import javax.validation.Validator;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class InsuranceAvailedRestTest {
    @MockBean
    private InsuranceAvailedRepository availableDbRepo;

    @InjectMocks
    private InsuranceController insuranceController;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

//    @Test
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

//    @Test
    public void testSaveInsuranceAvailed_Failure_SQLException() throws SQLException {
        // Mock repository to throw an SQLException
        when(availableDbRepo.callSaveInsuranceAvailed(any())).thenThrow(SQLException.class);

        // Send a request to the controller
        ResponseEntity<Object> response = insuranceController.save(createValidInsuranceAvailed());

        // Assert that the response status is INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

//    @Test
    public void testSaveInsuranceAvailed_Success_SQLException() throws SQLException {
        // Mock repository to throw an SQLException
        when(availableDbRepo.callSaveInsuranceAvailed(any())).thenThrow(SQLException.class);

        // Send a request to the controller
        ResponseEntity<Object> response = insuranceController.save(createValidInsuranceAvailed());

        // Assert that the response status is INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

//    @Test
    public void testSaveInsuranceAvailed_Failure_InsuranceAvailedException() throws SQLException, InsuranceAvailedException {
        // Mock repository to throw an InsuranceAvailedException
        InsuranceAvailed availed = createValidInsuranceAvailed();
        when(availableDbRepo.callSaveInsuranceAvailed(any())).thenReturn(availed);

        // Send a request to the controller
        ResponseEntity<Object> response = insuranceController.save(createValidInsuranceAvailed());

        // Assert that the response status is BAD_REQUEST
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
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
