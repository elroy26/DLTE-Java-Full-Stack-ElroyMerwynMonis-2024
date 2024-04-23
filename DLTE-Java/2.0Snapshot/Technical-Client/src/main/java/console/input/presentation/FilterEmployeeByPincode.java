package console.input.presentation;

import app.backend.entity.EmployeeDetails;
import app.backend.services.EmployeeService;
import console.input.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.EmployeeSoap;
import web.EmployeeSoapService;
import web.SOAPException_Exception;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FilterEmployeeByPincode {

    private static EmployeeSoapService employeeSoapService=new EmployeeSoapService();
    private static EmployeeSoap soap=employeeSoapService.getEmployeeSoapPort();
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("clientApp");
    private static Logger logger = LoggerFactory.getLogger(App.class);
    private static Scanner scanner=new Scanner(System.in);

    public void filterByPincode() {
//        EmployeeService employeeService=new EmployeeService();
        System.out.println("Enter the the pincode to find the Employees residing in that area");
        int pincode = scanner.nextInt();
        List<web.EmployeeDetails> employeeProfiles = null;
        try {
            employeeProfiles = soap.readByFilterPincode(pincode).getEmployeeDetails();
        } catch (SOAPException_Exception e) {
            System.out.println(e.getFaultInfo()+e.getMessage());
        }
        for (web.EmployeeDetails profile : employeeProfiles) {
//                                                                employeeDetails.setEmployeeID(profile.getEmployeeID());
            System.out.println("===========================================");
            System.out.println("-------Employee Details:-------");
            System.out.println("Employee ID: " + profile.getEmployeeID());
            System.out.println("First Name: " + profile.getEmployeeFirstName());
            System.out.println("Middle Name: " + profile.getEmployeeMiddleName());
            System.out.println("Last Name: " + profile.getEmployeeLastName());
            System.out.println("Email: " + profile.getEmail());
            System.out.println("Phone Number: " + profile.getPhoneNumber());

            System.out.println("-------Permanent Address:-------");
            System.out.println("House Name: " + profile.getEmployeePermanentAddress().getHouseName());
            System.out.println("Street Name: " + profile.getEmployeePermanentAddress().getStreetName());
            System.out.println("City: " + profile.getEmployeePermanentAddress().getCity());
            System.out.println("State: " + profile.getEmployeePermanentAddress().getState());
            System.out.println("Pincode: " + profile.getEmployeePermanentAddress().getPincode());

            System.out.println("--------Temporary Address:------");
            System.out.println("House Name: " + profile.getEmployeeTemporaryAddress().getHouseName());
            System.out.println("Street Name: " + profile.getEmployeeTemporaryAddress().getStreetName());
            System.out.println("City: " + profile.getEmployeeTemporaryAddress().getCity());
            System.out.println("State: " + profile.getEmployeeTemporaryAddress().getState());
            System.out.println("Pincode: " + profile.getEmployeeTemporaryAddress().getPincode());
            System.out.println("===========================================");

        }
    }
}
