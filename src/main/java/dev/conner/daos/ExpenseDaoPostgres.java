package dev.conner.daos;

import dev.conner.entities.Expense;

import java.util.Set;

public class ExpenseDaoPostgres implements ExpenseDAO{
    @Override
    public Expense newExpense(Expense expense) {
        return null;
    }

    @Override
    public Expense getExpenseById(int id) {
        return null;
    }

    @Override
    public Set<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    @Override
    public boolean deleteExpenseById(int id) {
        return false;
    }
}
