package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public  class InputEmployeeDetails implements InputEmployeeDetailsInterface {
    private  List<Employee> employee=new ArrayList<>();
    private  List<EmployeePermanentAddress> employeePermanentAddress= new ArrayList<>();
    private  List<EmployeeTemporaryAddress> employeeTemporaryAddresses=new ArrayList<>();
    static Scanner scanner=new Scanner(System.in);

    @Override
    public void employee() {
        System.out.println("Enter your First Name");
        String firstName=scanner.next();
        System.out.println("Enter your Middle Name");
        String middleName=scanner.next();
        System.out.println("Enter your Last Name");
        String lastName=scanner.next();
        System.out.println("Enter your Phone number");
        Long phoneNumer=scanner.nextLong();
        System.out.println("Enter Your email");
        String email=scanner.next();
        scanner.nextLine();
        employee.add(new Employee(firstName,middleName,lastName,email, phoneNumer));
    }

    @Override
    public void employeePermanentAddress() {
        System.out.println("Enter Your Permanent Address as per your official Id");
        System.out.println("Enter house name");
        String permanentHouse=scanner.next();
        System.out.println("Enter street name");
        String permanentStreet=scanner.next();
        System.out.println("Enter the city");
        String permanentCity=scanner.next();
        System.out.println("Enter the state");
        String permanentState=scanner.next();
        System.out.println("Enter the pincode");
        Integer permanentPincode=scanner.nextInt();
        employeePermanentAddress.add(new EmployeePermanentAddress(permanentHouse,permanentStreet,permanentCity,permanentState,permanentPincode));

    }

    @Override
    public void employeeTemporaryAddress() {
        System.out.println("Enter Your Temporary Address as per your official Id");
        System.out.println("Enter house name");
        String temporaryHouse=scanner.next();
        System.out.println("Enter street name");
        String temporaryStreet=scanner.next();
        System.out.println("Enter the city");
        String temporaryCity=scanner.next();
        System.out.println("Enter the state");
        String temporaryState=scanner.next();
        System.out.println("Enter the pincode");
        Integer temporaryPincode=scanner.nextInt();
        employeeTemporaryAddresses.add(new EmployeeTemporaryAddress(temporaryHouse,temporaryStreet,temporaryCity,temporaryState,temporaryPincode));

    }

    public  List<Employee> getEmployee() {
        return employee;
    }
    public  List<EmployeePermanentAddress> getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }
    public  List<EmployeeTemporaryAddress> getEmployeeTemporaryAddress() {
        return employeeTemporaryAddresses;
    }

}