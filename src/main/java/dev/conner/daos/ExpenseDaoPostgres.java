package dev.conner.daos;

import dev.conner.entities.Employee;
import dev.conner.entities.Expense;
import dev.conner.utils.ConnectionUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpenseDaoPostgres implements ExpenseDAO{
    @Override
    public Expense newExpense(Expense expense) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into expense values (default, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, expense.getIssuerId());
            ps.setString(2, expense.getDescription());
            ps.setString(3, expense.getType());
            ps.setInt(4, expense.getAmount());
            ps.setLong(5, expense.getDate());
            ps.setString(6, expense.getStatus().toString());


            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            rs.next();
            int key = rs.getInt("id");
            expense.setId(key);

            return expense;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense getExpenseById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expense where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Expense expense = new Expense();
            expense.setId(rs.getInt("id"));
            expense.setIssuerId(rs.getInt("issuer_id"));
            expense.setDescription((rs.getString("description")));
            expense.setType((rs.getString("e_type")));
            expense.setAmount(rs.getInt("amount"));
            expense.setDate(rs.getInt("issue_date"));
            //get the string stored in the database and makes it an enum Status
            expense.setStatus(Expense.Status.valueOf(rs.getString("status")));

            return expense;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Expense> getAllExpenses() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expense";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            Set<Expense> expenses = new HashSet<Expense>();
            while(rs.next()){
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setIssuerId(rs.getInt("issuer_id"));
                expense.setDescription((rs.getString("description")));
                expense.setType((rs.getString("e_type")));
                expense.setAmount(rs.getInt("amount"));
                expense.setDate(rs.getInt("issue_date"));
                //get the string stored in the database and makes it an enum Status
                expense.setStatus(Expense.Status.valueOf(rs.getString("status")));
                expenses.add(expense);
            }
            return expenses;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Expense> getAllExpensesByQuery(String param, List<String> val) {
        try(Connection conn = ConnectionUtil.createConnection()){

            //////////////       DONT LOOK AT THIS RN IM PLAYING WITH IT STILL  ////////////////////////////////

            //get empty table
            String checkTable = "select * from expense where 1 = 2";
            PreparedStatement checkPs = conn.prepareStatement(checkTable);
            ResultSet checkRs = checkPs.executeQuery();

            //find data type of param
            int columnType = 0;
            for(int i = 1; i < checkRs.getMetaData().getColumnCount(); i++){
                if(checkRs.getMetaData().getCatalogName(i) == "param"){
                    columnType = checkRs.getMetaData().getColumnType(i);
                }
            }

            String sql = "select * from expense where " + param + " = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            //prep statement based of query data type
            switch (columnType){
                case(4):  //int
                    ps.setInt(1, Integer.parseInt(val.get(0)));
                    break;
                case(12):  //varchar
                    ps.setString(1, val.get(0));
                    break;
                case(3):  //decimal
                    ps.setDouble(1, Double.valueOf(val.get(0)));
                    break;
                default:
                    throw new RuntimeException("Column data type not recognized");
            }

            ResultSet rs = ps.executeQuery();

            //make expense list
            Set<Expense> expenses = new HashSet<Expense>();
            while(rs.next()){
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setIssuerId(rs.getInt("issuer_id"));
                expense.setDescription((rs.getString("description")));
                expense.setType((rs.getString("e_type")));
                expense.setAmount(rs.getInt("amount"));
                expense.setDate(rs.getInt("issue_date"));
                //get the string stored in the database and makes it an enum Status
                expense.setStatus(Expense.Status.valueOf(rs.getString("status")));
                expenses.add(expense);
            }
            return expenses;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpense(Expense expense) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update expense set issuer_id = ?, description = ?, e_type = ?, amount = ?, issue_date = ?, status = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expense.getIssuerId());
            ps.setString(2, expense.getDescription());
            ps.setString(3, expense.getType());
            ps.setInt(4, expense.getAmount());
            ps.setLong(5, expense.getDate());
            ps.setString(6, expense.getStatus().toString());

            ps.setInt(7, expense.getId());

            ps.executeUpdate();
            return expense;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteExpenseById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from expense where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
