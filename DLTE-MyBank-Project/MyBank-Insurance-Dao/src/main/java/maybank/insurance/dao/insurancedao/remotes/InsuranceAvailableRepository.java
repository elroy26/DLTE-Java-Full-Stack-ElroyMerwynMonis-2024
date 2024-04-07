package maybank.insurance.dao.insurancedao.remotes;

import maybank.insurance.dao.insurancedao.entity.InsuranceAvailable;
import maybank.insurance.dao.insurancedao.exceptions.InsuranceAvailableException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface InsuranceAvailableRepository {
    List<InsuranceAvailable> callAllInsuranceAvailable() throws SQLException, InsuranceAvailableException;
}
