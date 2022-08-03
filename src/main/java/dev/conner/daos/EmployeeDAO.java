package dev.conner.daos;

import dev.conner.entities.Employee;

import java.util.Set;

public interface EmployeeDAO {

    Employee newEmployee(Employee employee);

    Employee getEmployeeById(int id);

    Set<Employee> getAllEmployees();

    Employee updateEmployee(Employee employee);

    boolean deleteEmployeeById(int id);
}
