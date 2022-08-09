package dev.conner.daos;

import dev.conner.entities.Expense;

import java.util.List;
import java.util.Set;

public interface ExpenseDAO {


    Expense newExpense(Expense expense);

    Expense getExpenseById(int id);
    Set<Expense> getAllExpenses();
    Set<Expense> getAllExpensesByQuery(String param, List<String> val );

    Expense updateExpense(Expense expense);

    boolean approveDenyExpense(int id, boolean approve);

    boolean deleteExpenseById(int id);

}
