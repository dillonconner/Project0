package dev.conner.app;

import io.javalin.Javalin;

public class App {

    //public static Service layer object thing

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        //make handlers

        // define routes

        app.start();
    }
}
