package app.employee.console.Presentation;

import app.employee.console.service.SoapConnector;
import app.employee.console.service.SoapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.soap.client.SoapFaultClientException;
import services.employee.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EmployeeConsole {
    public static EmployeeDetails employeeSend = new EmployeeDetails();

    public static EmployeeDetails detailsSend=new EmployeeDetails();
    public static EmployeeAddress permanentAddressSend = new EmployeeAddress();
    public static EmployeeAddress temporaryAddressSend = new EmployeeAddress();
    public static app.employee.console.entity.EmployeeAddress permanentAddress, temporaryAddress;
    static Scanner scanner = new Scanner(System.in);
    private static SoapConnector soapConnector;
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private static SoapService operations= new SoapService();
    private static Integer employeeID;
    static Logger logger = LoggerFactory.getLogger(EmployeeConsole.class);
    static int count = 0;
    static app.employee.console.entity.EmployeeDetails collectEmployeeDetails() {
        while (true) {
            try {
                System.out.println(resourceBundle.getString("app.employee.enterId"));
                employeeID = scanner.nextInt();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid employeeID");
                System.out.println(resourceBundle.getString("app.validation.invalidEmployeeId"));
            }
        }
        scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterFirstName"));
        String firstName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterMiddleName"));
        String middleName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterLastName"));
        String lastName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterPhone"));
        Long phone;
        while (true) {
            try {
                phone = scanner.nextLong();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid Phone Number");
                System.out.println(resourceBundle.getString("app.validation.invalidPhoneNumber"));
            }
        }
        scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterEmail"));
        String email = scanner.next();
        scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterPermanentAddress"));
        System.out.println(resourceBundle.getString("app.employee.enterHouseName"));
        String permanentHouseName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterStreetName"));
        String permanentStreetName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterCity"));
        String permanentCity = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterState"));
        String permanentState = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterPincode"));
        Integer permanentPincode;
        while (true) {
            try {
                permanentPincode = scanner.nextInt();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid Pincode");
                System.out.println(resourceBundle.getString("app.validation.invalidPincode"));
            }
        }
        permanentAddress = new app.employee.console.entity.EmployeeAddress(employeeID, permanentHouseName, permanentStreetName, permanentCity, permanentState, permanentPincode, "false");
        System.out.println(resourceBundle.getString("app.employee.enterTemporaryAddress"));
        System.out.println(resourceBundle.getString("app.employee.enterHouseName"));
        scanner.nextLine();
        String temporaryHouseName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterStreetName"));
        String temporaryStreetName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterCity"));
        String temporaryCity = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterState"));
        String temporaryState = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterPincode"));
        Integer temporaryPincode;
        while (true) {
            try {
                temporaryPincode = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid Pincode");
                System.out.println("Re-Enter your Permanent Pincode");
            }
        }
        temporaryAddress = new app.employee.console.entity.EmployeeAddress(employeeID, temporaryHouseName, temporaryStreetName, temporaryCity, temporaryState, temporaryPincode, "true");
        return new app.employee.console.entity.EmployeeDetails(employeeID,firstName, middleName, lastName,email, phone,   permanentAddress, temporaryAddress);
    }

    //Translate Object into Backend Entities
    public static void translateAndSend(app.employee.console.entity.EmployeeDetails employee) {
        BeanUtils.copyProperties(employee, employeeSend);
        BeanUtils.copyProperties(employee.getEmployeePermanentAddress(), permanentAddressSend);
        BeanUtils.copyProperties(employee.getEmployeeTemporaryAddress(), temporaryAddressSend);
        employeeSend.setEmployeePermanentAddress(permanentAddressSend);
        employeeSend.setEmployeeTemporaryAddress(temporaryAddressSend);
        logger.info(employee.getEmployeePermanentAddress().toString() + "\n" + employee.toString());
        CallInsertEmployeeDetailsResponse response = operations.detailsResponse(soapConnector, employeeSend);
        System.out.println(response.getResult());
        logger.info(response.getServiceStatus().getStatus() + " : " + response.getServiceStatus().getMessage());
    }
    //Employee Details Are Displayed
    public static void displayEmployeeDetails() {
//        operations = new DataBaseRepository();
        ArrayList<EmployeeDetails> employeeList = null;
        try {
            CallOutputEmployeeResponse response = operations.outputEmployeeResponse(soapConnector);
            employeeList = (ArrayList<EmployeeDetails>) response.getEmployeeProfile();
            logger.info(response.getServiceStatus().getStatus() + " : " + response.getServiceStatus().getMessage());
        } catch (SoapFaultClientException e) {
            logger.error(e.getFaultStringOrReason());
            return;
        }
        System.out.println(resourceBundle.getString("app.employee.details"));
        int size = employeeList.size();
        for (int index = 0; index < size; index++) {
            EmployeeDetails employee = employeeList.get(index);
            System.out.println("Employee " + employee.getEmployeeID() + " Details:");
            System.out.println("  First Name: " + employee.getEmployeeFirstName());
            System.out.println("  Middle Name: " + employee.getEmployeeMiddleName());
            System.out.println("  Last Name: " + employee.getEmployeeLastName());
            System.out.println("  Phone: " + employee.getPhoneNumber());
            System.out.println("  Email: " + employee.getEmail());
            System.out.println("  Permanent Address:");
            if (employee.getEmployeePermanentAddress() != null) {
                printAddressDetails(employee.getEmployeePermanentAddress());
            } else {
                System.out.println("  Not Available");
            }
            System.out.println("  Temporary Address:");
            if (employee.getEmployeeTemporaryAddress() != null) {
                printAddressDetails(employee.getEmployeeTemporaryAddress());
            } else {
                System.out.println("  Not Available");
            }
        }
        logger.info("Displayed Employee Details");

    }

    //Prints Address object
    static public void printAddressDetails(EmployeeAddress address) {
        System.out.println("    House Name: " + address.getHouseName());
        System.out.println("    Street Name: " + address.getStreetName());
        System.out.println("    City: " + address.getCity());
        System.out.println("    State: " + address.getState());
        System.out.println("    Pincode: " + address.getPincode());
    }

    //    Print Employee Based On Pincode
    public static void displayEmployeeDetails(Integer pincode) {
//        operations = new DataBaseRepository();
        ArrayList<EmployeeDetails> employeeList = null;
        try {
            CallFilterEmployeeResponse response = operations.filterEmployeeResponse(soapConnector, pincode);
            employeeList = (ArrayList<EmployeeDetails>) response.getEmployeeDetails();
            logger.info(response.getServiceStatus().getStatus() + " : " + response.getServiceStatus().getMessage());
        } catch (SoapFaultClientException e) {
            logger.error(e.getFaultStringOrReason());
            return;
        }
        System.out.println("Employee Details by pincode Are");
        int size = employeeList.size();
        for (int index = 0; index < size; index++) {
            EmployeeDetails employee = employeeList.get(index);
            System.out.println("Employee " + employee.getEmployeeID() + " Details:");
            System.out.println("  First Name: " + employee.getEmployeeFirstName());
            System.out.println("  Middle Name: " + employee.getEmployeeMiddleName());
            System.out.println("  Last Name: " + employee.getEmployeeLastName());
            System.out.println("  Phone: " + employee.getPhoneNumber());
            System.out.println("  Email: " + employee.getEmail());
            System.out.println("  Permanent Address:");
            if (employee.getEmployeePermanentAddress() != null) {
                printAddressDetails(employee.getEmployeePermanentAddress());
            } else {
                System.out.println("  Not Available");
            }
            System.out.println("  Temporary Address:");
            if (employee.getEmployeeTemporaryAddress() != null) {
                printAddressDetails(employee.getEmployeeTemporaryAddress());
            } else {
                System.out.println("  Not Available");
            }
        }
        logger.info("Displayed Employee Details Based On Pincode");

    }
    public void console(SoapConnector connector) {
        soapConnector = connector;
        try {
            while (true) {
//                operations = new DataBaseRepository();
                String choice = "";
                System.out.println(resourceBundle.getString("app.greet"));
                System.out.println(resourceBundle.getString("app.menu"));
                char option = scanner.next().charAt(0);
                switch (option) { // option based action
                    case '1':
                        do {
                            app.employee.console.entity.EmployeeDetails employee;
                            System.out.println("Enter Employee " + (count + 1) + " Details :");
                            employee = collectEmployeeDetails();
                            try {
                                translateAndSend(employee);
                                System.out.println(resourceBundle.getString("app.employee.addAnother"));
                            } catch (SoapFaultClientException e) {
                                if (e.getFaultStringOrReason().equalsIgnoreCase("ValidationException")) {
                                    logger.error(e.getFaultStringOrReason());
                                    System.out.println(resourceBundle.getString("app.error.systemFailure"));
                                    boolean flag = false; // verify if validation as been successful in console
                                    do {
                                        try {
                                            translateAndSend(employee);
                                            flag = true;
                                        } catch (SoapFaultClientException ex) {
                                            logger.error(ex.getFaultStringOrReason());
                                            System.out.println(resourceBundle.getString("app.error.systemFailure"));
                                        }
                                    } while (!flag);
                                    scanner.nextLine();
                                    System.out.println(resourceBundle.getString("app.employee.addAnother"));
                                } else if (e.getFaultStringOrReason().equalsIgnoreCase("EmployeeExistException")) {
                                    logger.warn(e.getFaultStringOrReason());
                                    System.out.println(resourceBundle.getString("app.error.employeeIdExists"));
                                    break;
                                } else {
                                    logger.warn(e.getFaultStringOrReason());
                                    System.out.println(resourceBundle.getString("app.error.systemFailure"));
                                    break;
                                }
                            }
                        } while (scanner.next().equalsIgnoreCase("yes"));
                        break;
                    case '2':
                        displayEmployeeDetails();
                        break;
                    case '3':
                        System.out.println(resourceBundle.getString("app.employee.enterPincodeFilter"));
                        Integer pincode = scanner.nextInt();
                        displayEmployeeDetails(pincode);
                        break;
                    default:
                        System.out.println(resourceBundle.getString("app.error.invalidOption"));
                        logger.warn("Invalid Option");
                }
            }
        } catch (SoapFaultClientException e) {
            logger.error(e.getFaultStringOrReason());
            System.out.println("Critical System Failure!!! Please Contact Support");
            return;
        }
    }
}
