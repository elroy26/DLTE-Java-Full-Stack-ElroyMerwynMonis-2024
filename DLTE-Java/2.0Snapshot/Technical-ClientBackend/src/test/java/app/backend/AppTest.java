package app.backend;

//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//import app.backend.entity.EmployeeAddress;
//import app.backend.entity.EmployeeDetails;
//import app.backend.remotes.EmployeeRepository;
//import app.backend.services.EmployeeService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(MockitoJUnitRunner.class)
public class AppTest 
{
//    @Mock
//    private EmployeeRepository mockRepository;
//
//    @InjectMocks
//    private EmployeeService employeeService;
//
//    @Test
//    public void testCallInsertEmployeeDetails_Success() {
//
//        EmployeeDetails details = new EmployeeDetails();
//        details.setEmployeeID(12345);
//        details.setEmployeeFirstName("elroy");
//        details.setEmployeeMiddleName("mon");
//        details.setEmployeeLastName("Smith");
//        details.setEmail("elroy.smith@example.com");
//        details.setPhoneNumber(1234567890L);
//
//        when(mockRepository.insertEmployeeDetails(details)).thenReturn(details);
//
//        EmployeeDetails result = employeeService.callInsertEmployeeDetails(details);
//
//        assertNotNull(result);
//    }
//
//    @Test
//    public void testCallInsertEmployeePermanentAddress_Success() {
//        EmployeeAddress permanentAddress = new EmployeeAddress();
//        permanentAddress.setEmployeeID(12345); // Example employee ID
//        permanentAddress.setPermanentHouseName("123 Maint");
//        permanentAddress.setPermanentStreetName("Downtown Ave");
//        permanentAddress.setPermanentCity("Metropolis");
//        permanentAddress.setPermanentState("State");
//        permanentAddress.setPincodePermanent(123456);
//
//        when(mockRepository.insertEmployeePermanentAddress(permanentAddress)).thenReturn(permanentAddress);
//
//        EmployeeAddress result = employeeService.callInsertEmployeePermanentAddress(permanentAddress);
//
//        assertNotNull(result);
//    }
//
//    @Test
//    public void testCallInsertEmployeeTemporaryAddress_Success() {
//        // Set up temporary address...
//        EmployeeTemporaryAddress temporaryAddress = new EmployeeTemporaryAddress();
//        temporaryAddress.setEmployeeID(12345);
//        temporaryAddress.setTemporaryHouseName("456 delhi");
//        temporaryAddress.setTemporaryStreetName("Uptown Ave");
//        temporaryAddress.setTemporaryCity("udupi");
//        temporaryAddress.setTemporaryState("State");
//        temporaryAddress.setPincodeTemporary(654321);
//
//        when(mockRepository.insertEmployeeTemporaryAddress(temporaryAddress)).thenReturn(temporaryAddress);
//
//        EmployeeTemporaryAddress result = employeeService.callInsertEmployeeTemporaryAddress(temporaryAddress);
//
//        assertNotNull(result);
//
//    }
//
//    @Test
//    public void testCallFilterEmployeeProfilesByPincode_Success() {
//        int pincode = 12345;
//
//        EmployeeDetails employee1 = new EmployeeDetails();
//        employee1.setEmployeeID(12345);
//        employee1.setEmployeeFirstName("John");
//        employee1.setEmployeeLastName("Doe");
//        employee1.setEmail("john.doe@example.com");
//        employee1.setPhoneNumber(1234567890L);
//
//        EmployeeAddress permanentAddress = new EmployeeAddress();
//        permanentAddress.setPermanentHouseName("123 Maint");
//        permanentAddress.setPermanentStreetName("Downtown Ave");
//        permanentAddress.setPermanentCity("Metropolis");
//        permanentAddress.setPermanentState("State");
//        permanentAddress.setPincodePermanent(12345);
//        employee1.setEmployeeAddress(permanentAddress);
//
//
//        EmployeeTemporaryAddress temporaryAddress1 = new EmployeeTemporaryAddress();
//        temporaryAddress1.setTemporaryHouseName("123 Street");
//        temporaryAddress1.setTemporaryStreetName("Main St");
//        temporaryAddress1.setTemporaryCity("City");
//        temporaryAddress1.setTemporaryState("State");
//        temporaryAddress1.setPincodeTemporary(12345);
//        employee1.setEmployeeTemporaryAddress(temporaryAddress1);
//
//        List<EmployeeDetails> employeeList = new ArrayList<>();
//        employeeList.add(employee1);
//
//        when(mockRepository.filterEmployeeProfilesByPincode(pincode)).thenReturn(employeeList);
//
//        List<EmployeeDetails> result = employeeService.callFilterEmployeeProfilesByPincode(pincode);
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(employee1, result.get(0));
//
//    }
//
//    @Test
//    public void testCallOutputEmployeeProfile_Success() {
//        EmployeeDetails employee1 = new EmployeeDetails();
//        employee1.setEmployeeID(12345);
//        employee1.setEmployeeFirstName("John");
//        employee1.setEmployeeLastName("Doe");
//        employee1.setEmail("john.doe@example.com");
//        employee1.setPhoneNumber(1234567890L);
//
//        EmployeeAddress permanentAddress = new EmployeeAddress();
//        permanentAddress.setPermanentHouseName("123 Maint");
//        permanentAddress.setPermanentStreetName("Downtown Ave");
//        permanentAddress.setPermanentCity("Metropolis");
//        permanentAddress.setPermanentState("State");
//        permanentAddress.setPincodePermanent(12345);
//        employee1.setEmployeeAddress(permanentAddress);
//
//
//        EmployeeTemporaryAddress temporaryAddress1 = new EmployeeTemporaryAddress();
//        temporaryAddress1.setTemporaryHouseName("123 Street");
//        temporaryAddress1.setTemporaryStreetName("Main St");
//        temporaryAddress1.setTemporaryCity("City");
//        temporaryAddress1.setTemporaryState("State");
//        temporaryAddress1.setPincodeTemporary(12345);
//        employee1.setEmployeeTemporaryAddress(temporaryAddress1);
//
//        List<EmployeeDetails> employeeList = new ArrayList<>();
//        employeeList.add(employee1);
//
//        when(mockRepository.outputEmployeeProfile()).thenReturn(employeeList);
//
//        List<EmployeeDetails> result = employeeService.callOutputEmployeeProfile();
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(employee1, result.get(0));
//    }
}
