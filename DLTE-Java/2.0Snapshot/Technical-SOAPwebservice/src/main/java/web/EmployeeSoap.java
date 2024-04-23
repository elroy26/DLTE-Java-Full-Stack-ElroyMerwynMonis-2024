package web;

import app.backend.entity.EmployeeAddress;
import app.backend.entity.EmployeeDetails;
import app.backend.exceptions.EmployeeException;
import app.backend.services.EmployeeService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;
import java.sql.SQLException;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EmployeeSoap {

    private SOAPFault createSOAPFault(Exception e) throws SOAPException {
        SOAPFault soapFault = SOAPFactory.newInstance().createFault();
        soapFault.setFaultCode(e.getClass().getSimpleName());
        soapFault.setFaultString(e.getMessage());
        return soapFault;
    }

    @WebMethod
    @WebResult(name="EmployeeCards")
    public EmployeeCard readAll() throws SOAPException {
        try {
            EmployeeService employeeService = new EmployeeService();
            List<EmployeeDetails> employeeDetails = employeeService.callOutputEmployeeProfile();
            EmployeeCard employeeCard = new EmployeeCard();
            employeeCard.setEmployeeDetails(employeeDetails);
            return employeeCard;
        } catch (SQLException | EmployeeException e) {
            throw new SOAPFaultException(createSOAPFault(e));
        }
    }

    @WebMethod
    public void addEmployeeDetails(@WebParam(name="addEmployeeDetails") EmployeeDetails employeeDetails) throws SOAPException {
        try {
            EmployeeService employeeService = new EmployeeService();
            employeeService.callInsertEmployeeDetails(employeeDetails);
        } catch (SQLException | EmployeeException e) {
            throw new SOAPFaultException(createSOAPFault(e));
        }
    }

    @WebMethod
    public void addEmployeeAddress(@WebParam(name="addEmployeePermanentAddress") EmployeeAddress permanentAddress, @WebParam(name = "addEmployeeTemporaryAddress") EmployeeAddress temporaryAddress) throws SOAPException {
        try {
            EmployeeService employeeService = new EmployeeService();
            employeeService.callInsertEmployeeAddress(permanentAddress, temporaryAddress);
        } catch (SQLException | EmployeeException e) {
            throw new SOAPFaultException(createSOAPFault(e));
        }
    }

    @WebMethod
    @WebResult(name="EmployeeCards")
    public EmployeeCard readByFilterPincode(@WebParam(name="IntegerPincode") Integer pincode) throws SOAPException {
        try {
            EmployeeService employeeService = new EmployeeService();
            List<EmployeeDetails> employeeDetails = employeeService.callFilterEmployeeProfilesByPincode(pincode);
            EmployeeCard employeeCard = new EmployeeCard();
            employeeCard.setEmployeeDetails(employeeDetails);
            return employeeCard;
        } catch (SQLException | EmployeeException e) {
            throw new SOAPFaultException(createSOAPFault(e));
        }
    }
}
