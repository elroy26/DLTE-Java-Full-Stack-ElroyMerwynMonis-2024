package app.backend.services;

import app.backend.entity.EmployeeAddress;
import app.backend.entity.EmployeeDetails;
import app.backend.exceptions.EmployeeException;
import app.backend.middleware.DatabaseTarget;
import app.backend.middleware.EmployeeDatabaseRepository;
import app.backend.remotes.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private Connection connection;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("employee");
    private EmployeeRepository employeeRepository;

    public EmployeeService(){
        this.connection=DatabaseTarget.initializeConnection();
        this.employeeRepository = new EmployeeDatabaseRepository(connection);
    }


    // Insert Employee Details
    public EmployeeDetails callInsertEmployeeDetails(EmployeeDetails details) throws SQLException,EmployeeException {
//        try {
            return employeeRepository.insertEmployeeDetails(details);
//        } catch (Exception e) {
//            logger.error(resourceBundle.getString("service.input.employee")+ e);
//            throw new EmployeeException(resourceBundle.getString("service.input.employee"));
//        }
    }

    public EmployeeAddress callInsertEmployeeAddress(EmployeeAddress permanentAddress, EmployeeAddress temporaryAddress) throws SQLException , EmployeeException{
//        try {

            return employeeRepository.insertEmployeeAddress(permanentAddress,temporaryAddress);
//        } catch (Exception e) {
//            logger.error(resourceBundle.getString("service.input.permanent")+ e);
//            throw new EmployeeException(resourceBundle.getString("service.input.permanent"));
//        }
    }


    public List<EmployeeDetails> callFilterEmployeeProfilesByPincode(int pincode) throws SQLException {
//        try {

            return employeeRepository.filterEmployeeProfilesByPincode(pincode);
//        } catch (Exception e) {
//            logger.error(resourceBundle.getString("service.input.temporary")+ e);
//            throw new EmployeeException(resourceBundle.getString("service.input.temporary"));
//        }
    }


    public List<EmployeeDetails> callOutputEmployeeProfile() throws SQLException {
//        try {

            return employeeRepository.outputEmployeeProfile();
//        } catch (Exception e) {
//            logger.error(resourceBundle.getString("service.output.temporary")+ e);
//            throw new EmployeeException(resourceBundle.getString("service.output.temporary"));
//        }
    }
}
