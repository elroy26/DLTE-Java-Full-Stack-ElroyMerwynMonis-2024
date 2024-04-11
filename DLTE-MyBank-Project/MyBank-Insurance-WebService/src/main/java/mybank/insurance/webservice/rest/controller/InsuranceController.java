package mybank.insurance.webservice.rest.controller;

import maybank.insurance.dao.entity.InsuranceAvailed;
import maybank.insurance.dao.exceptions.InsuranceAvailedException;
import maybank.insurance.dao.remotes.InsuranceAvailedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.ResourceBundle;

@RestController
@RequestMapping("/insurance")
@ComponentScan("maybank.insurance.dao")
public class InsuranceController {
//http://localhost:8082/swagger-ui/index.html
    //{{baseUrl}}/insurance/availed
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
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger = LoggerFactory.getLogger(InsuranceController.class);

    @Autowired
    private InsuranceAvailedRepository availableDbRepo;

    @PostMapping("/availed")
    public ResponseEntity<Object> save(@Valid @RequestBody InsuranceAvailed availed){
        InsuranceAvailed availed1=null;
        try {
            availed1=availableDbRepo.callSaveInsuranceAvailed(availed);
            return ResponseEntity.ok(availed1);
        }catch (SQLException sqlException) {
            // Handle SQL exception
            logger.error(resourceBundle.getString("availed.sql.error")+ sqlException);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resourceBundle.getString("availed.sql.error") + sqlException.getMessage());
        } catch (InsuranceAvailedException availedException) {
            // Handle InsuranceAvailedException based on error codes
            if (availedException.getMessage().contains("-20001")) {
                logger.error(resourceBundle.getString("availed.20001.error")+ availedException);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("availed.20001.error"));
            } else if (availedException.getMessage().contains("-20002")) {
                logger.warn(resourceBundle.getString("availed.20002.error")+ availedException);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("availed.20002.error") );
            } else if (availedException.getMessage().contains("-20003")) {
                logger.error(resourceBundle.getString("availed.20003.error")+ availedException);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("availed.20003.error") );
            } else if (availedException.getMessage().contains("-20004")) {
                logger.error(resourceBundle.getString("availed.20004.error")+ availedException);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("availed.20004.error"));
            } else {
                logger.error(resourceBundle.getString("availed.error")+ availedException);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("availed.error")+availedException.getMessage());
            }
        }
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
