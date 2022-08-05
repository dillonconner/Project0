package dev.conner.services;

import dev.conner.daos.ExpenseDAO;
import dev.conner.entities.Expense;

import java.util.List;
import java.util.Set;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDAO;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    @Override
    public Expense issueExpense(Expense expense) {
        if (expense.getDescription().length() == 0) {
            throw new RuntimeException("Expense Description must not be empty");
        }
        if (expense.getType().length() == 0) {
            throw new RuntimeException("Expense Type must not be empty");
        }
        if(expense.getAmount() <= 0){
            throw new RuntimeException("Expense Amount must not be empty");
        }
        expense.setStatus(Expense.Status.PENDING); //see if this is default for enum with tests
        expense.setDate(System.currentTimeMillis() / 1000L); //set epoch date of issue time

        Expense savedExpense = this.expenseDAO.newExpense(expense);
        return expense;
    }

    @Override
    public Expense retrieveExpenseById(int id) {
        return this.expenseDAO.getExpenseById(id);
    }

    @Override
    public Set<Expense> retrieveAllExpenses() {
        return this.expenseDAO.getAllExpenses();
    }

    @Override
    public Set<Expense> retrieveAllExpensesByQuery(String param, List<String> val) {
        return this.expenseDAO.getAllExpensesByQuery(param, val);
    }

    @Override
    public Expense modifyExpense(Expense expense) {
        if (expense.getDescription().length() == 0) {
            throw new RuntimeException("Expense Description must not be empty");
        }
        if (expense.getType().length() == 0) {
            throw new RuntimeException("Expense Type must not be empty");
        }
        if(expense.getAmount() <= 0){
            throw new RuntimeException("Expense Amount must not be empty");
        }
        //or check if its still pending i.e. you cant change after approved/denied
        expense.setStatus(Expense.Status.PENDING); //would go back to pending if approved and then modified
        expense.setDate(System.currentTimeMillis()/ 1000L); //set epoch date of issue time

        Expense savedExpense = this.expenseDAO.updateExpense(expense);
        return expense;
    }

    @Override
    public boolean deleteExpenseById(int id) {
        Expense testStatus = this.expenseDAO.getExpenseById(id);
        if(testStatus.getStatus() != Expense.Status.PENDING){
            throw new RuntimeException("Expenses that are APPROVED or DENIED cannot be deleted");
        }
        return this.expenseDAO.deleteExpenseById(id);
    }
}
