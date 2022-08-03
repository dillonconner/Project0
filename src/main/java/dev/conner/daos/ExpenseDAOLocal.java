package dev.conner.daos;

import dev.conner.entities.Expense;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExpenseDAOLocal implements ExpenseDAO{

    private Map<Integer, Expense> expenseTable = new HashMap(); //will turn into sql
    private int idMaker = 0;

    @Override
    public Expense newExpense(Expense expense) {
        expense.setId(++this.idMaker);
        expenseTable.put(this.idMaker, expense);
        return expense;
    }

    @Override
    public Expense getExpenseById(int id) {
        Expense expense = this.expenseTable.get(id);
        return expense;
    }

    @Override
    public Set<Expense> getAllExpenses() {
        Set<Expense> expenses = new HashSet<Expense>(this.expenseTable.values());
        return expenses;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        this.expenseTable.put(expense.getId(), expense);
        return expense;
    }

    @Override
    public boolean deleteExpenseById(int id) {
        Expense e = this.expenseTable.remove(id);
        return e != null;
    }
}
