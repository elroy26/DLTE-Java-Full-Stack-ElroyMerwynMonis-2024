package maybank.insurance.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.sql.Types;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import maybank.insurance.dao.entity.InsuranceAvailed;
import maybank.insurance.dao.exceptions.InsuranceAvailedException;
import maybank.insurance.dao.services.InsuranceAvailedDbRepo;
import org.springframework.jdbc.support.xml.SqlXmlFeatureNotImplementedException;

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
    public void testCallSaveInsuranceAvailedException_Success() throws SQLException,InsuranceAvailedException {
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
    public void testCallSaveInsuranceAvailedException_Failure() throws SQLException,InsuranceAvailedException {
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
        assertThrows(SQLException.class, () -> repo.callSaveInsuranceAvailed(availed));
    }
}
