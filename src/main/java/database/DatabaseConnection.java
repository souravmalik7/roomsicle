package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection databaseConnection = null;
    public static Connection connection = null;
    private String jdbcDriver;
    private String url;
    private String user;
    private String password;

    private DatabaseConnection() {
        try {
            jdbcDriver = ConfigProperties.getConfigPropertyValue("jdbc.driver");
            url = ConfigProperties.getConfigPropertyValue("url");
            user = ConfigProperties.getConfigPropertyValue("user");
            password = ConfigProperties.getConfigPropertyValue("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnectionObject() {
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static DatabaseConnection getDatabaseConnectionObject() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    public static void closeDatabaseConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
