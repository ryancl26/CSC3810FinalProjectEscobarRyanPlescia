//connect to database

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAL {
    private Connection connection;

    public DAL(String databaseName, String user, String password) {
        connection = getMySQLConnection(databaseName, user, password);
    }

    private Connection getMySQLConnection(String databaseName, String user, String password) {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, password);
        } catch (SQLException exception) {
            System.out.println("Failed to connect to the database: " + exception.getMessage());
            return null;
        }
    }

    public ResultSet getAllItems() throws SQLException {
        CallableStatement statement = connection.prepareCall("{CALL GetAllItems()}");
        return statement.executeQuery();
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
