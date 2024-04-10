package maybank.insurance.dao.exceptions;

public class CustomerException extends RuntimeException {
    public CustomerException(String message){
        super(message);
    }
}
