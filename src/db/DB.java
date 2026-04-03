package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {

        if (conn == null) {
            connect();
        }

        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DBException("failed to close connection: " + e.getMessage());
            }
        }
    }

    public static void closeStatement(java.sql.Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DBException("failed to close statement: " + e.getMessage());
            }
        }
    }

    public static void closeResultSet(java.sql.ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DBException("failed to close result set: " + e.getMessage());
            }
        }
    }

    private static void connect() {
        Properties props = loadProperties();

        try {
            String dbUrl = props.getProperty("dburl");
            conn = DriverManager.getConnection(dbUrl, props);
        } catch (SQLException e) {
            throw new DBException("Error to connect to database: " + e.getMessage());
        }
    }
    
    private static Properties loadProperties() {

        try(FileInputStream fs = new FileInputStream("db.properties")) {
            
            Properties props = new Properties();
            props.load(fs);

            return props;
        }
        catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }
}
