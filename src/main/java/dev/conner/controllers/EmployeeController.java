package dev.conner.controllers;

import com.google.gson.Gson;
import dev.conner.entities.Employee;
import dev.conner.services.EmployeeService;
import io.javalin.http.Handler;

import java.util.Set;

public class EmployeeController {

    private Gson gson = new Gson();
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    public Handler createEmployee = (ctx) -> {
        Employee em = this.gson.fromJson(ctx.body(), Employee.class);
        try{
            em = this.employeeService.hireEmployee(em);
            if(em == null){
                ctx.status(400);
                ctx.result("Employee not created");
            }else{
                String json = this.gson.toJson(em);
                ctx.status(201);
                ctx.result(json);
            }

        }catch(RuntimeException e){
            ctx.status(400);
            ctx.result(e.getMessage());
        }

    };

    public Handler getEmployeeById = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee em = this.employeeService.retrieveEmployeeById(id);
        if(em == null){
            ctx.status(404);
            ctx.result("Employee with Id:" + Integer.toString(id) + " not found");
        }else{
            String json = this.gson.toJson(em);
            ctx.status(200);
            ctx.result(json);
        }
    };

    public Handler getAllEmployees = (ctx) -> {
        Set<Employee> employees = this.employeeService.retrieveAllEmployees();
        String json = this.gson.toJson(employees);
        ctx.result(json);
    };

    public Handler updateEmployee = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee em = this.gson.fromJson(ctx.body(), Employee.class);
        em.setId(id);
        try{
            Employee res = this.employeeService.modifyEmployee(em);
            if(res == null){
                ctx.status(404);
                ctx.result("Employee with Id:" + Integer.toString(id) + " not found");
            }else{
                String json = this.gson.toJson(em);
                ctx.status(200);
                ctx.result(json);
            }

        }catch(RuntimeException e){
            ctx.status(400);
            ctx.result(e.getMessage());
        }
    };

    public Handler deleteEmployee = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = this.employeeService.deleteEmployee(id);

        if(result){
            ctx.status(200);
            ctx.result("Employee Deleted");
        }
        else{
            ctx.status(404);
            ctx.result("Employee with Id:" + Integer.toString(id) + " not found");
        }
    };
}
