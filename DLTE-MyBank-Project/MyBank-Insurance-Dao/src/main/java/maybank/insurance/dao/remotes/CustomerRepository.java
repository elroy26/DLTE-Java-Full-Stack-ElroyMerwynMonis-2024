package maybank.insurance.dao.remotes;

import maybank.insurance.dao.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  {
    Customer signingUp(Customer customer);
    Customer findByUserName(String username);
    void updateAttempts(Customer customer);
    void updateStatus(Customer customer);

}
