package app.employee.console.entity;

public class EmployeeAddress {
    private Integer employeeID;
    private String houseName, streetName, city, state;
    private Integer pincode;
    private String isTemporary;

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "employeeID=" + employeeID +
                ", houseName='" + houseName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode=" + pincode +
                ", isTemporary='" + isTemporary + '\'' +
                '}';
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getIsTemporary() {
        return isTemporary;
    }

    public void setIsTemporary(String isTemporary) {
        this.isTemporary = isTemporary;
    }

    public EmployeeAddress() {
    }

    public EmployeeAddress(Integer employeeID, String houseName, String streetName, String city, String state, Integer pincode, String isTemporary) {
        this.employeeID = employeeID;
        this.houseName = houseName;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.isTemporary = isTemporary;
    }
}
