package application;

import db.DB;
import db.DBException;
import model.Seller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            conn = DB.getConnection();
            statement = conn.createStatement();

            resultSet = statement.executeQuery("select * from department");

            while(resultSet.next()) {
                String line = resultSet.getInt("Id") + ", " + resultSet.getString("Name");
                System.out.println(line);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date birthDate = null;
            try {
                birthDate = sdf.parse("08/03/2001");
            } catch (java.text.ParseException e) {
                System.out.print("ParseException failed: " + e.getMessage());
            } 

            Seller firstSeller = new Seller(
                1,
                "Joao", 
                "joao@example.com", 
                birthDate,
                3000.0, 
                new model.Department(1, "Computers"));

            firstSeller.intertToDatabase(conn);
            System.out.println("Seller " + firstSeller.getName() + " inserted successfully!");

        } catch (DBException e) {
            System.out.print("DBException failed: " + e.getMessage());
        } catch (SQLException e) {
            System.out.print("SQLException failed: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.print("Unexpected error: " + e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
            DB.closeConnection();
            System.out.println("Database resources closed successfully!");
        }
    }
}

