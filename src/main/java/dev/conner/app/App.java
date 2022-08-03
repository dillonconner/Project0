package dev.conner.app;

import dev.conner.controllers.EmployeeController;
import dev.conner.services.EmployeeService;
import dev.conner.services.EmployeeServiceImpl;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {


    public static void main(String[] args) {
        Javalin app = Javalin.create();


        //make handlers
        EmployeeService eS = new EmployeeServiceImpl();
        EmployeeController eC = new EmployeeController(eS);
        // define routes
        app.get("/employees", eC.getEmployeeById);


        //app.start();
    }
}
