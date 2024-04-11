package maybank.insurance.dao.services;

import maybank.insurance.dao.entity.InsuranceAvailable;
import maybank.insurance.dao.exceptions.InsuranceAvailableException;
import maybank.insurance.dao.remotes.InsuranceAvailableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/* This service retrieves all the record from the oracle db and returns the list of the records.
 This service also throws the required exception if encountered.*/

@Service
public class InsuranceAvailableDbRepo implements InsuranceAvailableRepository {

     ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
     Logger LOGGER = LoggerFactory.getLogger(InsuranceAvailableDbRepo.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<InsuranceAvailable> callAllInsuranceAvailable() throws SQLException, InsuranceAvailableException {
        List<InsuranceAvailable> insuranceList=null;
        try {
             insuranceList = jdbcTemplate.query("select * from MYBANK_APP_INSURANCEAVAILABLE", new InsuranceMapper());
            LOGGER.debug(resourceBundle.getString("insurance.list.size"), insuranceList.size());

        } catch (DataAccessException sqlException) {
            LOGGER.error(resourceBundle.getString("insurance.sql.error"), sqlException);
            throw new SQLException(sqlException);
        }
        if(insuranceList.size()==0){
            LOGGER.warn(resourceBundle.getString("insurance.data.null"));
            throw new InsuranceAvailableException(resourceBundle.getString("insurance.data.null"));
        }
        return insuranceList;
    }

    public class InsuranceMapper implements RowMapper<InsuranceAvailable> {

        @Override
        public InsuranceAvailable mapRow(ResultSet rs, int rowNum) throws SQLException {
            InsuranceAvailable available = new InsuranceAvailable();
            available.setInsuranceId(rs.getInt(1));
            available.setInsuranceType(rs.getString(2));
            available.setInsuranceName(rs.getString(3));
            available.setInsuranceKeyBenefits(rs.getString(4));
            available.setInsuranceLifetime(rs.getInt(5));
            return available;
        }
    }
//---------------Lambda expression-------------------

//    @Override
//    public List<InsuranceAvailable> callAllInsuranceAvailable() throws SQLException, InsuranceAvailableException {
//        try {
//            List<InsuranceAvailable> insuranceList = jdbcTemplate.query(
//                    "select * from MYBANK_APP_INSURANCEAVAILABLE",
//                    (rs, rowNum) -> new InsuranceAvailable(
//                            rs.getInt(1),
//                            rs.getString(2),
//                            rs.getString(3),
//                            rs.getString(4),
//                            rs.getInt(5)
//                    )
//            );
//            LOGGER.debug(resourceBundle.getString("insurance.list.size"), insuranceList.size());
//
//            // Check if the list is empty
//            if (insuranceList.isEmpty()) {
//                LOGGER.warn(resourceBundle.getString("insurance.data.null"));
//                throw new InsuranceAvailableException(resourceBundle.getString("insurance.data.null"));
//            }
//
//            return insuranceList;
//        } catch (DataAccessException sqlException) {
//            LOGGER.error(resourceBundle.getString("insurance.sql.error"), sqlException);
//            throw new SQLException(sqlException);
//        }
//    }

    //--------------------Method reference---------------------
//    @Override
//    public List<InsuranceAvailable> callAllInsuranceAvailable() throws SQLException, InsuranceAvailableException {
//        try {
//            List<InsuranceAvailable> insuranceList = jdbcTemplate.query(
//                    "select * from MYBANK_APP_INSURANCEAVAILABLE",
//                    this::mapInsuranceAvailable
//            );
//            LOGGER.debug(resourceBundle.getString("insurance.list.size"), insuranceList.size());
//
//            // Check if the list is empty
//            if (insuranceList.isEmpty()) {
//                LOGGER.warn(resourceBundle.getString("insurance.data.null"));
//                throw new InsuranceAvailableException(resourceBundle.getString("insurance.data.null"));
//            }
//
//            return insuranceList;
//        } catch (DataAccessException sqlException) {
//            LOGGER.error(resourceBundle.getString("insurance.sql.error"), sqlException);
//            throw new SQLException(sqlException);
//        }
//    }
//
//    private InsuranceAvailable mapInsuranceAvailable(ResultSet rs, int rowNum) throws SQLException {
//        InsuranceAvailable available = new InsuranceAvailable();
//        available.setInsuranceId(rs.getInt(1));
//        available.setInsuranceType(rs.getString(2));
//        available.setInsuranceName(rs.getString(3));
//        available.setInsuranceKeyBenefits(rs.getString(4));
//        available.setInsuranceLifetime(rs.getInt(5));
//        return available;
//    }
}
