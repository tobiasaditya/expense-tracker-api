package com.obider.expensetrackerapi.transaction.service;

import com.obider.expensetrackerapi.category.entity.Category;
import com.obider.expensetrackerapi.category.service.CategoryService;
import com.obider.expensetrackerapi.transaction.entity.InputTransaction;
import com.obider.expensetrackerapi.transaction.entity.Transaction;
import com.obider.expensetrackerapi.transaction.repository.TransactionRepository;
import com.obider.expensetrackerapi.user.service.UserService;
import com.obider.expensetrackerapi.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserService userService, CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Transaction> getAllTransaction(Integer userId, Integer categoryId) {
        return transactionRepository.findByUser_IdAndCategory_Id(userId,categoryId);
    }

    @Override
    public Transaction getTransactionById(Integer userId, Integer categoryId, Integer transactionId) {
        Optional<Transaction> foundTransaction = transactionRepository.findByIdAndUser_IdAndCategory_Id(transactionId,userId,categoryId);
        if (foundTransaction.isEmpty()){
            return null;
        }
        return foundTransaction.get();
    }

    @Override
    public boolean addTrasaction(Integer userId, Integer categoryId, InputTransaction inputTransaction) {
        //Get user
        User user = userService.findUserById(userId);
        if (user == null){
            return false;
        }
        //Get Category
        Category category = categoryService.getUserCategoryById(userId,categoryId);
        if (category == null){
            return false;
        }

        //Create new transaction object
        Transaction newTransaction = new Transaction(inputTransaction.getAmount(),inputTransaction.getNote(),user,category);
        transactionRepository.save(newTransaction);
        return true;
    }

    @Override
    public boolean updateTransaction(Integer userId, Integer categoryId, Integer transactionId, InputTransaction inputTransaction) {
        Transaction foundTransaction = getTransactionById(userId,categoryId,transactionId);
        if (foundTransaction == null){
            return false;
        }
        foundTransaction.setAmount(inputTransaction.getAmount());
        foundTransaction.setNote(inputTransaction.getNote());

        transactionRepository.save(foundTransaction);
        return true;

    }

    @Override
    public boolean deleteTransaction(Integer userId, Integer categoryId, Integer transactionId) {
        Transaction foundTransaction = getTransactionById(userId,categoryId,transactionId);
        if (foundTransaction == null){
            return false;
        }
        transactionRepository.deleteById(transactionId);
        return true;
    }
}
