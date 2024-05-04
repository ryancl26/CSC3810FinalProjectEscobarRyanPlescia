package src;
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

    public void getAllEmployees() throws SQLException {
        String sql = "{CALL GetAllEmployees()}";

        try (CallableStatement statement = connection.prepareCall(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("EmployeeID: " + resultSet.getInt("EmployeeID"));
                System.out.println("EmployeeName: " + resultSet.getString("EmployeeName"));
                System.out.println("Position: " + resultSet.getString("Position"));
                System.out.println("Salary: " + resultSet.getDouble("Salary"));
                System.out.println("----------------------");
            }
        }
    }

    public void removeEmployee(int employeeID) throws SQLException {
        String sql = "DELETE FROM EmployeeInfo WHERE EmployeeID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeID);
            statement.executeUpdate();
        }
    }
    public void addReservation(String customerName, int tableNumber, int numberOfPeople, int date, int time) throws SQLException {
        String sql = "INSERT INTO Reservations (CustomerName, TableNumber, NumberOfPeople)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(2, customerName);
            statement.setDouble(3, tableNumber);
            statement.setDouble(4, numberOfPeople);
            statement.setDate(date, null);
            statement.setTime(time, null);
            statement.executeUpdate();
        }
    }
    public void removeReservation(int reservationID) throws SQLException {
        String sql = "DELETE FROM Reservations WHERE ReservationID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservationID);
            statement.executeUpdate();
        }
}
}