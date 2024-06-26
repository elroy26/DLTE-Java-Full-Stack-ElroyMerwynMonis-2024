package employee.client.soap.validation;



//import employeebackend.exceptions.ConnectionException;
//import employeebackend.interfaces.Operations;
//import employeebackend.repository.DataBaseRepository;

import employee.client.soap.entity.Address;
import employee.client.soap.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validation {
    static Logger logger = LoggerFactory.getLogger(Validation.class);

    public Employee validateEmployee(Employee employee) {
        Address permanentAddress = employee.getPermanentAddress();
        Address temporaryAddress = employee.getTemporaryAddress();
        Scanner scanner = new Scanner(System.in);
        //Validation of Employee Demographics Details
        while (!Pattern.matches("^[A-Za-z]+$", employee.getFirstName())) {
            logger.warn("Invalid Name Format");
            System.out.println("Re-Enter your First Name(Name can only contain alphabets)");
            employee.setFirstName(scanner.nextLine());
        }

        while (!Pattern.matches("^[A-Za-z]*$", employee.getMiddleName())) {
            logger.warn("Invalid Name Format");
            System.out.println("Re-Enter your Middle Name(Name can only contain alphabets)");
            employee.setMiddleName(scanner.nextLine());
        }

        while (!Pattern.matches("^[A-Za-z]*$", employee.getLastName())) {
            logger.warn("Invalid Name Format");
            System.out.println("Re-Enter your Last Name(Name can only contain alphabets)");
            employee.setLastName(scanner.nextLine());
        }
        while (!Pattern.matches("[0-9]{10}", employee.getPhone().toString())) {
            logger.warn("Invalid Phone Format");
            System.out.println("Re-Enter your Phone(Phone Can contain only number with count 10)");
            try {
                employee.setPhone(scanner.nextLong());
            } catch (InputMismatchException exception) {
            }
        }
        while (!Pattern.matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$", employee.getEmail())) {
            logger.warn("Invalid Email Format");
            System.out.println("Re-Enter your Email address");
            employee.setEmail(scanner.nextLine());
        }

        //Validate Permanent Address
        while (permanentAddress.getHouseName().length() == 0) {
            logger.warn("House Name Must be provided");
            System.out.println("Re-Enter your Permanent House Name");
            permanentAddress.setHouseName(scanner.nextLine());
        }
        while (permanentAddress.getStreetName().length() == 0) {
            logger.warn("Street Name Must be provided");
            System.out.println("Re-Enter your Permanent Street Name");
            permanentAddress.setStreetName(scanner.nextLine());
        }
        while (permanentAddress.getCity().length() == 0) {
            logger.warn("City Must be provided");
            System.out.println("Re-Enter your Permanent City ");
            permanentAddress.setCity(scanner.nextLine());
        }
        while (permanentAddress.getState().length() == 0) {
            logger.warn("State Must be provided");
            System.out.println("Re-Enter your Permanent State ");
            permanentAddress.setState(scanner.nextLine());
        }
        while (!Pattern.matches("[0-9]{6}", permanentAddress.getPincode().toString())) {
            logger.warn("Invalid Pincode Format");
            System.out.println("Re-Enter your Permanent Pincode");
            try {
                permanentAddress.setPincode(scanner.nextInt());
            } catch (InputMismatchException exception) {
            }
        }

        //Validate Temporary Address
        while (temporaryAddress.getHouseName().length() == 0) {
            logger.warn("House Name Must be provided");
            System.out.println("Re-Enter your Temporary House Name");
            temporaryAddress.setHouseName(scanner.nextLine());
        }
        while (temporaryAddress.getStreetName().length() == 0) {
            logger.warn("Street Name Must be provided");
            System.out.println("Re-Enter your Temporary Street Name");
            temporaryAddress.setStreetName(scanner.nextLine());
        }
        while (temporaryAddress.getCity().length() == 0) {
            logger.warn("City Must be provided");
            System.out.println("Re-Enter your Temporary City ");
            temporaryAddress.setCity(scanner.nextLine());
        }
        while (permanentAddress.getState().length() == 0) {
            logger.warn("State Must be provided");
            System.out.println("Re-Enter your Temporary State ");
            temporaryAddress.setState(scanner.nextLine());
        }
        while (!Pattern.matches("[0-9]{6}", temporaryAddress.getPincode().toString())) {
            logger.warn("Invalid Pincode Format");
            System.out.println("Re-Enter your Temporary Pincode");
            try {
                temporaryAddress.setPincode(scanner.nextInt());
            } catch (InputMismatchException exception) {
            }
        }

//        return new Address(employeeID, permanentHouseName, permanentStreetName, permanentCity, permanentState, permanentPincode);
//        operations.create(employee);
        employee.setPermanentAddress(permanentAddress);
        employee.setTemporaryAddress(temporaryAddress);
        return employee;
    }

}
