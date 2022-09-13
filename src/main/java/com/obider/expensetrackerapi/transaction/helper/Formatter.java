package com.obider.expensetrackerapi.transaction.helper;

import com.obider.expensetrackerapi.transaction.entity.ShowTransaction;
import com.obider.expensetrackerapi.transaction.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Formatter {
    public static ShowTransaction formatTransacation(Transaction transaction){
        return new ShowTransaction(transaction);
    }
    public static List<ShowTransaction> formatTransacations(List<Transaction> transactions){
        List<ShowTransaction> format = new ArrayList<>();
        for (int i = 0; i< transactions.size();i++){
            format.add(formatTransacation(transactions.get(i)));
        }
        return format;
    }

}
