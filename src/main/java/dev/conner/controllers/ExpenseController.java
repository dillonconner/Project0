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
        String param = ctx.pathParam("id");
        Expense ex = this.gson.fromJson(ctx.body(), Expense.class);
        ex.setIssuerId(Integer.parseInt(param));
        try{
            ex = this.expenseService.issueExpense(ex);
            if(ex == null){
                ctx.status(400);
                ctx.result("Expense not created");
            }else{
                String json = gson.toJson(ex);
                ctx.status(201);
                ctx.result(json);
            }

        }catch(RuntimeException e){
            ctx.status(400);
            ctx.result(e.getMessage());
        }
    };

    public Handler getExpenseById = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("expenseId"));
        Expense res = this.expenseService.retrieveExpenseById(id);
        if(res == null){
            ctx.status(404);
            ctx.result("Expense with Id: " + Integer.toString(id) + " not found");
        }else{
            String json = this.gson.toJson(res);
            ctx.status(200);
            ctx.result(json);
        }
    };

    //maybe make it get all expenses for specific employee???
    public Handler getAllExpenses = (ctx) -> {
        Map<String, List<String>> query = ctx.queryParamMap();

        if(query.isEmpty()){
            Set<Expense> expenses = this.expenseService.retrieveAllExpenses();
            String json = this.gson.toJson(expenses);
            ctx.status(200);
            ctx.result(json);
        }else{
            String json = "";
            for(Map.Entry<String,List<String>> q: query.entrySet()){
                Set<Expense> expenses = this.expenseService.retrieveAllExpensesByQuery(q.getKey(), q.getValue());
                json += this.gson.toJson(expenses);
            }
            ctx.status(200);
            ctx.result(json);
        }
    };

    public Handler updateExpense = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("expenseId"));
        Expense ex = this.gson.fromJson(ctx.body(), Expense.class);
        ex.setId(id);
        try{
            Expense res =  this.expenseService.modifyExpense(ex);
            if(res == null){
                ctx.status(404);
                ctx.result("Expense with Id: " + Integer.toString(id) + " not found");
                return;
            }
            String json = this.gson.toJson(ex);
            ctx.status(200);
            ctx.result(json);
        }catch(RuntimeException e){
            ctx.status(400);
            ctx.result(e.getMessage());
        }
    };

    public Handler approveExpense = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("expenseId"));
        boolean res = this.expenseService.approveDenyExpense(id, true);
        if(res){
            ctx.status(200);
            ctx.result("Expense Approved");
        }else{
            ctx.status(404);
            ctx.result("Expense with Id: " + Integer.toString(id) + " not found");
        }


    };
    public Handler denyExpense = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("expenseId"));
        boolean res = this.expenseService.approveDenyExpense(id, false);
        if(res){
            ctx.status(200);
            ctx.result("Expense Denied");
        }else{
            ctx.status(404);
            ctx.result("Expense with Id: " + Integer.toString(id) + " not found");
        }
    };

    public Handler deleteExpense = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("expenseId"));
        try{
            boolean result = this.expenseService.deleteExpenseById(id);
            if(result){
                ctx.status(200);
                ctx.result("Expense successfully deleted");
            }
            else {
                System.out.println("SERVICE RETURN FALSE BOCK");
                ctx.status(404);
                ctx.result("Expense with Id: " + Integer.toString(id) + " not found");
            }
        }catch(RuntimeException e){
            System.out.println("CATCH BLOCK");
            ctx.status(422);
            ctx.result(e.getMessage());
        }
    };
}
