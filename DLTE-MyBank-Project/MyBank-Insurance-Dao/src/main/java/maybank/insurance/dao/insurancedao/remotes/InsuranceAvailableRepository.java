package maybank.insurance.dao.insurancedao.remotes;

import maybank.insurance.dao.insurancedao.entity.InsuranceAvailable;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface InsuranceAvailableRepository {
    List<InsuranceAvailable> callAllInsuranceAvailable() throws SQLSyntaxErrorException;
}
