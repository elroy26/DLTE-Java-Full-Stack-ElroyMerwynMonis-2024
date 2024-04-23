package app.backend.middleware;

import app.backend.entity.EmployeeAddress;
import app.backend.entity.EmployeeDetails;
import app.backend.exceptions.EmployeeException;
import app.backend.remotes.EmployeeRepository;

import app.backend.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeDatabaseRepository implements EmployeeRepository {
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDatabaseRepository.class);
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("employee");
    private static Integer empId;


    public EmployeeDatabaseRepository(Connection connection) {
        this.connection=connection;
    }

    @Override
    public EmployeeDetails insertEmployeeDetails(EmployeeDetails details) throws SQLException {
        PreparedStatement employeeStatement = null;
        try {
            Validation validation=new Validation();
            if (validation.validateEmployeeDetails(details)==true){
                empId=details.getEmployeeID();
                String employeeInsertQuery = "INSERT INTO Employee_Details (employee_id, FirstName, MiddleName, LastName, email, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
                employeeStatement = connection.prepareStatement(employeeInsertQuery);
                employeeStatement.setInt(1, details.getEmployeeID());
                employeeStatement.setString(2, details.getEmployeeFirstName());
                employeeStatement.setString(3, details.getEmployeeMiddleName());
                employeeStatement.setString(4, details.getEmployeeLastName());
                employeeStatement.setString(5, details.getEmail());
                employeeStatement.setLong(6, details.getPhoneNumber());
                int affectedRows = employeeStatement.executeUpdate();
                if (affectedRows > 0) {
                    logger.info(resourceBundle.getString("db.repo.insertEmployeeOk"));
                } else {
                    logger.error(resourceBundle.getString("service.input.employee"));
                    throw new EmployeeException(resourceBundle.getString("service.input.employee"));
                }
            }else{
                logger.warn("Error in employee details");
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            logger.error("Constraint violation: " + e.getMessage());
            throw new SQLException(e);
        } catch (SQLSyntaxErrorException e) {
            logger.error("SQL syntax error: " + e.getMessage());
            throw new SQLException(e);
        } catch (DataTruncation e) {
            logger.error("Data truncation: " + e.getMessage());
            throw new SQLException(e);
        } catch (SQLException e) {
            logger.error("Other SQL Exception: " + e.getMessage());
            throw new SQLException(e);
        } catch (Exception e) {
            logger.error("Unexpected exception occurred: " + e.getMessage());
            throw new SQLException(e);
        }finally {
            if (employeeStatement != null) {
                try {
                    employeeStatement.close();
                } catch (SQLException e) {
                    logger.error(resourceBundle.getString("db.repo.preparedStatement"), e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error closing connection: " + e.getMessage());
                }
            }
        }

        return null;
    }

    @Override
    public EmployeeAddress insertEmployeeAddress(EmployeeAddress permanentAddress,EmployeeAddress temporaryAddress) throws SQLException {
        PreparedStatement addressStatement = null;

        try {
            Validation validation=new Validation();
            if (validation.validateEmployeeAddress(permanentAddress,temporaryAddress)==true){
                permanentAddress.setEmployeeID(empId);
                temporaryAddress.setEmployeeID(empId);
                String addressInsertQuery = "INSERT INTO Employee_Addresses (employee_id, house_name, street_name, city, residing_state, pincode, isTemporary) VALUES (?, ?, ?, ?, ?, ?, ?)";
                addressStatement = connection.prepareStatement(addressInsertQuery);
//                addressStatement.setInt(1, permanentAddress.getEmployeeID());
//                addressStatement.setString(2, permanentAddress.getHouseName());
//                addressStatement.setString(3, permanentAddress.getStreetName());
//                addressStatement.setString(4, permanentAddress.getCity());
//                addressStatement.setString(5, permanentAddress.getState());
//                addressStatement.setInt(6, permanentAddress.getPincode());
//
//                addressStatement.setString(7, temporaryAddress.getHouseName());
//                addressStatement.setString(8, temporaryAddress.getStreetName());
//                addressStatement.setString(9, temporaryAddress.getCity());
//                addressStatement.setString(10, temporaryAddress.getState());
//                addressStatement.setInt(11, temporaryAddress.getPincode());

                // Insert permanent address
                addressStatement.setInt(1, permanentAddress.getEmployeeID());
                addressStatement.setString(2, permanentAddress.getHouseName());
                addressStatement.setString(3, permanentAddress.getStreetName());
                addressStatement.setString(4, permanentAddress.getCity());
                addressStatement.setString(5, permanentAddress.getState());
                addressStatement.setInt(6, permanentAddress.getPincode());
                addressStatement.setString(7, "false");
                addressStatement.executeUpdate();

                // Insert temporary address
                addressStatement.setInt(1, temporaryAddress.getEmployeeID());
                addressStatement.setString(2, temporaryAddress.getHouseName());
                addressStatement.setString(3, temporaryAddress.getStreetName());
                addressStatement.setString(4, temporaryAddress.getCity());
                addressStatement.setString(5, temporaryAddress.getState());
                addressStatement.setInt(6, temporaryAddress.getPincode());
                addressStatement.setString(7, "true");
//                addressStatement.executeUpdate();

                int affectedRows = addressStatement.executeUpdate();

                if (affectedRows > 0) {
                    logger.info(resourceBundle.getString("db.repo.insertPermanentOK"));
                } else {
                    logger.error(resourceBundle.getString("service.input.permanent"));
                    throw new EmployeeException(resourceBundle.getString("service.input.permanent"));
                }
            }else {
                logger.warn("Error in employee details");
                throw new EmployeeException("Error in employee details");
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            logger.error("Constraint violation: " + e.getMessage());
            throw new SQLException(e);

        } catch (SQLSyntaxErrorException e) {
            logger.error("SQL syntax error: " + e.getMessage());
            throw new SQLException(e);

        } catch (DataTruncation e) {
            logger.error("Data truncation: " + e.getMessage());
            throw new SQLException(e);

        } catch (SQLException e) {
            logger.error("Other SQL Exception: " + e.getMessage());
            throw new SQLException(e);

        } catch (Exception e) {
            logger.error("Unexpected exception occurred: " + e.getMessage());
            throw new SQLException(e);

        } finally {
        if (addressStatement != null) {
            try {
                addressStatement.close();
            } catch (SQLException e) {
                logger.error(resourceBundle.getString("db.repo.preparedStatement"), e.getMessage());
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error closing connection: " + e.getMessage());
            }
        }
        }
        return null;
    }


    @Override
    public List<EmployeeDetails> outputEmployeeProfile() throws SQLException {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
        try {

            String query = "SELECT e.employee_ID, e.FirstName, e.MiddleName, e.LastName, e.email, e.phoneNumber, " +
                    "a.House_Name, a.Street_Name, a.City, a.Residing_State, a.pincode,a.isTemporary " +
                    "FROM employee_details e " +
                    "JOIN employee_addresses a ON e.employee_ID = a.employee_id ";


            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EmployeeDetails employeeDetails = new EmployeeDetails();
                employeeDetails.setEmployeeID(resultSet.getInt(1));
                employeeDetails.setEmployeeFirstName(resultSet.getString(2));
                employeeDetails.setEmployeeMiddleName(resultSet.getString(3));
                employeeDetails.setEmployeeLastName(resultSet.getString(4));
                employeeDetails.setEmail(resultSet.getString(5));
                employeeDetails.setPhoneNumber(resultSet.getLong(6));

                // Creating permanent address object and setting its details
                EmployeeAddress permanentAddress = new EmployeeAddress();
                permanentAddress.setHouseName(resultSet.getString(7));
                permanentAddress.setStreetName(resultSet.getString(8));
                permanentAddress.setCity(resultSet.getString(9));
                permanentAddress.setState(resultSet.getString(10));
                permanentAddress.setPincode(resultSet.getInt(11));

                EmployeeAddress temporaryAddress = new EmployeeAddress();
                if (resultSet.next()) {
                    // Creating temporary address object and setting its details
                    temporaryAddress.setHouseName(resultSet.getString(7));
                    temporaryAddress.setStreetName(resultSet.getString(8));
                    temporaryAddress.setCity(resultSet.getString(9));
                    temporaryAddress.setState(resultSet.getString(10));
                    temporaryAddress.setPincode(resultSet.getInt(11));
                }
                employeeDetails.setEmployeeTemporaryAddress(temporaryAddress);
                employeeDetails.setEmployeePermanentAddress(permanentAddress);
                employeeDetailsList.add(employeeDetails);
                }

    } catch (SQLIntegrityConstraintViolationException e) {
            logger.error("Constraint violation: " + e.getMessage());
            throw new SQLException(e);

        }
        catch (SQLSyntaxErrorException e) {
            logger.error("SQL syntax error: " + e.getMessage());
            throw new SQLException(e);

        }
        catch (DataTruncation e) {
            logger.error("Data truncation: " + e.getMessage());
            throw new SQLException(e);

        }
        catch (SQLException e) {
            logger.error("Other SQL Exception: " + e.getMessage());
            throw new SQLException(e);

        }finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Error closing result set: " + e.getMessage());
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error("Error closing prepared statement: " + e.getMessage());
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error closing connection: " + e.getMessage());
            }
        }
        }
        return employeeDetailsList;
    }
    @Override
    public List<EmployeeDetails> filterEmployeeProfilesByPincode(int pincode) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();

        try {
            String query = "SELECT e.employee_ID, e.FirstName, e.MiddleName, e.LastName, e.email, e.phoneNumber, " +
                    "a.House_Name, a.Street_Name, a.City, a.Residing_State, a.pincode,a.isTemporary " +
                    "FROM employee_details e " +
                    "JOIN employee_addresses a ON e.employee_ID = a.employee_id "+
                    "WHERE a.pincode = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pincode);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EmployeeDetails employeeDetails = new EmployeeDetails();
                employeeDetails.setEmployeeID(resultSet.getInt(1));
                employeeDetails.setEmployeeFirstName(resultSet.getString(2));
                employeeDetails.setEmployeeMiddleName(resultSet.getString(3));
                employeeDetails.setEmployeeLastName(resultSet.getString(4));
                employeeDetails.setEmail(resultSet.getString(5));
                employeeDetails.setPhoneNumber(resultSet.getLong(6));

                EmployeeAddress permanentAddress = new EmployeeAddress();
                permanentAddress.setHouseName(resultSet.getString(7));
                permanentAddress.setStreetName(resultSet.getString(8));
                permanentAddress.setCity(resultSet.getString(9));
                permanentAddress.setState(resultSet.getString(10));
                permanentAddress.setPincode(resultSet.getInt(11));

                EmployeeAddress temporaryAddress = new EmployeeAddress();
                if(resultSet.next()) {
                    temporaryAddress.setHouseName(resultSet.getString(7));
                    temporaryAddress.setStreetName(resultSet.getString(8));
                    temporaryAddress.setCity(resultSet.getString(9));
                    temporaryAddress.setState(resultSet.getString(10));
                    temporaryAddress.setPincode(resultSet.getInt(11));
                }
                employeeDetails.setEmployeePermanentAddress(permanentAddress);
                employeeDetails.setEmployeeTemporaryAddress(temporaryAddress);

                employeeDetailsList.add(employeeDetails);
            }

        } catch (SQLException e) {
            logger.error("Failed to fetch employee profiles by pincode: " + e.getMessage());
            throw new SQLException(e);

        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error("Error closing result set: " + e.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Error closing prepared statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error closing connection: " + e.getMessage());
                }
            }
        }
        return employeeDetailsList;
    }

}
