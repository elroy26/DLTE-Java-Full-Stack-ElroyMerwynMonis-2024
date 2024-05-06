package maybank.insurance.dao;

import maybank.insurance.dao.entity.InsuranceAvailable;
import maybank.insurance.dao.exceptions.InsuranceAvailableException;
import maybank.insurance.dao.services.InsuranceAvailableDbRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class InsurancedaoApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private InsuranceAvailableDbRepo insuranceAvailableDbRepo;

    private List<InsuranceAvailable> mockInsuranceList;

    @BeforeEach
    void setUp() {
        // Initialize mockInsuranceList with common test data
        mockInsuranceList = new ArrayList<>();
        mockInsuranceList.add(new InsuranceAvailable(1, "Type1", "Name1", "KeyBenefits1", 10));
        mockInsuranceList.add(new InsuranceAvailable(2, "Type2", "Name2", "KeyBenefits2", 20));

        // Set up common behavior for the mock jdbcTemplate
        when(jdbcTemplate.query(anyString(), any(InsuranceAvailableDbRepo.InsuranceMapper.class)))
                .thenReturn(mockInsuranceList);
    }

    @Test
    void testGetterAndSetter() {
        // Create an instance of InsuranceAvailable
        InsuranceAvailable insuranceAvailable = new InsuranceAvailable();

        // Set values using setter methods
        insuranceAvailable.setInsuranceId(1);
        insuranceAvailable.setInsuranceType("Type1");
        insuranceAvailable.setInsuranceName("Name1");
        insuranceAvailable.setInsuranceKeyBenefits("KeyBenefits1");
        insuranceAvailable.setInsuranceLifetime(10);

        // Get values using getter methods
        assertEquals(1, insuranceAvailable.getInsuranceId());
        assertEquals("Type1", insuranceAvailable.getInsuranceType());
        assertEquals("Name1", insuranceAvailable.getInsuranceName());
        assertEquals("KeyBenefits1", insuranceAvailable.getInsuranceKeyBenefits());
        assertEquals(10, insuranceAvailable.getInsuranceLifetime());
    }

    @Test
    void testToStringMethod() {
        // Create an InsuranceAvailable object
        InsuranceAvailable insurance = new InsuranceAvailable();
        insurance.setInsuranceId(1);
        insurance.setInsuranceType("Life");
        insurance.setInsuranceName("Term Life Insurance");
        insurance.setInsuranceKeyBenefits("Death Benefit, Tax Benefits");
        insurance.setInsuranceLifetime(30);

        // Verify that toString() method returns the correct string representation
        assertEquals("InsuranceAvailable{insuranceId=1, insuranceType='Life', insuranceName='Term Life Insurance', insuranceKeyBenefits='Death Benefit, Tax Benefits', insuranceLifetime=30}", insurance.toString());
    }

    @Test
    void callAllInsuranceAvailable_Success() throws SQLException {
        // Calling the method under test
        List<InsuranceAvailable> result = insuranceAvailableDbRepo.callAllInsuranceAvailable();

        // Verifying the result
        assertEquals(mockInsuranceList.size(), result.size());
        assertEquals(mockInsuranceList.get(0), result.get(0));
        assertEquals(mockInsuranceList.get(1), result.get(1));
        assertEquals(2, result.size()); // Check if the correct number of items is returned
        assertEquals("Type1", result.get(0).getInsuranceType()); // Check the first item's insurance type
    }

    @Test
    void callAllInsuranceAvailable_NoDataFound() {
        // Mocking an empty response from the database
        when(jdbcTemplate.query(anyString(), any(InsuranceAvailableDbRepo.InsuranceMapper.class))).thenReturn(new ArrayList<>());

        assertThrows(InsuranceAvailableException.class, () -> insuranceAvailableDbRepo.callAllInsuranceAvailable());
    }

    @Test
    void callAllInsuranceAvailable_SQLException() throws SQLException {
        // Mocking the jdbcTemplate to throw a SQLException
        when(jdbcTemplate.query(anyString(), any(InsuranceAvailableDbRepo.InsuranceMapper.class)))
                .thenAnswer(invocation -> {
                    throw new SQLException("Test SQL Exception");
                });

        // Verifying that calling the method under test throws the expected exception
        assertThrows(SQLException.class, () -> insuranceAvailableDbRepo.callAllInsuranceAvailable());
    }


    @Test
    void callAllInsuranceAvailable_DataAccessException() throws SQLException {
        when(jdbcTemplate.query(any(String.class), any(InsuranceAvailableDbRepo.InsuranceMapper.class)))
                .thenThrow(new DataAccessException("Test DataAccessException") {});

        assertThrows(SQLException.class, () -> insuranceAvailableDbRepo.callAllInsuranceAvailable());
    }

    @Test
    void callAllInsuranceAvailable_EmptyList() throws SQLException, InsuranceAvailableException {
        when(jdbcTemplate.query(any(String.class), any(InsuranceAvailableDbRepo.InsuranceMapper.class))).thenReturn(new ArrayList<>());

        assertThrows(InsuranceAvailableException.class, () -> insuranceAvailableDbRepo.callAllInsuranceAvailable());
    }
    @Test
    void testCallAllInsuranceAvailable() throws SQLException, InsuranceAvailableException {
        // Mocking the result set
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getInt(1)).thenReturn(1);
        when(resultSetMock.getString(2)).thenReturn("Type");
        when(resultSetMock.getString(3)).thenReturn("Name");
        when(resultSetMock.getString(4)).thenReturn("Key benefits");
        when(resultSetMock.getInt(5)).thenReturn(10);

        // Mocking the row mapper
        InsuranceAvailableDbRepo.InsuranceMapper insuranceMapper = new InsuranceAvailableDbRepo().new InsuranceMapper();
        InsuranceAvailable insuranceAvailable = insuranceMapper.mapRow(resultSetMock, 0);

        // Mocking the jdbcTemplate query method
        List<InsuranceAvailable> insuranceList = new ArrayList<>();
        insuranceList.add(insuranceAvailable);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(insuranceList);

        // Calling the method under test
        List<InsuranceAvailable> result = insuranceAvailableDbRepo.callAllInsuranceAvailable();

        // Assertions
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(insuranceAvailable, result.get(0));
    }

}
