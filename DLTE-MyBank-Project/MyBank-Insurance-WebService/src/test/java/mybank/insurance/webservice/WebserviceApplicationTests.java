package mybank.insurance.webservice;

import maybank.insurance.dao.insurancedao.entity.InsuranceAvailable;
import maybank.insurance.dao.insurancedao.remotes.InsuranceAvailableRepository;
import mybank.insurance.webservice.soap.endpoint.InsuranceAvailableEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import services.insurance.CallAllInsuranceAvailableRequest;
import services.insurance.CallAllInsuranceAvailableResponse;
import services.insurance.ServiceStatus;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WebserviceApplicationTests {

    @InjectMocks
    private InsuranceAvailableEndpoint endpoint;

    @Mock
    private InsuranceAvailableRepository repository;

    @Test
    public void testListLoans_Success() throws SQLSyntaxErrorException {
        // Arrange
        CallAllInsuranceAvailableRequest request = new CallAllInsuranceAvailableRequest();
        ServiceStatus expectedServiceStatus = new ServiceStatus();
        expectedServiceStatus.setStatus(HttpServletResponse.SC_OK);
        expectedServiceStatus.setMessage("OK");

        List<InsuranceAvailable> insuranceList = new ArrayList<>();
        insuranceList.add(new InsuranceAvailable());

        when(repository.callAllInsuranceAvailable()).thenReturn(insuranceList);

        // Act
        CallAllInsuranceAvailableResponse response = endpoint.listLoans(request);

        // Assert
        assertNotNull(response);
        assertEquals(expectedServiceStatus.getStatus(), response.getServiceStatus().getStatus());
        assertEquals(expectedServiceStatus.getMessage(), response.getServiceStatus().getMessage());
        assertNotNull(response.getInsurance());
        assertEquals(1, response.getInsurance().size());
    }

    @Test
    public void testListLoans_SQLException() throws SQLSyntaxErrorException {
        // Arrange
        CallAllInsuranceAvailableRequest request = new CallAllInsuranceAvailableRequest();
        ServiceStatus expectedServiceStatus = new ServiceStatus();
        expectedServiceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        expectedServiceStatus.setMessage("Database error");

        when(repository.callAllInsuranceAvailable()).thenThrow(SQLSyntaxErrorException.class);

        // Act
        CallAllInsuranceAvailableResponse response = endpoint.listLoans(request);

        // Assert
        assertNotNull(response);
        assertEquals(expectedServiceStatus.getStatus(), response.getServiceStatus().getStatus());
        assertEquals(expectedServiceStatus.getMessage(), response.getServiceStatus().getMessage());
        assertTrue(response.getInsurance().isEmpty());
    }

    // Add more tests as needed to cover various scenarios
}
