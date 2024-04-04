package maybank.insurance.dao.insurancedao;

import maybank.insurance.dao.insurancedao.entity.InsuranceAvailable;
import maybank.insurance.dao.insurancedao.exceptions.InsuranceAvailableException;
import maybank.insurance.dao.insurancedao.services.InsuranceAvailableDbRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class InsurancedaoApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private InsuranceAvailableDbRepo insuranceAvailableDbRepo;

    @Test
    void callAllInsuranceAvailable_Success() throws SQLSyntaxErrorException {
        // Mocking the response from the database
        List<InsuranceAvailable> mockInsuranceList = new ArrayList<>();
        mockInsuranceList.add(new InsuranceAvailable(1, "Type1", "Name1", "KeyBenefits1", 10));
        mockInsuranceList.add(new InsuranceAvailable(2, "Type2", "Name2", "KeyBenefits2", 20));
        when(jdbcTemplate.query(anyString(), any(InsuranceAvailableDbRepo.CardMapper.class))).thenReturn(mockInsuranceList);

        // Calling the method under test
        List<InsuranceAvailable> result = insuranceAvailableDbRepo.callAllInsuranceAvailable();

        // Verifying the result
        assertEquals(2, result.size());
        assertEquals("Type1", result.get(0).getInsuranceType());
        assertEquals("Name2", result.get(1).getInsuranceName());
    }

    @Test
    void callAllInsuranceAvailable_NoDataFound() {
        // Mocking an empty response from the database
        when(jdbcTemplate.query(anyString(), any(InsuranceAvailableDbRepo.CardMapper.class))).thenReturn(new ArrayList<>());

        // Calling the method under test and expecting an exception
        assertThrows(InsuranceAvailableException.class, () -> insuranceAvailableDbRepo.callAllInsuranceAvailable());
    }

}
