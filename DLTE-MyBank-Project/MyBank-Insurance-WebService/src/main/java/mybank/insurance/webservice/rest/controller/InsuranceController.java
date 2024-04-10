package mybank.insurance.webservice.rest.controller;

import maybank.insurance.dao.entity.InsuranceAvailed;
import maybank.insurance.dao.exceptions.InsuranceAvailedException;
import maybank.insurance.dao.remotes.InsuranceAvailedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/insurance")
@ComponentScan("maybank.insurance.dao")
public class InsuranceController {
//http://localhost:7001/webservice-0.0.1-SNAPSHOT/insurance/availed/
//http://localhost:8082/insurance/availed
//{
//    "insurancePremium": "2000.0",
//        "insuranceKeyBenefits": "hign interest rates",
//        "insuranceLifetime": 2,
//        "customerId": 123,
//        "insuranceId": 90019002,
//        "insuranceName": "Maxlife",
//        "insuranceType": "Health",
//        "insuranceCoverage": "10000.0"
//}
    @Autowired
    private InsuranceAvailedRepository availableDbRepo;

    @PostMapping("/availed")
    public ResponseEntity<Object> save(@Valid @RequestBody InsuranceAvailed availed){
        InsuranceAvailed availed1=null;
        try {
            availed1=availableDbRepo.callSaveInsuranceAvailed(availed);
        } catch (SQLException e) {
            System.out.println(e);
        }catch (InsuranceAvailedException e){
            System.out.println(e);
        }

        return ResponseEntity.ok(availed1);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
