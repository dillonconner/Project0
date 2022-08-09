package dev.conner.daos;

import dev.conner.entities.Expense;

import java.util.*;

public class ExpenseDAOLocal implements ExpenseDAO{

    private Map<Integer, Expense> expenseTable = new HashMap<>(); //will turn into sql
    private int idMaker = 0;

    @Override
    public Expense newExpense(Expense expense) {
        expense.setId(++this.idMaker);
        expenseTable.put(this.idMaker, expense);
        return expense;
    }

    @Override
    public Expense getExpenseById(int id) {
        return this.expenseTable.get(id);
    }

    @Override
    public Set<Expense> getAllExpenses() {
        return new HashSet<>(this.expenseTable.values());
    }

    @Override
    public Set<Expense> getAllExpensesByQuery(String param, List<String> val) {
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        this.expenseTable.put(expense.getId(), expense);
        return expense;
    }

    @Override
    public boolean approveDenyExpense(int id, boolean approve) {
        return false;
    }

    @Override
    public boolean deleteExpenseById(int id) {
        Expense e = this.expenseTable.remove(id);
        return e != null;
    }
}
