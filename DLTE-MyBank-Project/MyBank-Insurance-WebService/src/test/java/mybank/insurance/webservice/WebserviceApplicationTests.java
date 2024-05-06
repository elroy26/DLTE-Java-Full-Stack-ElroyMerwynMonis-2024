package mybank.insurance.webservice;

import maybank.insurance.dao.entity.InsuranceAvailable;
import maybank.insurance.dao.remotes.InsuranceAvailableRepository;
import mybank.insurance.webservice.soap.endpoint.InsuranceAvailableEndpoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import services.insurance.CallAllInsuranceAvailableRequest;
import services.insurance.CallAllInsuranceAvailableResponse;
import services.insurance.ServiceStatus;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WebserviceApplicationTests {
    @MockBean
    private InsuranceAvailableRepository repository;

    @InjectMocks
    private InsuranceAvailableEndpoint endpoint;

    @Test
    public void testListLoans_Success() throws SQLException {
        // Arrange
        CallAllInsuranceAvailableRequest request = new CallAllInsuranceAvailableRequest();
        ServiceStatus expectedServiceStatus = new ServiceStatus();
        expectedServiceStatus.setStatus(HttpServletResponse.SC_OK);
        expectedServiceStatus.setMessage("OK");

        List<InsuranceAvailable> insuranceList = Stream.of(
                new InsuranceAvailable(1, "Type1", "Name1", "KeyBenefits1", 10),
                new InsuranceAvailable(2, "Type2", "Name2", "KeyBenefits2", 20)

        ).collect((Collectors.toList()));
        when(repository.callAllInsuranceAvailable()).thenReturn(insuranceList);

        // Act
        CallAllInsuranceAvailableResponse response = endpoint.listInsurance(request);

        // Assert
        assertNotNull(response);
        assertEquals(2, response.getInsurance().size());
        assertEquals(expectedServiceStatus.getStatus(), response.getServiceStatus().getStatus());
        assertNotNull(response.getInsurance());
    }
    @Test
    public void testListLoans_Failure() throws SQLException {
        // Arrange
        CallAllInsuranceAvailableRequest request = new CallAllInsuranceAvailableRequest();
        ServiceStatus expectedServiceStatus = new ServiceStatus();
        expectedServiceStatus.setStatus(HttpServletResponse.SC_OK);
        expectedServiceStatus.setMessage("INSURANCE IS AVAILABLE.");

        List<InsuranceAvailable> insuranceList = Stream.of(
                new InsuranceAvailable(1, "Type1", "Name1", "KeyBenefits1", 10),
                new InsuranceAvailable(2, "Type2", "Name2", "KeyBenefits2", 20)

        ).collect((Collectors.toList()));
        when(repository.callAllInsuranceAvailable()).thenReturn(insuranceList);

        // Act
        CallAllInsuranceAvailableResponse response = endpoint.listInsurance(request);

        // Assert
        assertEquals(expectedServiceStatus.getMessage(), response.getServiceStatus().getMessage());// fail
        assertNotEquals(1, response.getInsurance().size());//fail
    }



}
