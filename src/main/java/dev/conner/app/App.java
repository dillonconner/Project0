package dev.conner.app;

import dev.conner.controllers.EmployeeController;
import dev.conner.controllers.ExpenseController;
import dev.conner.daos.EmployeeDAO;
import dev.conner.daos.EmployeeDaoPostgres;
import dev.conner.daos.ExpenseDAO;
import dev.conner.daos.ExpenseDaoPostgres;
import dev.conner.services.EmployeeService;
import dev.conner.services.EmployeeServiceImpl;
import dev.conner.services.ExpenseService;
import dev.conner.services.ExpenseServiceImpl;
import io.javalin.Javalin;

public class App {


    public static void main(String[] args) {
        Javalin app = Javalin.create();


        //make handlers
        EmployeeDAO emD = new EmployeeDaoPostgres();
        EmployeeService emS = new EmployeeServiceImpl(emD);
        EmployeeController emC = new EmployeeController(emS);

        ExpenseDAO exD = new ExpenseDaoPostgres();
        ExpenseService exS = new ExpenseServiceImpl(exD);
        ExpenseController exC = new ExpenseController(exS);

        // DEFINE ROUTES
        // employees
        app.post("/employees", emC.createEmployee);
        app.get("/employees/{id}", emC.getEmployeeById);
        app.get("/employees", emC.getAllEmployees);
        app.put("/employees/{id}", emC.updateEmployee);
        app.delete("/employees/{id}", emC.deleteEmployee);

        // expenses
        //app.post("/expenses", exC.createExpense);
        //app.get("/expenses/{expenseId}", exC.getExpenseById);
        //app.get("/expenses", exC.getAllExpenses);

        //app.put("/expenses/{expenseId}", exC.updateExpense);
        //app.delete("/expenses/{expenseId}", exC.deleteExpense);

        // Not sure if I did this right
        //app.patch("/expenses/{id}/approve", exC.approveExpense);
        //app.patch("/expenses/{id}/deny", exC.denyExpense);

        //nested //ask trainers about this
        app.post("/employees/{id}/expenses", exC.createExpense);
        app.get("/employees/{id}/expenses", exC.getAllExpenses);
        app.get("/employees/{id}/expenses/{expenseId}", exC.getExpenseById);
        app.put("/employees/{id}/expenses/{expenseId}", exC.updateExpense);
        app.delete("/employees/{id}/expenses/{expenseId}", exC.deleteExpense);
        app.patch("/employees/{id}/expenses/{expenseId}/approve", exC.approveExpense);
        app.patch("/employees/{id}/expenses/{expenseId}/deny", exC.denyExpense);

        app.start();
    }
}
