package app.employee.dao.remotes;

import app.employee.dao.entity.EmployeeAddress;
import app.employee.dao.entity.EmployeeDetails;
import app.employee.dao.entity.EmployeeHttpResponse;
import app.employee.dao.exceptions.EmployeeException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface EmployeeRepository {
    //Insert employee details
    EmployeeHttpResponse insertEmployeeDetails(EmployeeDetails details) throws SQLException, EmployeeException, EmployeeHttpResponse;
//    EmployeeHttpResponse insertEmployeeAddress(EmployeeAddress permanentAddress, EmployeeAddress temporaryAddress) throws SQLException, EmployeeException;

    //Output employee details
    List<EmployeeDetails> outputEmployeeProfile() throws SQLException;
    List<EmployeeDetails> filterEmployeeProfilesByPincode(int pincode) throws SQLException;
}
