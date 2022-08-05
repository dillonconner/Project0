package dev.conner.services;

import dev.conner.entities.Expense;

import java.util.List;
import java.util.Set;

public interface ExpenseService {

    Expense issueExpense(Expense expense);

    Expense retrieveExpenseById(int id);

    Set<Expense> retrieveAllExpenses();
    Set<Expense> retrieveAllExpensesByQuery(String param, List<String> val);

    Expense modifyExpense(Expense expense);

    boolean deleteExpenseById(int id);
}
