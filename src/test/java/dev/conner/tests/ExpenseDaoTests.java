package dev.conner.tests;

import dev.conner.daos.ExpenseDAO;
import dev.conner.daos.ExpenseDAOLocal;
import dev.conner.daos.ExpenseDaoPostgres;
import dev.conner.entities.Employee;
import dev.conner.entities.Expense;
import dev.conner.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDaoTests {
    static ExpenseDAO expenseDAO = new ExpenseDaoPostgres();

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table expense(\n" +
                    "id serial primary key,\n" +
                    "issuer_id int references employee(id),\n" +
                    "description varchar(100) not null,\n" +
                    "e_type varchar(100),\n" +
                    "amount int,\n" +
                    "issue_date int,\n" +
                    "status varchar(10) not null\n" +
                    ");\n";
            Statement s = conn.createStatement();
            s.execute(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_expense_test(){
        Expense e = new Expense(0, 1, "none", "misc", 420, 2, Expense.Status.PENDING);
        Expense savedE = expenseDAO.newExpense(e);
        Assertions.assertNotEquals(0,savedE.getId());
    }

    @Test
    @Order(2)
    void get_expense_by_id_test(){
        Expense expense = expenseDAO.getExpenseById(1);
        Assertions.assertEquals("none",expense.getDescription());
        Assertions.assertEquals(Expense.Status.PENDING,expense.getStatus());
    }

    @Test
    @Order(3)
    void get_all_expenses_test(){
        Expense e1 = new Expense(0, 1, "none", "misc", 420, 2, Expense.Status.PENDING);
        Expense savedE1 = expenseDAO.newExpense(e1);
        Expense e2 = new Expense(0, 1, "none", "misc", 420, 2, Expense.Status.PENDING);
        Expense savedE2 = expenseDAO.newExpense(e2);
        Assertions.assertEquals(3, expenseDAO.getAllExpenses().size());
    }

    @Test
    @Order(4)
    void update_employee_test() {
        Expense e1 = new Expense(0, 1, "Not none", "misc", 420, 2, Expense.Status.PENDING);
        Expense edit = expenseDAO.updateExpense(e1);
        Assertions.assertEquals("Not none", edit.getDescription());
    }
    @Test
    @Order(5)
    void delete_employee_test() {
        Assertions.assertTrue(expenseDAO.deleteExpenseById(2));
    }

    @AfterAll
    static void teardown() {
        System.out.println(expenseDAO.getAllExpenses());

        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "drop table expense";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
