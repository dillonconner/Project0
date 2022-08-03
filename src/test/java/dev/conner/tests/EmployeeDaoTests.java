package dev.conner.tests;

import dev.conner.daos.EmployeeDAO;
import dev.conner.daos.EmployeeDaoPostgres;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {

    static EmployeeDAO employeeDAO = new EmployeeDaoPostgres();

    @Test
    @Order(1)
    void create_employee_test(){

    }
}
