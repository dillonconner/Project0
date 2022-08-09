package dev.conner.services;

import dev.conner.entities.Employee;
import java.util.Set;

public interface EmployeeService {

    Employee hireEmployee(Employee employee);

    Employee retrieveEmployeeById(int id);

    Set<Employee> retrieveAllEmployees();

    Employee modifyEmployee(Employee employee);

    boolean deleteEmployee(int id);

}
