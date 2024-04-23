package app.employee.console.service;

import services.employee.*;

public class SoapService {
    public CallInsertEmployeeDetailsResponse detailsResponse(SoapConnector soapConnector, EmployeeDetails employeeDetails){
        CallInsertEmployeeDetailsRequest detailsRequest= new CallInsertEmployeeDetailsRequest();
        detailsRequest.setEmployeeDetails(employeeDetails);
        return (CallInsertEmployeeDetailsResponse) soapConnector.callWebService("http://localhost:8082/employeerepo",detailsRequest);
    }

    public CallOutputEmployeeResponse outputEmployeeResponse(SoapConnector soapConnector){
        CallOutputEmployeeRequest detailsRequest= new CallOutputEmployeeRequest();
        return (CallOutputEmployeeResponse) soapConnector.callWebService("http://localhost:8082/employeerepo",detailsRequest);
    }

    public CallFilterEmployeeResponse filterEmployeeResponse(SoapConnector soapConnector,Integer pincode){
        CallFilterEmployeeRequest filterEmployeeRequest=new CallFilterEmployeeRequest();
        filterEmployeeRequest.setPincode(pincode);
        return (CallFilterEmployeeResponse) soapConnector.callWebService("http://localhost:8082/employeerepo",filterEmployeeRequest);
    }
}
