package com.obider.expensetrackerapi.transaction.repository;

import com.obider.expensetrackerapi.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByUser_IdAndCategory_Id(Integer userId, Integer categoryId);

    Optional<Transaction> findByIdAndUser_IdAndCategory_Id(Integer transactionId, Integer userId, Integer categoryId);


    
    
}
