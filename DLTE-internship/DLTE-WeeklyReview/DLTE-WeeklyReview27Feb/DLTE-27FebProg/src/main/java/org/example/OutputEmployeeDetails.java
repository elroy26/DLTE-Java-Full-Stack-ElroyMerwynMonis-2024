
package org.example;

public  class OutputEmployeeDetails implements OutputEmployeeDetailsInterface{

    @Override
    public void outputEmployee(Employee employee) {

        System.out.println("First Name:"+employee.getEmployeeFirstName()+
                "  Middle Name:"+employee.getEmployeeMiddleName()+
                "  Last Name:"+employee.getEmployeeLastName());
        System.out.println("Email:"+employee.getEmail());
        System.out.println("Phone number:"+employee.getPhoneNumber());

    }

    @Override
    public void outputPermanentAddress(EmployeePermanentAddress employeePermanentAddress) {
        System.out.println("\n------------Your Permanent Address is-------------\n"+
                "House Name:"+employeePermanentAddress.getPermanentHouseName()+
                "\nStreet Name:"+employeePermanentAddress.getPermanentStreetName()+
                "\nCity Name:"+employeePermanentAddress.getPermanentCity()+
                "\nState Name:"+employeePermanentAddress.getPermanentState()+
                "\nPincode:"+employeePermanentAddress.getPincodePermanent());

    }

    @Override
    public void outputTemporaryAddress(EmployeeTemporaryAddress employeeTemporaryAddress) {
        System.out.println("\n---------Your Temporary Address is-----------\n"+
                        "House Name:"+employeeTemporaryAddress.getTemporaryHouseName()+
                "\nStreet Name:"+employeeTemporaryAddress.getTemporaryStreetName()+
                "\nCity Name:"+employeeTemporaryAddress.getTemporaryCity()+
                "\nState Name:"+employeeTemporaryAddress.getTemporaryState()+
                "\nPincode:"+employeeTemporaryAddress.getPincodeTemporary());
    }

}
