package com.obider.expensetrackerapi.transaction.service;

import com.obider.expensetrackerapi.transaction.entity.InputTransaction;
import com.obider.expensetrackerapi.transaction.entity.Transaction;
import com.obider.expensetrackerapi.user.entity.User;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransaction(Integer userId, Integer categoryId);

    Transaction getTransactionById(Integer userId, Integer categoryId, Integer transactionId);

    boolean addTrasaction(User user, Integer categoryId, InputTransaction inputTransaction);

    boolean updateTransaction(Integer userId, Integer categoryId, Integer transactionId, InputTransaction inputTransaction);
    boolean deleteTransaction(Integer userId, Integer categoryId,Integer transactionId);

}
