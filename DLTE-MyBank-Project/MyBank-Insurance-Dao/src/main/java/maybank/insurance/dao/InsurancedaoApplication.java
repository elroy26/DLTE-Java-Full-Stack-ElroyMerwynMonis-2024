package maybank.insurance.dao;

import maybank.insurance.dao.entity.InsuranceAvailed;
import maybank.insurance.dao.exceptions.InsuranceAvailableException;
import maybank.insurance.dao.exceptions.InsuranceAvailedException;
import maybank.insurance.dao.services.CustomerDbRepo;
import maybank.insurance.dao.services.InsuranceAvailableDbRepo;
import maybank.insurance.dao.services.InsuranceAvailedDbRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class InsurancedaoApplication {

    public static void main(String[] args) throws SQLSyntaxErrorException {
        //SpringApplication.run(InsurancedaoApplication.class, args);
        ConfigurableApplicationContext context=  SpringApplication.run(InsurancedaoApplication.class, args);

//        InsuranceAvailableDbRepo availableDbRepo=context.getBean(InsuranceAvailableDbRepo.class);
//        try{
//            System.out.println(availableDbRepo.callAllInsuranceAvailable());
//        }
//        catch (InsuranceAvailableException | SQLException exception){
//            System.out.println(exception);
//        }

//        InsuranceAvailed insuranceAvailed = Stream.of(
//                new InsuranceAvailed(123, 90019002, "Health", "MaxLife", "High Interest Rates", 3, 10000.0, 20000.0)
//        ).collect(Collectors.toList()).get(0);


//        InsuranceAvailedDbRepo availedDbRepo=context.getBean(InsuranceAvailedDbRepo.class);
//        try {
//            System.out.println(availedDbRepo.callSaveInsuranceAvailed(insuranceAvailed));
//        }catch (InsuranceAvailedException | SQLException e){
//            System.out.println(e);
//        }
        CustomerDbRepo dbRepo=context.getBean(CustomerDbRepo.class);
        String name="raj";
        System.out.println(dbRepo.findByCustomerId(name));
    }

}
