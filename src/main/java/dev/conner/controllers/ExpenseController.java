package dev.conner.controllers;

import com.google.gson.Gson;
import dev.conner.entities.Expense;
import dev.conner.services.ExpenseService;
import io.javalin.http.Handler;

import java.util.Set;

public class ExpenseController {

    private Gson gson = new Gson();
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    public Handler createExpense = (ctx) -> {
        Expense ex = this.gson.fromJson(ctx.body(), Expense.class);
        ex = this.expenseService.issueExpense(ex);
        String json = gson.toJson(ex);
        ctx.status(201);
        ctx.result(json);
    };

    public Handler getExpenseById = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense ex = this.expenseService.retrieveExpenseById(id);
        String json = this.gson.toJson(ex);
        ctx.result(json);
    };

    //maybe make it get all expenses for specific employee???
    public Handler getAllExpenses = (ctx) -> {
        //String employee = ctx.queryParam("employee", "none");

        //if(employee.equals("none")){
            Set<Expense> expenses = this.expenseService.retrieveAllExpenses();
            String json = this.gson.toJson(expenses);
            ctx.result(json);
       // }else{
       //     Set<Expense> expenses = this.expenseService.retrieveAllExpensesByEmployeeId(employee);
       //     String json = this.gson.toJson(expenses);
       //     ctx.result(json);
       //}
    };

    public Handler updateExpense = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense ex = this.gson.fromJson(ctx.body(), Expense.class);
        ex.setId(id);
        try{
            this.expenseService.modifyExpense(ex);
            String json = this.gson.toJson(ex);
            ctx.result(json);
        }catch(RuntimeException e){
            ctx.status(400);
            ctx.result("no expense found");
        }
    };

    public Handler deleteExpense = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = this.expenseService.deleteExpenseById(id);

        if(result) ctx.status(200);
        else ctx.status(404);
    };
}
