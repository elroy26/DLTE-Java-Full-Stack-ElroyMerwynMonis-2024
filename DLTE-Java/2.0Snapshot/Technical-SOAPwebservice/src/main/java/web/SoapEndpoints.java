package web;

import javax.xml.ws.Endpoint;

public class SoapEndpoints {
    private static String url="http://localhost:7070/employeeform";

    public static void main(String[] args) {
        EmployeeSoap employeeSoap=new EmployeeSoap();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url,employeeSoap);
    }
}
