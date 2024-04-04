package maybank.insurance.dao.insurancedao.remotes;

import maybank.insurance.dao.insurancedao.entity.InsuranceAvailable;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

public interface InsuranceAvailableRepository {
    List<InsuranceAvailable> callAllInsuranceAvailable() throws SQLSyntaxErrorException;
}
