package mybank.insurance.webservice.soap.endpoint;

import maybank.insurance.dao.entity.InsuranceAvailable;
import maybank.insurance.dao.exceptions.InsuranceAvailableException;
import maybank.insurance.dao.remotes.InsuranceAvailableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.insurance.CallAllInsuranceAvailableRequest;
import services.insurance.CallAllInsuranceAvailableResponse;
import services.insurance.ServiceStatus;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//this endpoint performs the handling req and res for fetching all the insurance types from db
@Endpoint
@ComponentScan("maybank.insurance.dao")
public class InsuranceAvailableEndpoint {
    //http://localhost:8082/insurancerepo/insurance.wsdl

    private final String url = "http://insurance.services";
    Logger LOGGER = LoggerFactory.getLogger(InsuranceAvailableEndpoint.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Autowired
    private InsuranceAvailableRepository availableDbRepo;

    @PayloadRoot(namespace = url, localPart = "callAllInsuranceAvailableRequest")
    @ResponsePayload
    public CallAllInsuranceAvailableResponse listInsurance(@RequestPayload CallAllInsuranceAvailableRequest availableRequest) {

        CallAllInsuranceAvailableResponse availableResponse = new CallAllInsuranceAvailableResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            List<InsuranceAvailable> insuranceDao = availableDbRepo.callAllInsuranceAvailable();

            List<services.insurance.InsuranceAvailable> actualInsurance = new ArrayList<>();
            insuranceDao.forEach(each -> {
                services.insurance.InsuranceAvailable insuranceAvailable = new services.insurance.InsuranceAvailable();
                BeanUtils.copyProperties(each, insuranceAvailable);
                actualInsurance.add(insuranceAvailable);
            });

            //Here the service status is ok when the data is successfully retrived
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage(resourceBundle.getString("soap.status.ok"));
            LOGGER.debug(resourceBundle.getString("soap.status.ok")+HttpServletResponse.SC_OK);

            availableResponse.getInsurance().addAll(actualInsurance);
        } catch (SQLException e) {
            //this catch throws exception with respect to sql syntax
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOGGER.error(resourceBundle.getString("soap.sql.error") + e + HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            serviceStatus.setMessage(resourceBundle.getString("soap.db.error"));
        } catch (InsuranceAvailableException e) {
            //this catch throws exceptions with respect to the other server exceptions
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            LOGGER.error(resourceBundle.getString("soap.unknown.error")+ e + HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("soap.unknown.error"));
        }

        availableResponse.setServiceStatus(serviceStatus);
        return availableResponse;
    }

}
