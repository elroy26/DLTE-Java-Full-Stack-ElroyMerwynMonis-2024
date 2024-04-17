package mybank.insurance.webservice.rest.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Insurance added successfully"),
        @ApiResponse(responseCode = "409", description = "Insurance already exists for the customer"),
        @ApiResponse(responseCode = "204", description = "No Insurance data exists"),
        @ApiResponse(responseCode = "403", description = "Customer is inactive"),
        @ApiResponse(responseCode = "500", description = "Internal server error"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
})
public class InsuranceController {
//http://localhost:8082/swagger-ui/index.html
    //{{baseUrl}}/insurance/availed
//http://localhost:7001/webservice-0.0.1-SNAPSHOT/insurance/availed/
//    http://localhost:7001/webservice-0.0.1-SNAPSHOT/v3/api-docs
//http://localhost:8082/insurance/availed
//{
//    "insurancePremium": "2000.0",
//        "insuranceKeyBenefits": "hign interest rates",
//        "insuranceLifetime": 2,
//        "customerId": 20012004,
//        "insuranceId": 90019002,
//        "insuranceName": "Maxlife",
//        "insuranceType": "Health",
//        "insuranceCoverage": "10000.0"
//}
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger = LoggerFactory.getLogger(InsuranceController.class);

    @Autowired
    private InsuranceAvailedRepository availedDbRepo;
    @Autowired
    private CustomerRepository customerRepository;
//    @Autowired
//    private InsuranceAvailableRepository repo;

//    @GetMapping("/")
//    public List<InsuranceAvailable> readAll() throws SQLException {
//        return repo.callAllInsuranceAvailable();
//    }

    @PostMapping("/availed")
    public ResponseEntity<Object> save(@Valid @RequestBody InsuranceAvailed availed){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
//        System.out.println(username);
        // Retrieve the customer ID associated with the logged-in customer name from the database
        Integer loggedInCustomerId =customerRepository.findByCustomerId(username);
//        System.out.println(loggedInCustomerId);
        InsuranceAvailed availed1=null;
        if (!loggedInCustomerId.equals(availed.getCustomerId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resourceBundle.getString("customer.avail.iderror"));
        }
        try {
            availed1=availedDbRepo.callSaveInsuranceAvailed(availed);

//            System.out.println(availed.getCustomerId());

            return ResponseEntity.ok(availed1);

        }catch (SQLException sqlException) {
            // Handle SQL exception
            logger.error(resourceBundle.getString("availed.sql.error")+ sqlException);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resourceBundle.getString("availed.sql.error") + sqlException.getMessage());
        } catch (InsuranceAvailedException availedException) {
            // Handle InsuranceAvailedException based on error codes
            if (availedException.getMessage().contains("-20001")) {
                logger.error(resourceBundle.getString("availed.20001.error")+ availedException);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resourceBundle.getString("availed.20001.error"));
            } else if (availedException.getMessage().contains("-20002")) {
                logger.warn(resourceBundle.getString("availed.20002.error")+ availedException);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(resourceBundle.getString("availed.20002.error") );
            } else if (availedException.getMessage().contains("-20003")) {
                logger.error(resourceBundle.getString("availed.20003.error")+ availedException);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resourceBundle.getString("availed.20003.error") );
            } else if (availedException.getMessage().contains("-20004")) {
                logger.error(resourceBundle.getString("availed.20004.error")+ availedException);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("availed.20004.error"));
            } else {
                logger.error(resourceBundle.getString("availed.error")+ availedException.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("availed.error"));
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
