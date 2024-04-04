package maybank.insurance.dao.insurancedao.services;

import maybank.insurance.dao.insurancedao.entity.InsuranceAvailable;
import maybank.insurance.dao.insurancedao.exceptions.InsuranceAvailableException;
import maybank.insurance.dao.insurancedao.remotes.InsuranceAvailableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Service
public class InsuranceAvailableDbRepo implements InsuranceAvailableRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<InsuranceAvailable> callAllInsuranceAvailable() throws SQLSyntaxErrorException {
        List<InsuranceAvailable> insuranceList=null;
        try {
            insuranceList=jdbcTemplate.query("select * from MYBANK_APP_INSURANCEAVAILABLE",new CardMapper());
            System.out.println(insuranceList.toString());
        }catch (DataAccessException sqlException){
            throw new SQLSyntaxErrorException(sqlException);
        }
        if(insuranceList.size()==0){
            throw new  InsuranceAvailableException("no data found"+insuranceList);
        }
        return insuranceList;
    }

    public class CardMapper implements RowMapper<InsuranceAvailable>{

        @Override
        public InsuranceAvailable mapRow(ResultSet rs, int rowNum) throws SQLException {
            InsuranceAvailable available=new InsuranceAvailable();
            available.setInsuranceId(rs.getInt(1));
            available.setInsuranceType(rs.getString(2));
            available.setInsuranceName(rs.getString(3));
            available.setInsuranceKeyBenefits(rs.getString(4));
            available.setInsuranceLifetime(rs.getInt(5));
            System.out.println(available.toString());
            return available;
        }
    }
}
