package dev.conner.daos;

import dev.conner.entities.Expense;

import java.util.List;

public interface ExpenseDAO {


    Expense newExpense(Expense expense);

    Expense getExpenseById(int id);
    List<Expense> getAllExpenses();

    Expense updateExpense(Expense expense);

    boolean deleteExpenseById(int id);

}
