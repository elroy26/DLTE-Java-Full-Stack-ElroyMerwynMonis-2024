package maybank.insurance.dao.exceptions;

public class InsuranceAvailedException extends RuntimeException{
    public InsuranceAvailedException(String message){
        super(message);
    }
}
