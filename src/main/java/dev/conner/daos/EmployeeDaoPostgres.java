package dev.conner.daos;

import dev.conner.entities.Employee;
import dev.conner.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDaoPostgres implements EmployeeDAO{
    @Override
    public Employee createEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into employee values (default, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getTitle());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            rs.next();
            int key = rs.getInt("id");
            employee.setId(key);

            return employee;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setTitle((rs.getString("title")));

            return employee;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Employee> getAllEmployees() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            Set<Employee> employees = new HashSet<Employee>();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setTitle((rs.getString("title")));
                employees.add(employee);
            }
            return employees;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update employee set name = ?, title = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getTitle());
            ps.setInt(3,employee.getId());

            ps.executeUpdate();
            return employee;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from employee where id = ?";
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
