package dev.conner.services;

import dev.conner.daos.EmployeeDAO;
import dev.conner.entities.Employee;
import java.util.Set;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee hireEmployee(Employee employee) {
        if(employee.getName().length() == 0){
            throw new RuntimeException("Employee name cannot be empty");
        }
        if(employee.getTitle().length() == 0){
            throw new RuntimeException("Employee title cannot be empty");
        }
        return this.employeeDAO.createEmployee(employee);
    }

    @Override
    public Employee retrieveEmployeeById(int id) {
        return this.employeeDAO.getEmployeeById(id);
    }

    @Override
    public Set<Employee> retrieveAllEmployees() {
        return this.employeeDAO.getAllEmployees();
    }

    @Override
    public Employee modifyEmployee(Employee employee) {
        if(employee.getName().length() == 0){
            throw new RuntimeException("Employee name cannot be empty");
        }
        if(employee.getTitle().length() == 0){
            throw new RuntimeException("Employee title cannot be empty");
        }
        return this.employeeDAO.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(int id) {
        //Employee em = this.employeeDAO.getEmployeeById(id);
        //if(em == null) return false;
        return this.employeeDAO.deleteEmployeeById(id);
    }
}
