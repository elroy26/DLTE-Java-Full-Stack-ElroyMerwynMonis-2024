package app.backend.remotes;

import app.backend.entity.EmployeeAddress;
import app.backend.entity.EmployeeDetails;

public interface ValidationRepository {
    boolean isValidName(String name);
    boolean isValidPhoneNumber(String phoneNumber);
    boolean isValidEmail(String email);
    boolean isValidPincode(String pincode);
    boolean isValidId(String employeeID);
    boolean validateEmployeeDetails(EmployeeDetails details);
    boolean validateEmployeeAddress(EmployeeAddress permanentAddress,EmployeeAddress temporaryAddress);

}
