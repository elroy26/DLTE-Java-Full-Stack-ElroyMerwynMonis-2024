package app.employee.console.entity;

public class EmployeeDetails {
    private Integer employeeID;
    private String employeeFirstName,employeeMiddleName,employeeLastName;
    private String email;
    private Long phoneNumber;
    private EmployeeAddress employeePermanentAddress;
    private EmployeeAddress employeeTemporaryAddress;

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "employeeID=" + employeeID +
                ", employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeMiddleName='" + employeeMiddleName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", employeePermanentAddress=" + employeePermanentAddress +
                ", employeeTemporaryAddress=" + employeeTemporaryAddress +
                '}';
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
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

    public EmployeeAddress getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    public void setEmployeePermanentAddress(EmployeeAddress employeePermanentAddress) {
        this.employeePermanentAddress = employeePermanentAddress;
    }

    public EmployeeAddress getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    public void setEmployeeTemporaryAddress(EmployeeAddress employeeTemporaryAddress) {
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }

    public EmployeeDetails() {
    }

    public EmployeeDetails(Integer employeeID, String employeeFirstName, String employeeMiddleName, String employeeLastName, String email, Long phoneNumber, EmployeeAddress employeePermanentAddress, EmployeeAddress employeeTemporaryAddress) {
        this.employeeID = employeeID;
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastName = employeeLastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.employeePermanentAddress = employeePermanentAddress;
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }
}
