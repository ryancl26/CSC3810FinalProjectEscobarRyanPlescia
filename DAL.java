//connect to database

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public void addEmployee(String employeeName, String position, double salary) throws SQLException {
        String sql = "INSERT INTO EmployeeInfo (EmployeeName, Position, Salary) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employeeName);
            statement.setString(2, position);
            statement.setDouble(3, salary);
            statement.executeUpdate();
        }
    }
}
