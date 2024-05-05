package mybank.insurance.webservice.rest.controller;

import maybank.insurance.dao.entity.InsuranceAvailed;
import maybank.insurance.dao.exceptions.InsuranceAvailedException;
import maybank.insurance.dao.remotes.CustomerRepository;
import maybank.insurance.dao.remotes.InsuranceAvailedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    ResourceBundle resourceBundle = ResourceBundle.getBundle("insurance");
    Logger logger = LoggerFactory.getLogger(InsuranceController.class);

    @Autowired
    private InsuranceAvailedRepository availedDbRepo;
    @Autowired
    private CustomerRepository customerRepository;
    @PostMapping("/availed")
    public ResponseEntity<Object> save(@Valid @RequestBody InsuranceAvailed availed){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Retrieve the customer ID associated with the logged-in customer name from the database
        Integer loggedInCustomerId =customerRepository.findByCustomerId(username);
        InsuranceAvailed availed1=null;
        if (!loggedInCustomerId.equals(availed.getCustomerId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resourceBundle.getString("customer.avail.iderror"));
        }
        try {
            availed1=availedDbRepo.callSaveInsuranceAvailed(availed);
            return ResponseEntity.ok(availed1);

        }catch (SQLException sqlException) {
            // Handle SQL exception
            logger.error(resourceBundle.getString("availed.sql.error")+ sqlException);
            return ResponseEntity.ok().body(resourceBundle.getString("availed.sql.error") + sqlException.getMessage());
        } catch (InsuranceAvailedException availedException) {
            // Handle InsuranceAvailedException based on error codes
            if (availedException.getMessage().contains("-20001")) {
                logger.error(resourceBundle.getString("availed.20001.error")+ availedException);
                return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("availed.20001.error"));
            } else if (availedException.getMessage().contains("-20002")) {
                logger.warn(resourceBundle.getString("availed.20002.error")+ availedException);
                return ResponseEntity.ok(resourceBundle.getString("availed.20002.error"));
//                return ResponseEntity.status(HttpStatus.CONFLICT).body(resourceBundle.getString("availed.20002.error"));
            } else if (availedException.getMessage().contains("-20003")) {
                logger.error(resourceBundle.getString("availed.20003.error")+ availedException);
                return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("availed.20003.error") );
            } else if (availedException.getMessage().contains("-20004")) {
                logger.error(resourceBundle.getString("availed.20004.error")+ availedException);
                return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("availed.20004.error"));
            } else {
                logger.error(resourceBundle.getString("availed.error")+ availedException.getMessage());
                return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("availed.error"));
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
            switch (fieldName){
            //customer
                case "customerId":
                    errors.put("1000",errorMessage);
                    break;
                case "customerName":
                    errors.put("1002",errorMessage);
                    break;
                case "customerAddress":
                    errors.put("1003",errorMessage);
                    break;
                case "customerStatus":
                    errors.put("1004",errorMessage);
                    break;
                case "customerContact":
                    errors.put("1005",errorMessage);
                    break;
                case "username":
                    errors.put("1006",errorMessage);
                    break;
                case "password":
                    errors.put("1007",errorMessage);
                    break;
            //insurance
                case "insuranceId":
                    errors.put("1008",errorMessage);
                    break;
                case "insuranceType":
                    errors.put("1009",errorMessage);
                    break;
                case "insuranceName":
                    errors.put("1010",errorMessage);
                    break;
                case "insuranceKeyBenefits":
                    errors.put("10011",errorMessage);
                    break;
                case "insuranceLifetime":
                    errors.put("10012",errorMessage);
                    break;
                case "insurancePremium":
                    errors.put("10013",errorMessage);
                    break;
                case "insuranceCoverage":
                    errors.put("10014",errorMessage);
                    break;
            }
        });
        return errors;
    }

}
