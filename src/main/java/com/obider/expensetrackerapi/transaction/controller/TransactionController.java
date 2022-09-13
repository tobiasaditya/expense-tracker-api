package com.obider.expensetrackerapi.transaction.controller;

import com.obider.expensetrackerapi.response.ResponseHandler;
import com.obider.expensetrackerapi.transaction.entity.InputTransaction;
import com.obider.expensetrackerapi.transaction.entity.ShowTransaction;
import com.obider.expensetrackerapi.transaction.entity.Transaction;
import com.obider.expensetrackerapi.transaction.helper.Formatter;
import com.obider.expensetrackerapi.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/categories/{categoryId}/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Object> createTransaction(
            HttpServletRequest request,
            @PathVariable("categoryId") Integer categoryId,
            @RequestBody InputTransaction inputTransaction){
        Integer userId = (Integer) request.getAttribute("userId");
        boolean result = transactionService.addTrasaction(userId,categoryId,inputTransaction);
        if (!result){
            return ResponseHandler.generateResponse("Failed to create transaction",HttpStatus.BAD_REQUEST);
        }
        return ResponseHandler.generateResponse("Success create transaction", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Object> getAllTransaction(
            HttpServletRequest request,
            @PathVariable("categoryId") Integer categoryId){
        Integer userId = (Integer) request.getAttribute("userId");
        List<Transaction> result = transactionService.getAllTransaction(userId,categoryId);
        List<ShowTransaction> format = Formatter.formatTransacations(result);
        return ResponseHandler.generateResponse("Success get all transactions",HttpStatus.OK,format);
    }

    @GetMapping(path = "/{transactionId}")
    public ResponseEntity<Object> getTransactionById(
            HttpServletRequest request,
            @PathVariable("categoryId") Integer categoryId,
            @PathVariable("transactionId") Integer transactionId
    ){
        Integer userId = (Integer) request.getAttribute("userId");
        Transaction transaction = transactionService.getTransactionById(userId,categoryId,transactionId);
        if (transaction == null){
            return ResponseHandler.generateResponse("Transaction not found",HttpStatus.NOT_FOUND);
        }
        ShowTransaction format = Formatter.formatTransacation(transaction);
        return ResponseHandler.generateResponse("Success found transaction",HttpStatus.OK,format);
    }

    @PutMapping(path = "/{transactionId}")
    public ResponseEntity<Object> updateTransaction(
            HttpServletRequest request,
            @PathVariable("categoryId") Integer categoryId,
            @PathVariable("transactionId") Integer transactionId,
            @Valid @RequestBody InputTransaction inputTransaction,
            BindingResult bindingResult
    ){
        //Check validation error
        if (bindingResult.hasErrors()){
            return ResponseHandler.generateResponse("Invalid input",HttpStatus.UNPROCESSABLE_ENTITY,bindingResult.getAllErrors());
        }
        Integer userId = (Integer) request.getAttribute("userId");
        boolean result = transactionService.updateTransaction(userId,categoryId,transactionId,inputTransaction);
        if (!result){
            return ResponseHandler.generateResponse("Update transaction failed",HttpStatus.BAD_REQUEST);
        }
        return ResponseHandler.generateResponse("Update transaction success",HttpStatus.OK);
    }

    @DeleteMapping(path = "/{transactionId}")
    public ResponseEntity<Object> deleteTransaction(
            HttpServletRequest request,
            @PathVariable("categoryId") Integer categoryId,
            @PathVariable("transactionId") Integer transactionId
    ){
        Integer userId = (Integer) request.getAttribute("userId");

        boolean result = transactionService.deleteTransaction(userId,categoryId,transactionId);
        if (!result){
            return ResponseHandler.generateResponse("Failed to delete transaction",HttpStatus.BAD_REQUEST);
        }
        return ResponseHandler.generateResponse("Success delete transaction",HttpStatus.OK);
    }

}
