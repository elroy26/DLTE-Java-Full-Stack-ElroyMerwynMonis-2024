package app.employee.dao.services;

import app.employee.dao.entity.EmployeeAddress;
import app.employee.dao.entity.EmployeeDetails;
import app.employee.dao.entity.EmployeeHttpResponse;
import app.employee.dao.exceptions.EmployeeException;
import app.employee.dao.remotes.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class EmployeeDbRepo implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Integer empid;
    private static final String INSERT_EMPLOYEE_QUERY = "insert into EMPLOYEE_DETAILS values(?,?,?,?,?,?)";
    private static final String INSERT_ADDRESS_QUERY = "INSERT INTO Employee_Addresses (employee_id, house_name, street_name, city, residing_state, pincode, isTemporary) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_EMPLOYEE_QUERY = "SELECT e.employee_ID, e.FirstName, e.MiddleName, e.LastName, e.email, e.phoneNumber, a.House_Name, a.Street_Name, a.City, a.Residing_State, a.pincode,a.isTemporary FROM employee_details e JOIN employee_addresses a ON e.employee_ID = a.employee_id where a.isTemporary in ('true','false')";
    private static final String FILTER_BY_PINCODE_QUERY = "SELECT e.employee_ID, e.FirstName, e.MiddleName, e.LastName, e.email, e.phoneNumber, a.House_Name, a.Street_Name, a.City, a.Residing_State, a.pincode,a.isTemporary FROM employee_details e JOIN employee_addresses a ON e.employee_ID = a.employee_id WHERE a.pincode = ?";


    @Override
    public EmployeeHttpResponse insertEmployeeDetails(EmployeeDetails details) throws SQLException, EmployeeException, EmployeeHttpResponse {
        empid=details.getEmployeeID();
        try {
            int affectedRows = jdbcTemplate.update(INSERT_EMPLOYEE_QUERY,
                    details.getEmployeeID(),
                    details.getEmployeeFirstName(),
                    details.getEmployeeMiddleName(),
                    details.getEmployeeLastName(),
                    details.getEmail(),
                    details.getPhoneNumber());

            if (affectedRows == 0) {
                throw new EmployeeHttpResponse(HttpStatus.NO_CONTENT,"Failed to insert the employee Profile records");
            }

             affectedRows = jdbcTemplate.update(INSERT_ADDRESS_QUERY,
                    details.getEmployeePermanentAddress().getEmployeeID(),
                    details.getEmployeePermanentAddress().getHouseName(),
                    details.getEmployeePermanentAddress().getStreetName(),
                    details.getEmployeePermanentAddress().getCity(),
                    details.getEmployeePermanentAddress().getState(),
                    details.getEmployeePermanentAddress().getPincode(),
                    "false");


            if (affectedRows == 0) {
                throw new EmployeeHttpResponse(HttpStatus.NO_CONTENT,"Failed to insert the employee Permanent Address records");
            }

             affectedRows = jdbcTemplate.update(INSERT_ADDRESS_QUERY,
                    details.getEmployeeTemporaryAddress().getEmployeeID(),
                    details.getEmployeeTemporaryAddress().getHouseName(),
                    details.getEmployeeTemporaryAddress().getStreetName(),
                    details.getEmployeeTemporaryAddress().getCity(),
                    details.getEmployeeTemporaryAddress().getState(),
                    details.getEmployeeTemporaryAddress().getPincode(),
                    "true");

            if (affectedRows <= 0) {
                throw new EmployeeHttpResponse(HttpStatus.NO_CONTENT,"Failed to insert the employee Temporary Address records");
            }
            return new EmployeeHttpResponse(HttpStatus.OK, "Employee details inserted successfully");

        } catch (DataAccessException | EmployeeHttpResponse e) {
            throw new EmployeeHttpResponse(HttpStatus.NO_CONTENT,"DataBase error occured"+e);
        }
    }


    @Override
    public List<EmployeeDetails> outputEmployeeProfile() throws SQLException, EmployeeException {
        List<EmployeeDetails> details=null;
        try {
            details = jdbcTemplate.query(SELECT_EMPLOYEE_QUERY, new EmployeeProfileMapper());
        } catch (DataAccessException e) {
            throw new EmployeeException("Database error occurred"+ e);
        }
        if(details.size()==0){
            throw new EmployeeException("no data found");
        }
        return details;
    }

    @Override
    public List<EmployeeDetails> filterEmployeeProfilesByPincode(int pincode) throws SQLException, EmployeeException {
        List<EmployeeDetails> details=null;
        try {
            details= jdbcTemplate.query(FILTER_BY_PINCODE_QUERY, new EmployeeProfileMapper(), pincode);
        } catch (DataAccessException e) {
            throw new EmployeeException("Database error occurred"+ e);
        }
        if(details.size()==0){
            throw new EmployeeException("no data found");
        }
        return details;
    }

    public class EmployeeProfileMapper implements RowMapper<EmployeeDetails> {

        @Override
        public EmployeeDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setEmployeeID(rs.getInt("employee_ID"));
            employeeDetails.setEmployeeFirstName(rs.getString("FirstName"));
            employeeDetails.setEmployeeMiddleName(rs.getString("MiddleName"));
            employeeDetails.setEmployeeLastName(rs.getString("LastName"));
            employeeDetails.setEmail(rs.getString("email"));
            employeeDetails.setPhoneNumber(rs.getLong("phoneNumber"));

            EmployeeAddress permanentAddress = new EmployeeAddress();
            permanentAddress.setHouseName(rs.getString("House_Name"));
            permanentAddress.setStreetName(rs.getString("Street_Name"));
            permanentAddress.setCity(rs.getString("City"));
            permanentAddress.setState(rs.getString("Residing_State"));
            permanentAddress.setPincode(rs.getInt("pincode"));

            EmployeeAddress temporaryAddress = new EmployeeAddress();
            temporaryAddress.setHouseName(rs.getString("House_Name"));
            temporaryAddress.setStreetName(rs.getString("Street_Name"));
            temporaryAddress.setCity(rs.getString("City"));
            temporaryAddress.setState(rs.getString("Residing_State"));
            temporaryAddress.setPincode(rs.getInt("pincode"));

            employeeDetails.setEmployeePermanentAddress(permanentAddress);
            employeeDetails.setEmployeeTemporaryAddress(temporaryAddress);

            return employeeDetails;
        }
    }
}
