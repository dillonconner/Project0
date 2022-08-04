package dev.conner.tests;

import dev.conner.daos.EmployeeDAO;
import dev.conner.daos.EmployeeDaoPostgres;
import dev.conner.entities.Employee;
import dev.conner.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {

    static EmployeeDAO employeeDAO = new EmployeeDaoPostgres();

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table employee(id serial primary key,name varchar(100) not null, title varchar(100) not null);";
            Statement s = conn.createStatement();
            s.execute(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_employee_test(){
        Employee employee = new Employee(0,"Dillon","associate");
        Employee savedE = employeeDAO.createEmployee(employee);
        Assertions.assertNotEquals(0,savedE.getId());
    }

    @Test
    @Order(2)
    void get_employee_by_id_test(){
        Employee employee = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals("Dillon",employee.getName());
    }

    @Test
    @Order(3)
    void get_all_employees(){
        Employee employee1 = new Employee(0,"Terrel","associate");
        Employee employee2 = new Employee(0,"Adam","trainer");
        Employee savedE1 = employeeDAO.createEmployee(employee1);
        Employee savedE2 = employeeDAO.createEmployee(employee2);
        Assertions.assertEquals(3,employeeDAO.getAllEmployees().size());
    }

    @Test
    @Order(4)
    void update_employee_test(){
        Employee employee = new Employee(1,"Conner","associate");
        Employee edit = employeeDAO.updateEmployee(employee);
        Assertions.assertEquals("Conner", edit.getName());
    }

    @Test
    @Order(5)
    void delete_employee_test(){
        Assertions.assertTrue(employeeDAO.deleteEmployeeById(2));
    }

    @AfterAll
    static void teardown(){
        System.out.println(employeeDAO.getAllEmployees());

        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "drop table employee";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
