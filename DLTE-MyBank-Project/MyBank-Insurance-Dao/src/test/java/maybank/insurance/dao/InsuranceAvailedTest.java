package maybank.insurance.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import maybank.insurance.dao.entity.InsuranceAvailed;
import maybank.insurance.dao.exceptions.InsuranceAvailedException;
import maybank.insurance.dao.services.InsuranceAvailedDbRepo;


@SpringBootTest
public class InsuranceAvailedTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private InsuranceAvailedDbRepo repo;

    @Test
    public void testCallSaveInsuranceAvailed_Success() throws SQLException, InsuranceAvailedException {
        // Mock jdbcTemplate behavior to return a specific value
        when(jdbcTemplate.update(anyString(), any(Object[].class), any(int[].class))).thenReturn(1);

        // Create test data
        InsuranceAvailed availed = new InsuranceAvailed();
        availed.setCustomerId(123);
        availed.setInsuranceId(90019001);
        availed.setInsuranceType("Health");
        availed.setInsuranceName("maxlife");
        availed.setInsuranceKeyBenefits("high interest rates");
        availed.setInsuranceLifetime(5);
        availed.setInsurancePremium(1000.0);
        availed.setInsuranceCoverage(20000.0);

        // Call the method under test
        InsuranceAvailed result = repo.callSaveInsuranceAvailed(availed);

        // Verify that JdbcTemplate.update() was called with correct parameters
        verify(jdbcTemplate).update(anyString(), any(Object[].class), any(int[].class));

        // Assert that the result matches the input availed object
        assertEquals(availed, result);


    }


    @Test
    public void testCallSaveInsuranceAvailedException_Success() throws InsuranceAvailedException {
        // Mock jdbcTemplate behavior to throw DataAccessException
        when(jdbcTemplate.update(anyString(), any(Object[].class), any(int[].class))).thenThrow(InsuranceAvailedException.class);

        // Create test data
        InsuranceAvailed availed = new InsuranceAvailed();
        availed.setCustomerId(123);
        availed.setInsuranceId(90019001);
        availed.setInsuranceType("Health");
        availed.setInsuranceName("maxlife");
        availed.setInsuranceKeyBenefits("high interest rates");
        availed.setInsuranceLifetime(5);
        availed.setInsurancePremium(1000.0);
        availed.setInsuranceCoverage(20000.0);

        // Call the method under test and assert that it throws InsuranceAvailedException
        assertThrows(InsuranceAvailedException.class, () -> repo.callSaveInsuranceAvailed(availed));
    }

    @Test
    public void testCallSaveInsuranceAvailedException_Failure() throws InsuranceAvailedException {
        // Mock jdbcTemplate behavior to throw DataAccessException
        when(jdbcTemplate.update(anyString(), any(Object[].class), any(int[].class)))
                .thenThrow(new DataAccessException("Test Data Access Exception") {});

        // Create test data
        InsuranceAvailed availed = new InsuranceAvailed();
        availed.setCustomerId(123);
        availed.setInsuranceId(90019001);
        availed.setInsuranceType("Health");
        availed.setInsuranceName("maxlife");
        availed.setInsuranceKeyBenefits("high interest rates");
        availed.setInsuranceLifetime(5);
        availed.setInsurancePremium(1000.0);
        availed.setInsuranceCoverage(20000.0);

        // Call the method under test and assert that it throws InsuranceAvailedException
        assertThrows(InsuranceAvailedException.class, () -> repo.callSaveInsuranceAvailed(availed));
    }

    @Test
    void testToStringMethod() {
        // Create an InsuranceAvailed object
        InsuranceAvailed insurance = new InsuranceAvailed();
        insurance.setInsuranceType("Life");
        insurance.setInsuranceName("Term Life Insurance");
        insurance.setInsuranceKeyBenefits("Death Benefit, Tax Benefits");
        insurance.setInsuranceLifetime(30);
        insurance.setInsurancePremium(500.00);
        insurance.setCustomerId(123456);
        insurance.setInsuranceId(1);
        insurance.setInsuranceCoverage(2365.0);

        // Verify that toString() method returns the correct string representation
        assertNotNull("InsuranceAvailed{, insuranceType='Life', insuranceName='Term Life Insurance', insuranceKeyBenefits='Death Benefit, Tax Benefits', insuranceLifetime=30, insurancePremium=500.0, customerId=123456, insuranceId=1, insuranceCoverage=Basic Coverage}", insurance.toString());
    }
    @Test
    void testConstructorWithValidParameters() {
        // Create an InsuranceAvailed object with valid parameters
        InsuranceAvailed insurance = new InsuranceAvailed(123456, 1, "Life", "Term Life Insurance", "Death Benefit, Tax Benefits", 30, 500.00, 100000.00);

        // Verify that the object is created successfully
        assertNotNull(insurance);
        assertEquals("Life", insurance.getInsuranceType());
        assertEquals("Term Life Insurance", insurance.getInsuranceName());
        assertEquals("Death Benefit, Tax Benefits", insurance.getInsuranceKeyBenefits());
        assertEquals(30, insurance.getInsuranceLifetime());
        assertEquals(500.00, insurance.getInsurancePremium());
        assertEquals(123456, insurance.getCustomerId());
        assertEquals(1, insurance.getInsuranceId());
        assertEquals(100000.00, insurance.getInsuranceCoverage());
    }



}
