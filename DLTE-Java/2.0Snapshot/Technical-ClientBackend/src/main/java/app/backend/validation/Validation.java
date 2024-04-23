package app.backend.validation;

import app.backend.entity.EmployeeAddress;
import app.backend.entity.EmployeeDetails;
import app.backend.remotes.ValidationRepository;

import java.util.regex.Pattern;

public class Validation implements ValidationRepository {
    @Override
     public boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }
    @Override
    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }
    @Override
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }
    @Override
     public boolean isValidPincode(String pincode) {
        return pincode.matches("\\d{6}");
    }
    @Override
     public boolean isValidId(String employeeID) {
        return employeeID.matches("\\d{5}");
    }



    @Override
    public boolean validateEmployeeDetails(EmployeeDetails details){
//        Validation validation=new Validation();

        String empId=details.getEmployeeID().toString();
        String phone=details.getPhoneNumber().toString();

        if (isValidId(empId))
            details.setEmployeeID(details.getEmployeeID());
        if (isValidName(details.getEmployeeFirstName()))
            details.setEmployeeFirstName(details.getEmployeeFirstName());
        if (isValidName(details.getEmployeeMiddleName()))
            details.setEmployeeMiddleName(details.getEmployeeMiddleName());
        if (isValidName(details.getEmployeeLastName()))
            details.setEmployeeLastName(details.getEmployeeLastName());
        if (isValidEmail(details.getEmail()))
            details.setEmail(details.getEmail());
        if (isValidPhoneNumber(phone))
            details.setPhoneNumber(details.getPhoneNumber());
        return true;
    }
    @Override
    public boolean validateEmployeeAddress(EmployeeAddress permanentAddress,EmployeeAddress temporaryAddress){

        String permanentPincode=permanentAddress.getPincode().toString();
        String temporaryPincode=temporaryAddress.getPincode().toString();

        if (isValidName(permanentAddress.getCity()))
            permanentAddress.setCity(permanentAddress.getCity());
        if (isValidName(permanentAddress.getState()))
            permanentAddress.setState(permanentAddress.getState());
        if (isValidPincode(permanentPincode))
            permanentAddress.setPincode(permanentAddress.getPincode());

        if (isValidName(temporaryAddress.getCity()))
            temporaryAddress.setCity(temporaryAddress.getCity());
        if (isValidName(temporaryAddress.getState()))
            temporaryAddress.setState(temporaryAddress.getState());
        if (isValidPincode(temporaryPincode))
            temporaryAddress.setPincode(temporaryAddress.getPincode());
        return true;
    }


}
