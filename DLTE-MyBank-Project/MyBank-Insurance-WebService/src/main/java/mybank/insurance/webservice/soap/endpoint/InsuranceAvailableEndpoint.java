package mybank.insurance.webservice.soap.endpoint;

import maybank.insurance.dao.insurancedao.entity.InsuranceAvailable;
import maybank.insurance.dao.insurancedao.remotes.InsuranceAvailableRepository;
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

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Endpoint
@ComponentScan("maybank.insurance.dao.insurancedao.remotes.InsuranceAvailableRepository")
public class InsuranceAvailableEndpoint {
    private final String url="http://insurance.services";
//http://localhost:8082/insurancerepo/insurance.wsdl
    @Autowired
    private InsuranceAvailableRepository availableDbRepo;

    @PayloadRoot(namespace = url, localPart = "callAllInsuranceAvailableRequest")
    @ResponsePayload
    public CallAllInsuranceAvailableResponse listLoans(@RequestPayload CallAllInsuranceAvailableRequest availableRequest) throws SQLSyntaxErrorException {
        CallAllInsuranceAvailableResponse availableResponse=new CallAllInsuranceAvailableResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<InsuranceAvailable> insuranceDao=availableDbRepo.callAllInsuranceAvailable();
        List<services.insurance.InsuranceAvailable> actualInsurance=new ArrayList<>();

        Iterator<InsuranceAvailable> iterator=insuranceDao.iterator();
        while (iterator.hasNext()){
            services.insurance.InsuranceAvailable insuranceAvailable=new services.insurance.InsuranceAvailable();
            BeanUtils.copyProperties(iterator.next(),insuranceAvailable);
            actualInsurance.add(insuranceAvailable);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("insurance are available");

        availableResponse.setServiceStatus(serviceStatus);
        availableResponse.getInsurance().addAll(actualInsurance);
        return availableResponse;

    }

}
