package maybank.insurance.dao.services;

import maybank.insurance.dao.entity.InsuranceAvailed;
import maybank.insurance.dao.exceptions.InsuranceAvailedException;
import maybank.insurance.dao.remotes.InsuranceAvailedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;

@Service
public class InsuranceAvailedDbRepo implements InsuranceAvailedRepository {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger = LoggerFactory.getLogger(InsuranceAvailedDbRepo.class);

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
                logger.error(resourceBundle.getString("availed.20001.error")+ errorMessage);
                throw new SQLException(resourceBundle.getString("availed.20001.error")+ errorMessage);
            } else if (errorMessage.contains("-20002")) {
                logger.warn(resourceBundle.getString("availed.20002.error")+ errorMessage);
                throw new InsuranceAvailedException(resourceBundle.getString("availed.20002.error")+ errorMessage);
            } else if (errorMessage.contains("-20003")) {
                logger.warn(resourceBundle.getString("availed.20003.error")+ errorMessage);
                throw new InsuranceAvailedException(resourceBundle.getString("availed.20003.error")+ errorMessage);
            } else if (errorMessage.contains("-20004")) {
                logger.warn(resourceBundle.getString("availed.20004.error")+ errorMessage);
                throw new InsuranceAvailedException(resourceBundle.getString("availed.20004.error")+ errorMessage);
            } else {
                logger.warn(resourceBundle.getString("availed.error") + errorMessage);
                throw new InsuranceAvailedException(resourceBundle.getString("availed.error") + errorMessage);
            }
        }
    }
}
