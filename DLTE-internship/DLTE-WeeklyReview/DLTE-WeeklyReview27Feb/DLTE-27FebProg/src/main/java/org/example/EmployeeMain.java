package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeMain  {

    static InputEmployeeDetails inputEmployeeDetails=new InputEmployeeDetails();
    static OutputEmployeeDetails outputEmployeeDetails=new OutputEmployeeDetails();
    static FilterOperations filterOperations=new FilterOperations();
    static FileStorage fileStorage=new FileStorage();
    static List<Employee> employeeDetails= new ArrayList<>();
    static List<EmployeePermanentAddress> permanentAddress = new ArrayList<>();
    static List<EmployeeTemporaryAddress> temporaryAddress = new ArrayList<>();
    static List<Object> employeeData=new ArrayList<>();
    static List<Object> employeeDataSheet;

    public static void outputDetails() {

//        if (employeeDetails.size()!=0 ){
//            for (int i = 0; i < employeeDetails.size(); i++) {
//                System.out.println("--------Your Employee"+(i+1)+" Profile Details are-----------");
//                Employee currentEmployee = employeeDetails.get(i);
//                EmployeePermanentAddress currentPermanentAddress = permanentAddress.get(i);
//                EmployeeTemporaryAddress currentTemporaryAddress = temporaryAddress.get(i);
//                outputEmployeeDetails.outputEmployee(currentEmployee);
//                outputEmployeeDetails.outputPermanentAddress(currentPermanentAddress);
//                outputEmployeeDetails.outputTemporaryAddress(currentTemporaryAddress);
//            }
//        }else {
//            System.out.println("No Employee Details to display.\n");;
//        }
        List<?> employeeData = fileStorage.readEmployeeFile();
        for (Object entry : employeeData) {
            System.out.println(entry);
        }

    }

    public static void enterDetails() {

        inputEmployeeDetails.employee();
        inputEmployeeDetails.employeePermanentAddress();
        inputEmployeeDetails.employeeTemporaryAddress();
        employeeDetails.add(inputEmployeeDetails.getEmployee().get(0));
        permanentAddress.add( inputEmployeeDetails.getEmployeePermanentAddress().get(0));
        temporaryAddress.add(inputEmployeeDetails.getEmployeeTemporaryAddress().get(0));
        employeeData.add(employeeDetails);
        employeeData.add(permanentAddress);
        employeeData.add(temporaryAddress);
        employeeDataSheet = fileStorage.readEmployeeFile();
        employeeDataSheet.add(employeeData);
        fileStorage.writeEmployeeFile( employeeDataSheet);

    }
    public static List<Object> filterData(Integer pincode){
        fileStorage.readEmployeeFile();
        return filterOperations.filterByPermanentPincode(employeeDataSheet,pincode);
    }


}
