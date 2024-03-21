package com.example.hibernet.remotes;

import com.example.hibernet.model.ViewTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JpaTransactionRepository extends JpaRepository<ViewTransaction,String>{
    //hql query
    @Query("from ViewTransaction where transactionAmount between :transactionAmount1 and :transactionAmount2")
    List<ViewTransaction> lookForAmountRange(Double transactionAmount1,Double transactionAmount2);

    //native Query
    @Query(value = "select * from View_Transaction where user_Name = :user and transaction_Type = :type", nativeQuery = true)
    List<ViewTransaction> lookForUserAndType(String user, String type);
}
