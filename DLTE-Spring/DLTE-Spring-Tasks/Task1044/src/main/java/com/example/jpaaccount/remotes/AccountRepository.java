package com.example.jpaaccount.remotes;

import com.example.jpaaccount.model.AccountHolder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountHolder,Long> {

}
