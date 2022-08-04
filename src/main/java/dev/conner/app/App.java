package dev.conner.app;

import dev.conner.controllers.EmployeeController;
import dev.conner.daos.EmployeeDAO;
import dev.conner.daos.EmployeeDaoPostgres;
import dev.conner.services.EmployeeService;
import dev.conner.services.EmployeeServiceImpl;
import io.javalin.Javalin;

public class App {


    public static void main(String[] args) {
        Javalin app = Javalin.create();


        //make handlers
        EmployeeDAO eD = new EmployeeDaoPostgres();
        EmployeeService eS = new EmployeeServiceImpl(eD);
        EmployeeController eC = new EmployeeController(eS);

        // DEFINE ROUTES
        // employees
        app.post("/employees", eC.createEmployee);
        app.get("/employees/{id}", eC.getEmployeeById);
        app.get("/employees", eC.getAllEmployees);
        app.put("/employees/{id}", eC.updateEmployee);
        app.delete("/employees/{id}", eC.deleteEmployee);

        // expenses


        //app.start();
    }
}
