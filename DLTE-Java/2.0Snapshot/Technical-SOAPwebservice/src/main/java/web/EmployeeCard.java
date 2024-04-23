package web;

import app.backend.entity.EmployeeAddress;
import app.backend.entity.EmployeeDetails;

import java.util.List;

public class EmployeeCard {
    private List<EmployeeDetails> employeeDetails;
    private List<EmployeeAddress> employeeAddresses;

    public EmployeeCard() {
    }

    public EmployeeCard(List<EmployeeDetails> employeeDetails, List<EmployeeAddress> employeeAddresses) {
        this.employeeDetails = employeeDetails;
        this.employeeAddresses = employeeAddresses;
    }

    public List<EmployeeDetails> getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(List<EmployeeDetails> employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public List<EmployeeAddress> getEmployeeAddresses() {
        return employeeAddresses;
    }

    public void setEmployeeAddresses(List<EmployeeAddress> employeeAddresses) {
        this.employeeAddresses = employeeAddresses;
    }

    @Override
    public String toString() {
        return "EmployeeCard{" +
                "employeeDetails=" + employeeDetails +
                ", employeeAddresses=" + employeeAddresses +
                '}';
    }
}
