package app.employee.webservice.endpoint;

import app.employee.dao.entity.EmployeeDetails;
import app.employee.dao.entity.EmployeeAddress;
import app.employee.dao.entity.EmployeeHttpResponse;
import app.employee.dao.exceptions.EmployeeException;
import app.employee.dao.remotes.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.employee.*;

import javax.jws.WebService;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Endpoint
@ComponentScan("app.employee.dao.remotes")
public class EmployeeProfileEndpoint {
    //http://localhost:8082/employeerepo/employee.wsdl
    private final String url="http://employee.services";

    @Autowired
    private EmployeeRepository employeeRepository;

    @PayloadRoot(namespace = url,localPart = "callInsertEmployeeDetailsRequest")
    @ResponsePayload
    public CallInsertEmployeeDetailsResponse addNewEmployee(@RequestPayload CallInsertEmployeeDetailsRequest detailsRequest) throws EmployeeException {
        CallInsertEmployeeDetailsResponse detailsResponse=new CallInsertEmployeeDetailsResponse();
        ServiceStatus serviceStatus=new ServiceStatus();

        services.employee.EmployeeDetails actual= detailsRequest.getEmployeeDetails();
        EmployeeDetails daoEmployee=new EmployeeDetails();
        EmployeeAddress temporaryAddress=new EmployeeAddress();
        EmployeeAddress permanentAddress=new EmployeeAddress();
        BeanUtils.copyProperties(actual,daoEmployee,"permanentAddress","temporaryAddress");
        BeanUtils.copyProperties(actual.getEmployeePermanentAddress(),permanentAddress);
        BeanUtils.copyProperties(actual.getEmployeeTemporaryAddress(),temporaryAddress);
        daoEmployee.setEmployeePermanentAddress(permanentAddress);
        daoEmployee.setEmployeeTemporaryAddress(temporaryAddress);
        EmployeeHttpResponse httpResponse=null;

        if (daoEmployee!=null){
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage("Employee details inserted successfully");
            detailsResponse.setServiceStatus(serviceStatus);
        }
        try {
            httpResponse= employeeRepository.insertEmployeeDetails(daoEmployee);
        } catch ( SQLException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage(actual.getEmployeeID() + " not inserted");
            throw new EmployeeException("Database error occurred"+e);
        }catch (EmployeeException | EmployeeHttpResponse e){
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage(actual.getEmployeeID() + " not inserted");
            throw new EmployeeException("Insertion failed"+e);
        }
        detailsResponse.setServiceStatus(serviceStatus);
        return detailsResponse;
    }



    @PayloadRoot(namespace = url,localPart = "callOutputEmployeeRequest")
    @ResponsePayload
    public CallOutputEmployeeResponse listEmployee(@RequestPayload CallOutputEmployeeRequest outputEmployeeRequest) throws EmployeeException{
        CallOutputEmployeeResponse outputEmployeeResponse=new CallOutputEmployeeResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.employee.EmployeeDetails> returnEmployee = new ArrayList<>();

        List<EmployeeDetails> fromDao=null;
        try {
            fromDao=employeeRepository.outputEmployeeProfile();

        } catch (SQLException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage("Failed To read Employee Details");
            throw new EmployeeException("Database error occurred"+e);
        }catch (EmployeeException e){
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage("Failed To read Employee Details");
            throw new EmployeeException("Retrieval failed"+e);
        }
        for (EmployeeDetails each: fromDao){
            services.employee.EmployeeDetails currentEmployee = new services.employee.EmployeeDetails();
            BeanUtils.copyProperties(each, currentEmployee);
            services.employee.EmployeeAddress permanentAddress=new services.employee.EmployeeAddress();
            services.employee.EmployeeAddress temporaryAddress=new services.employee.EmployeeAddress();
            BeanUtils.copyProperties(each.getEmployeePermanentAddress(), permanentAddress);
            BeanUtils.copyProperties(each.getEmployeeTemporaryAddress(), temporaryAddress);
            currentEmployee.setEmployeePermanentAddress(permanentAddress);
            currentEmployee.setEmployeeTemporaryAddress(temporaryAddress);
            returnEmployee.add(currentEmployee);
        }
        serviceStatus.setStatus(HttpServletResponse.SC_OK);
        serviceStatus.setMessage("Employee Details were fetched");

        outputEmployeeResponse.setServiceStatus(serviceStatus);
        outputEmployeeResponse.getEmployeeProfile().addAll(returnEmployee);

        return outputEmployeeResponse;
    }

    @PayloadRoot(namespace = url,localPart = "callFilterEmployeeRequest")
    @ResponsePayload
    public CallFilterEmployeeResponse listFilteredEmployee(@RequestPayload CallFilterEmployeeRequest filterEmployeeRequest) throws EmployeeException{
        CallFilterEmployeeResponse filterEmployeeResponse=new CallFilterEmployeeResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.employee.EmployeeDetails> returnEmployee = new ArrayList<>();

        List<EmployeeDetails> fromDao=null;
        try {
            fromDao=employeeRepository.outputEmployeeProfile();

        } catch (SQLException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage("Failed To read Employee Details");
            throw new EmployeeException("Database error occurred"+e);
        }catch (EmployeeException e){
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage("Failed To read Employee Details");
            throw new EmployeeException("Retrieval failed"+e);
        }
        for (EmployeeDetails each: fromDao){
            services.employee.EmployeeDetails currentEmployee = new services.employee.EmployeeDetails();
            BeanUtils.copyProperties(each, currentEmployee);
            services.employee.EmployeeAddress permanentAddress=new services.employee.EmployeeAddress();
            services.employee.EmployeeAddress temporaryAddress=new services.employee.EmployeeAddress();
            BeanUtils.copyProperties(each.getEmployeePermanentAddress(), permanentAddress);
            BeanUtils.copyProperties(each.getEmployeeTemporaryAddress(), temporaryAddress);
            currentEmployee.setEmployeePermanentAddress(permanentAddress);
            currentEmployee.setEmployeeTemporaryAddress(temporaryAddress);
            returnEmployee.add(currentEmployee);
        }
        serviceStatus.setStatus(HttpServletResponse.SC_OK);
        serviceStatus.setMessage("Employee Details were fetched");

        filterEmployeeResponse.setServiceStatus(serviceStatus);
        filterEmployeeResponse.getEmployeeDetails().addAll(returnEmployee);

        return filterEmployeeResponse;
    }
}
