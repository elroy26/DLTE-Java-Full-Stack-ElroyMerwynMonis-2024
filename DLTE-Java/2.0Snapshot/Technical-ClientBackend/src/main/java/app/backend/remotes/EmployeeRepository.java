package app.backend.remotes;

import app.backend.entity.EmployeeDetails;
import app.backend.entity.EmployeeAddress;
import app.backend.exceptions.EmployeeException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface EmployeeRepository {
    //Insert employee details
    EmployeeDetails insertEmployeeDetails(EmployeeDetails details) throws SQLException, EmployeeException;
    EmployeeAddress insertEmployeeAddress(EmployeeAddress permanentAddress,EmployeeAddress temporaryAddress) throws SQLException, EmployeeException;

    //Output employee details
    List<EmployeeDetails> outputEmployeeProfile() throws SQLException;
    List<EmployeeDetails> filterEmployeeProfilesByPincode(int pincode) throws SQLException;


}
