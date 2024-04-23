package console.input.presentation;

import app.backend.services.EmployeeService;
import console.input.App;
import console.input.entity.EmployeeDetails;
import console.input.entity.EmployeeAddress;
import console.input.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.EmployeeSoap;
import web.EmployeeSoapService;
import web.SOAPException_Exception;

import java.util.ResourceBundle;
import java.util.Scanner;

public class InputEmployeeProfile {
//    private static EmployeeService employeeService=new EmployeeService();
    private static EmployeeSoapService employeeSoapService=new EmployeeSoapService();
    EmployeeSoap soap=employeeSoapService.getEmployeeSoapPort();

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("clientApp");
    private static Logger logger = LoggerFactory.getLogger(App.class);
    private static Scanner scanner = new Scanner(System.in);
    private static Validation validation = new Validation();

    private static EmployeeDetails employeeDetails = new EmployeeDetails();
//    private static app.backend.entity.EmployeeDetails details = new app.backend.entity.EmployeeDetails();
    private static web.EmployeeDetails details=new web.EmployeeDetails();

    private static EmployeeAddress employeePermanentAddress = new EmployeeAddress();
//    private static app.backend.entity.EmployeeAddress permanentAddress = new app.backend.entity.EmployeeAddress();
    private static web.EmployeeAddress permanentAddress = new web.EmployeeAddress();

    private static EmployeeAddress employeeTemporaryAddress = new EmployeeAddress();
    private static web.EmployeeAddress temporaryAddress = new web.EmployeeAddress();


    public void inputEmployeeDetails() {
//        EmployeeService employeeService = new EmployeeService();
        do {
            System.out.println("Enter Employee ID");
            Integer employeeID = scanner.nextInt();
            String empId = employeeID.toString();
            if (validation.isValidId(empId)) {
                employeeDetails.setEmployeeID(employeeID);
                break;
            } else {
                logger.warn(resourceBundle.getString("app.valid.empid"));
            }
        } while (true);
        do {
            System.out.println("Enter your First Name");
            String firstName = scanner.next();
            if (validation.isValidName(firstName)) {
                employeeDetails.setEmployeeFirstName(firstName);
                break;
            } else {
                logger.warn(resourceBundle.getString("app.valid.name"));
            }
        } while (true);

        do {
            System.out.println("Enter your Middle Name");
            String middleName = scanner.next();
            if (validation.isValidName(middleName)) {
                employeeDetails.setEmployeeMiddleName(middleName);
                break;
            } else {
                logger.warn(resourceBundle.getString("app.valid.name"));
            }
        } while (true);

        do {
            System.out.println("Enter your Last Name");
            String lastName = scanner.next();
            if (validation.isValidName(lastName)) {
                employeeDetails.setEmployeeLastName(lastName);
                break;
            } else {
                logger.warn(resourceBundle.getString("app.valid.name"));
            }
        } while (true);

        do {
            System.out.println("Enter Your email");
            String email = scanner.next();
            if (validation.isValidEmail(email)) {
                employeeDetails.setEmail(email);
                break;
            } else {
                logger.warn(resourceBundle.getString("app.valid.email"));
            }
        } while (true);
        do {
            System.out.println("Enter your Phone number");
            Long phoneNumber = scanner.nextLong();
            String phone = phoneNumber.toString();
            if (validation.isValidPhoneNumber(phone)) {
                employeeDetails.setPhoneNumber(phoneNumber);
                break;
            } else {
                logger.warn(resourceBundle.getString("app.valid.phone"));
            }
        } while (true);

        scanner.nextLine();
        //Transatlation Of employee Details
        details.setEmployeeID(employeeDetails.getEmployeeID());
        details.setEmployeeFirstName(employeeDetails.getEmployeeFirstName());
        details.setEmployeeMiddleName(employeeDetails.getEmployeeMiddleName());
        details.setEmployeeLastName(employeeDetails.getEmployeeLastName());
        details.setEmail(employeeDetails.getEmail());
        details.setPhoneNumber(employeeDetails.getPhoneNumber());
        try {
            soap.addEmployeeDetails(details);
        } catch (SOAPException_Exception e) {
            System.out.println(e.getFaultInfo()+e.getMessage());
        }

    }

