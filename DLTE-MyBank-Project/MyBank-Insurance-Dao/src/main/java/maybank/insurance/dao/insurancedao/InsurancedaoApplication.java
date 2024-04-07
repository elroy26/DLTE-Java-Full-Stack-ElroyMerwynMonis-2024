package maybank.insurance.dao.insurancedao;

import maybank.insurance.dao.insurancedao.exceptions.InsuranceAvailableException;
import maybank.insurance.dao.insurancedao.services.InsuranceAvailableDbRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

@SpringBootApplication
public class InsurancedaoApplication {

    public static void main(String[] args) throws SQLSyntaxErrorException {
        //SpringApplication.run(InsurancedaoApplication.class, args);
        ConfigurableApplicationContext context=  SpringApplication.run(InsurancedaoApplication.class, args);

        InsuranceAvailableDbRepo availableDbRepo=context.getBean(InsuranceAvailableDbRepo.class);
        try{
            System.out.println(availableDbRepo.callAllInsuranceAvailable());
        }
        catch (InsuranceAvailableException | SQLException exception){
            System.out.println(exception);
        }
    }

}
