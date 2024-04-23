package app.employee.dao.entity;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
public class EmployeeHttpResponse extends Throwable {
    private EmployeeDetails details;
    private HttpStatus status;
//    private HttpMessage message;
    private String message;

    public EmployeeHttpResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public EmployeeHttpResponse(EmployeeDetails details, HttpStatus status, String message) {
        this.details = details;
        this.status = status;
        this.message = message;
    }

    public EmployeeHttpResponse() {

    }
}
