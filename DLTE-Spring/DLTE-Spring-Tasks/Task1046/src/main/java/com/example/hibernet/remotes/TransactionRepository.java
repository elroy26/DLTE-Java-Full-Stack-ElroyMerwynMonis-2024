package com.example.hibernet.remotes;

import com.example.hibernet.model.ViewTransaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<ViewTransaction,String> {
}