    public void inputEmployeeAddress() {
//        EmployeeService employeeService = new EmployeeService();
        System.out.println("Enter Your Permanent Address as per your official Id");
        System.out.println("Enter house name");
        String permanentHouse = scanner.next();
        employeePermanentAddress.setHouseName(permanentHouse);

        System.out.println("Enter street name");
        String permanentStreet = scanner.next();
        employeePermanentAddress.setStreetName(permanentStreet);

        do {
            System.out.println("Enter the city ");
            String permanentCity = scanner.next();
            if (validation.isValidName(permanentCity)) {
                employeePermanentAddress.setCity(permanentCity);
                break;
            } else {
                logger.warn(resourceBundle.getString("app.valid.name"));
            }
        } while (true);

        do {
            System.out.println("Enter the state");
            String permanentState = scanner.next();
            if (validation.isValidName(permanentState)) {
                employeePermanentAddress.setState(permanentState);
                break;
            } else {
                logger.warn(resourceBundle.getString("app.valid.name"));
            }
        } while (true);

        do {
            System.out.println("Enter the pincode");
            Integer permanentPincode = scanner.nextInt();
            String pincode = permanentPincode.toString();
            if (Validation.isValidPincode(pincode)) {
                employeePermanentAddress.setPincode(permanentPincode);
                break;
            } else {
                logger.warn(resourceBundle.getString("app.valid.pincode"));
            }
        } while (true);
        permanentAddress.setHouseName(employeePermanentAddress.getHouseName());
        permanentAddress.setStreetName(employeePermanentAddress.getStreetName());
        permanentAddress.setCity(employeePermanentAddress.getCity());
        permanentAddress.setState(employeePermanentAddress.getState());
        permanentAddress.setPincode(employeePermanentAddress.getPincode());

        System.out.println("Enter Your Temporary Address as per your official Id");
        System.out.println("Enter house name");
        String temporaryHouse = scanner.next();
        employeeTemporaryAddress.setHouseName(temporaryHouse);

        System.out.println("Enter street name");
        String temporaryStreet = scanner.next();
        employeeTemporaryAddress.setStreetName(temporaryStreet);

        do {
            System.out.println("Enter the city ");
            String temporaryCity = scanner.next();
            if (validation.isValidName(temporaryCity)) {
                employeeTemporaryAddress.setCity(temporaryCity);
                break;
            } else {
                logger.warn(resourceBundle.getString("app.valid.name"));
            }
        } while (true);

        do {
            System.out.println("Enter the state");
            String temporaryState = scanner.next();
            if (validation.isValidName(temporaryState)) {
                employeeTemporaryAddress.setState(temporaryState);
                break;
            } else {
                logger.warn(resourceBundle.getString("app.valid.name"));
            }
        } while (true);
        do {
            System.out.println("Enter the pincode");
            Integer temporaryPincode = scanner.nextInt();
            String pincode = temporaryPincode.toString();
            if (Validation.isValidPincode(pincode)) {
                employeeTemporaryAddress.setPincode(temporaryPincode);
                break;
            } else {
                logger.warn(resourceBundle.getString("app.valid.pincode"));
            }
        } while (true);

        temporaryAddress.setHouseName(employeeTemporaryAddress.getHouseName());
        temporaryAddress.setStreetName(employeeTemporaryAddress.getStreetName());
        temporaryAddress.setCity(employeeTemporaryAddress.getCity());
        temporaryAddress.setState(employeeTemporaryAddress.getState());
        temporaryAddress.setPincode(employeeTemporaryAddress.getPincode());
        try {
            soap.addEmployeeAddress(permanentAddress, temporaryAddress);
        } catch (SOAPException_Exception e) {
            System.out.println(e.getFaultInfo()+e.getMessage());
        }
        scanner.nextLine();

    }

}