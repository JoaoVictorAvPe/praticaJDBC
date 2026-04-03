package application;

import db.DB;
import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        
        Connection conn = DB.getConnection();
        System.out.println("connected sucessfuly");

        DB.closeConnection();
        System.out.println("connected closed");
    }
}

