package dev.conner.controllers;

import com.google.gson.Gson;
import dev.conner.entities.Expense;
import dev.conner.services.ExpenseService;
import io.javalin.http.Handler;

import java.util.List;
import java.util.Map;
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
        Map<String, List<String>> query = ctx.queryParamMap();
        //int employee = Integer.parseInt(ctx.queryParam("employee"));
        //String type = ctx.queryParam("type");
        //String status = ctx.queryParam("status");



        if(query.isEmpty()){
            Set<Expense> expenses = this.expenseService.retrieveAllExpenses();
            String json = this.gson.toJson(expenses);
            ctx.result(json);
        }else{
            String json = "";
            //Map.Entry<String,String> entry : gfg.entrySet()
            for(Map.Entry<String,List<String>> q: query.entrySet()){
                //System.out.println(q.getKey() + " " + q.getValue().toString());
                Set<Expense> expenses = this.expenseService.retrieveAllExpensesByQuery(q.getKey(), q.getValue());
                json += this.gson.toJson(expenses);
                ctx.result(json);
            }
        }

//        if(employee != 0){
//            Set<Expense> expenses = this.expenseService.retrieveAllExpensesByEmployeeId(employee);
//            String json = this.gson.toJson(expenses);
//            ctx.result(json);
//        } else if (!type.equals("none")) {
//            Set<Expense> expenses = this.expenseService.retrieveAllExpensesByType(type);
//            String json = this.gson.toJson(expenses);
//            ctx.result(json);
//        } else if (!status.equals("none")) {
//            Set<Expense> expenses = this.expenseService.retrieveAllExpensesByStatus(status);
//            String json = this.gson.toJson(expenses);
//            ctx.result(json);
//        } else{
//            Set<Expense> expenses = this.expenseService.retrieveAllExpenses();
//            String json = this.gson.toJson(expenses);
//            ctx.result(json);
//       }
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
