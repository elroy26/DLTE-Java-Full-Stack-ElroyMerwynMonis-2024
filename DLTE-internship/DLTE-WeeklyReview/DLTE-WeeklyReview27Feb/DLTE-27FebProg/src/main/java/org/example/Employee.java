package org.example;

import java.io.Serializable;

public class Employee implements Serializable {

    private String employeeFirstName,employeeMiddleName,employeeLastName;
    private String email;
    private Long phoneNumber;

    public Employee( String employeeFirstName, String employeeMiddleName, String employeeLastName, String email, Long phoneNumber) {

        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastName = employeeLastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Employee() {

    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeMiddleName() {
        return employeeMiddleName;
    }

    public void setEmployeeMiddleName(String employeeMiddleName) {
        this.employeeMiddleName = employeeMiddleName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeMiddleName='" + employeeMiddleName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
