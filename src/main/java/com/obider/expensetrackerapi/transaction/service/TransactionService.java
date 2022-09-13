package com.obider.expensetrackerapi.transaction.service;

import com.obider.expensetrackerapi.transaction.entity.InputTransaction;
import com.obider.expensetrackerapi.transaction.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransaction(Integer userId, Integer categoryId);

    Transaction getTransactionById(Integer userId, Integer categoryId, Integer transactionId);

    boolean addTrasaction(Integer userId, Integer categoryId, InputTransaction inputTransaction);

    boolean updateTransaction(Integer userId, Integer categoryId, Integer transactionId, InputTransaction inputTransaction);
    boolean deleteTransaction(Integer userId, Integer categoryId,Integer transactionId);

}
