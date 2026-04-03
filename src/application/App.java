package application;

import db.DB;
import db.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        }
    }
}

