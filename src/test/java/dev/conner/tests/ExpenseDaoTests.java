package dev.conner.tests;

import dev.conner.daos.ExpenseDAO;
import dev.conner.daos.ExpenseDAOLocal;
import dev.conner.entities.Expense;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDaoTests {
    static ExpenseDAO expenseDAO = new ExpenseDAOLocal();

    @Test
    @Order(1)
    void create_expense_test(){
        Expense e = new Expense(0, "idk", "none", "misc", 420, 2, Expense.Status.PENDING);
        Expense savedE = expenseDAO.newExpense(e);
        Assertions.assertNotEquals(0,savedE.getId());
    }


}
