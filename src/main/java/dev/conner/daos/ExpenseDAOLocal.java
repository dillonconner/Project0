package dev.conner.daos;

import dev.conner.entities.Expense;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDAOLocal implements ExpenseDAO{

    private Map<Integer, Expense> expenseTable = new HashMap(); //will turn into sql
    private int idMaker = 1;

    @Override
    public Expense newExpense(Expense expense) {
        return null;
    }

    @Override
    public Expense getExpenseById(int id) {
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
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
