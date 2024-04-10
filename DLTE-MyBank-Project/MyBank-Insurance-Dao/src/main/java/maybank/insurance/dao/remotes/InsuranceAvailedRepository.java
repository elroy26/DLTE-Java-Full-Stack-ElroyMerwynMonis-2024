package maybank.insurance.dao.remotes;

import maybank.insurance.dao.entity.InsuranceAvailed;
import maybank.insurance.dao.exceptions.InsuranceAvailedException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface InsuranceAvailedRepository {
    InsuranceAvailed callSaveInsuranceAvailed(InsuranceAvailed availed) throws SQLException, InsuranceAvailedException;
}
