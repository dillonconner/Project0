package dev.conner.daos;

import dev.conner.entities.Employee;
import dev.conner.utils.ConnectionUtil;

import java.sql.*;
import java.util.Set;

public class EmployeeDaoPostgres implements EmployeeDAO{
    @Override
    public Employee newEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            //insert into employee values (id, name, title)
            String sql = "insert into employee values (default, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getName());
            //rest of the ? marks

            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            int key = rs.getInt("id");
            employee.setId(key);
            return employee;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public Set<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        return false;
    }
}
