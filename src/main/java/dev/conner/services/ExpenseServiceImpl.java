package dev.conner.services;

import dev.conner.daos.ExpenseDAO;
import dev.conner.entities.Expense;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDAO;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    @Override
    public Expense issueExpense(Expense expense) {

        return null;
    }

    @Override
    public Expense retrieveExpenseById(int id) {
        return null;
    }

    @Override
    public List<Expense> retrieveAllExpenses() {
        return null;
    }

    @Override
    public Expense modifyExpense(Expense expense) {
        return null;
    }

    @Override
    public boolean deleteExpenseById(int id) {
        return false;
    }
}
