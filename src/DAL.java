package src;
//connect to database


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class DAL {
   private Connection connection;


   public DAL(String databaseName, String user, String password) {
       this.connection  = getMySQLConnection(databaseName, user, password);
   }


   private Connection getMySQLConnection(String databaseName, String user, String password) {
       String jdbcUrl = "jdbc:mysql://localhost:3306/" + databaseName;
       try {
           return DriverManager.getConnection(jdbcUrl, user, password);
       } catch (SQLException exception) {
           System.out.println("Failed to connect to the database: " + exception.getMessage());
           return null;
       }
   }


   public void closeConnection() throws SQLException {
       if (connection != null && !connection.isClosed()) {
           connection.close();
       }
   }

   public List<String> getTables() throws SQLException {
    List<String> tables = new ArrayList<>();
    String sql = "SELECT TableNumber, NumberOfSeats, Location FROM Tables ORDER BY TableNumber LIMIT 10 ";
    try (PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            String tableInfo = "Table Number: " + resultSet.getInt("TableNumber") +
                               ", Seats: " + resultSet.getInt("NumberOfSeats") +
                               ", Location: " + resultSet.getString("Location");
            tables.add(tableInfo);
        }
    }
    return tables;
}



    public void addReservation(String customerName, int tableNumber, int menuItemID, int numberOfPeople, Date reservationDate, Time reservationTime) throws SQLException {
       String sql = "INSERT INTO Reservations (CustomerName, TableNumber, MenuItemID, NumberOfPeople, ReservationDate, ReservationTime) VALUES (?, ?, ?, ?, ?, ?)";
       try (PreparedStatement statement = connection.prepareStatement(sql)) {
           statement.setString(1, customerName);
           statement.setInt(2, tableNumber);
           statement.setInt(3, menuItemID);
           statement.setInt(4, numberOfPeople);
           statement.setDate(5, reservationDate);
           statement.setTime(6, reservationTime);
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




   public void addEmployee(String employeeName, String position, double salary) throws SQLException {
       String sql = "INSERT INTO EmployeeInfo (EmployeeName, Position, Salary) VALUES (?, ?, ?)";


       try (PreparedStatement statement = connection.prepareStatement(sql)) {
           statement.setString(1, employeeName);
           statement.setString(2, position);
           statement.setDouble(3, salary);
           statement.executeUpdate();
       }
   }




   public void removeEmployee(int employeeID) throws SQLException {
       String sql = "DELETE FROM EmployeeInfo WHERE EmployeeID = ?";


       try (PreparedStatement statement = connection.prepareStatement(sql)) {
           statement.setInt(1, employeeID);
           statement.executeUpdate();
       }
   }




public void getAllEmployees() throws SQLException {
   String sql = "SELECT EmployeeID, EmployeeName, Position, Salary FROM EmployeeInfo";
   try (PreparedStatement statement = connection.prepareStatement(sql);
       ResultSet resultSet = statement.executeQuery()) {
       while (resultSet.next()) {
           System.out.print("EmployeeID: " + resultSet.getInt("EmployeeID") + " ");
           System.out.print("EmployeeName: " + resultSet.getString("EmployeeName")+ " ");
           System.out.print("Position: " + resultSet.getString("Position")+ " ");
           System.out.print("Salary: " + resultSet.getDouble("Salary") + " ");
           System.out.println("----------------------");
      
    }
   }
 }


 public List<String> getMenuItems() throws SQLException {
       List<String> menuItems = new ArrayList<>();
       String sql = "SELECT DISTINCT MenuItemID, ItemName, Category, Price, AvailableTime FROM Menu LIMIT 18";
       try (PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
           while (resultSet.next()) {
               String item = resultSet.getInt("MenuItemID") + ", " +
                             resultSet.getString("ItemName") + ", " +
                             resultSet.getString("Category") + ", Price: $" +
                             resultSet.getDouble("Price") + ", Available at: " +
                             resultSet.getTime("AvailableTime").toString();
               menuItems.add(item);
           }
       }
       return menuItems;
   }

   public void getAllReservations() throws SQLException{
    String sql = "SELECT CustomerName, TableNumber, MenuItemID, NumberOfPeople, ReservationTime, ReservationDate FROM Reservations";
   try (PreparedStatement statement = connection.prepareStatement(sql);
       ResultSet resultSet = statement.executeQuery()) {
       while (resultSet.next()) {
           System.out.println("Customer Name: " + resultSet.getString("CustomerName") + " ");
           System.out.print("Table Number: " + resultSet.getInt("TableNumber") + " ");
           System.out.print("Menu Item: " + resultSet.getInt("MenuItemId") + " ");
           System.out.print("Number of People: " + resultSet.getInt("NumberOfPeople")+ " ");
           System.out.print("Reservation Time: " + resultSet.getTime("ReservationTime")+ " ");
           System.out.println("Reservation Date " + resultSet.getDate("ReservationDate"));
           System.out.println("----------------------");
      
    }
   }
   }
}

