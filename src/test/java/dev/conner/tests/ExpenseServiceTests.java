package dev.conner.tests;

import dev.conner.daos.ExpenseDAO;
import dev.conner.entities.Expense;
import dev.conner.services.ExpenseService;
import dev.conner.services.ExpenseServiceImpl;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ExpenseServiceTests {

//    @Test
//    void mock_create_correctly(){
//        ExpenseDAO mockDAO = Mockito.mock(ExpenseDAO.class);
//        Expense expense = new Expense(1,1,"test", "test", 50, 1659992053,Expense.Status.PENDING);
//        Mockito.when(mockDAO.newExpense(expense)).thenReturn(expense);
//        ExpenseService expenseService = new ExpenseServiceImpl(mockDAO);
//
//        Assertions.assertEquals(expense, ()->{
//            expenseService.issueExpense(expense);
//        });
//    }

    @Test
    void mock_create_nullDescription(){
        ExpenseDAO mockDAO = Mockito.mock(ExpenseDAO.class);
        Expense expense = new Expense(1,1,"", "test", 50, 1659992053,Expense.Status.PENDING);
        Mockito.when(mockDAO.newExpense(expense)).thenReturn(expense);
        ExpenseService expenseService = new ExpenseServiceImpl(mockDAO);

        Assertions.assertThrows(RuntimeException.class, ()->{
            expenseService.issueExpense(expense);
        });
    }
    @Test
    void mock_create_nullType(){
        ExpenseDAO mockDAO = Mockito.mock(ExpenseDAO.class);
        Expense expense = new Expense(1,1,"test", "", 50, 1659992053,Expense.Status.PENDING);
        Mockito.when(mockDAO.newExpense(expense)).thenReturn(expense);
        ExpenseService expenseService = new ExpenseServiceImpl(mockDAO);

        Assertions.assertThrows(RuntimeException.class, ()->{
            expenseService.issueExpense(expense);
        });
    }
    @Test
    void mock_create_negativeAmount(){
        ExpenseDAO mockDAO = Mockito.mock(ExpenseDAO.class);
        Expense expense = new Expense(1,1,"test", "test", -50, 1659992053,Expense.Status.PENDING);
        Mockito.when(mockDAO.newExpense(expense)).thenReturn(expense);
        ExpenseService expenseService = new ExpenseServiceImpl(mockDAO);

        Assertions.assertThrows(RuntimeException.class, ()->{
            expenseService.issueExpense(expense);
        });
    }

    @Test
    void mock_delete_approved(){
        ExpenseDAO mockDAO = Mockito.mock(ExpenseDAO.class);
        Expense expense = new Expense(1,1,"test", "test", -50, 1659992053,Expense.Status.PENDING);
        Mockito.when(mockDAO.deleteExpenseById(1)).thenReturn(true);
        ExpenseService expenseService = new ExpenseServiceImpl(mockDAO);

        Assertions.assertThrows(RuntimeException.class, ()->{
            expenseService.deleteExpenseById(1);
        });
    }
}
