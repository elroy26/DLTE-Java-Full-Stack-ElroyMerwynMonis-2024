package maybank.insurance.dao.remotes;

import maybank.insurance.dao.entity.InsuranceAvailable;
import maybank.insurance.dao.exceptions.InsuranceAvailableException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface InsuranceAvailableRepository {
    List<InsuranceAvailable> callAllInsuranceAvailable() throws SQLException, InsuranceAvailableException;
}
