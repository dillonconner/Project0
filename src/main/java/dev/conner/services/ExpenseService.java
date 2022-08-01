package dev.conner.services;

import dev.conner.entities.Expense;

import java.util.List;

public interface ExpenseService {

    Expense issueExpense(Expense expense);

    Expense retrieveExpenseById(int id);

    List<Expense> retrieveAllExpenses();

    Expense modifyExpense(Expense expense);

    boolean deleteExpenseById(int id);
}
