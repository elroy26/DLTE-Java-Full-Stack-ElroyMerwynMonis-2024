package maybank.insurance.dao.services;

import maybank.insurance.dao.entity.InsuranceAvailed;
import maybank.insurance.dao.exceptions.InsuranceAvailedException;
import maybank.insurance.dao.remotes.InsuranceAvailedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Service
public class InsuranceAvailedDbRepo implements InsuranceAvailedRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public InsuranceAvailed callSaveInsuranceAvailed(InsuranceAvailed availed) throws SQLException, InsuranceAvailedException {
        String procedureCall = "{call insert_insurance_availed(?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            jdbcTemplate.update(procedureCall,
                    new Object[]{
                            availed.getCustomerId(),
                            availed.getInsuranceId(),
                            availed.getInsuranceType(),
                            availed.getInsuranceName(),
                            availed.getInsuranceKeyBenefits(),
                            availed.getInsuranceLifetime(),
                            availed.getInsurancePremium(),
                            availed.getInsuranceCoverage()
                    },
                    new int[]{
                            Types.INTEGER,
                            Types.INTEGER,
                            Types.VARCHAR,
                            Types.VARCHAR,
                            Types.VARCHAR,
                            Types.INTEGER,
                            Types.DOUBLE,
                            Types.DOUBLE
                    });
            return availed;
        }catch (DataAccessException e){
            String errorMessage = e.getMessage();
            if (errorMessage.contains("-20001")) {
                throw new SQLException("Selected Insurance type not available"+ e);
            } else if (errorMessage.contains("-20002")) {
                throw new InsuranceAvailedException("Customer has already availed this insurance"+ e);
            } else if (errorMessage.contains("-20003")) {
                throw new InsuranceAvailedException("Cannot insert insurance for inactive customer"+ e);
            } else if (errorMessage.contains("-20004")) {
                throw new InsuranceAvailedException("Customer not found"+ e);
            } else {
                throw new InsuranceAvailedException("Failed to insert insurance availed: " + errorMessage+ e);
            }
        }
    }
}
