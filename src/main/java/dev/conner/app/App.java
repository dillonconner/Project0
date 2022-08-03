package dev.conner.app;

import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    //public static Service layer object thing

    public static void main(String[] args) {
        //Javalin app = Javalin.create();
        try{
            Connection conn = DriverManager.getConnection(System.getenv("AZURE_SQL_DB"));

            Statement s = conn.createStatement();
            s.execute("insert into player values (default,'Dillon','Conner', 60000)");
            System.out.println(conn);
        }catch(SQLException e){
            e.printStackTrace();
        }


        //make handlers

        // define routes

        //app.start();
    }
}
