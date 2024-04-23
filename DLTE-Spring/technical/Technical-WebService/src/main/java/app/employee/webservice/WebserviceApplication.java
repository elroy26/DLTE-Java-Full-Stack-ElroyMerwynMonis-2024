package app.employee.webservice;

//import app.employee.webservice.endpoint.EmployeeProfileEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class WebserviceApplication {
    private static String url="http://localhost:8084/employeeform";
    public static void main(String[] args)
    {
        SpringApplication.run(WebserviceApplication.class, args);
//        EmployeeProfileEndpoint endpoint=new EmployeeProfileEndpoint();
//        System.out.println("Webservice hosted @ "+url);
//        Endpoint.publish(url,endpoint);
    }

}
