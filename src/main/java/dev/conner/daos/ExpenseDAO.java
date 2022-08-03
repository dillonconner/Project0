package dev.conner.daos;

import dev.conner.entities.Expense;
import java.util.Set;

public interface ExpenseDAO {


    Expense newExpense(Expense expense);

    Expense getExpenseById(int id);
    Set<Expense> getAllExpenses();

    Expense updateExpense(Expense expense);

    boolean deleteExpenseById(int id);

}
