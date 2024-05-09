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

   /**
     * Constructs a DAL instance with a connection to the specified database.
     *
     * @param databaseName The name of the database.
     * @param user         The username for database access.
     * @param password     The password for database access.
     */
   public DAL(String databaseName, String user, String password) {
       this.connection  = getMySQLConnection(databaseName, user, password);
   }


   /**
     * Establishes a connection to a MySQL database.
     *
     * @param databaseName The name of the database.
     * @param user         The username for database access.
     * @param password     The password for database access.
     * @return A Connection object representing the established database connection.
     */
   private Connection getMySQLConnection(String databaseName, String user, String password) {
       String jdbcUrl = "jdbc:mysql://localhost:3306/" + databaseName;
       try {
           return DriverManager.getConnection(jdbcUrl, user, password);
       } catch (SQLException exception) {
           System.out.println("Failed to connect to the database: " + exception.getMessage());
           return null;
       }
   }


   /**
     * Closes the database connection.
     *
     * @throws SQLException If a database access error occurs.
     */
   public void closeConnection() throws SQLException {
       if (connection != null && !connection.isClosed()) {
           connection.close();
       }
   }


   /**
     * Retrieves a list of tables with limited information.
     *
     * @return A list containing table information.
     * @throws SQLException If a database access error occurs.
     */
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

    /**
     * Adds a reservation to the database.
     *
     * @param customerName   The name of the customer making the reservation.
     * @param tableNumber    The number of the reserved table.
     * @param menuItemID     The ID of the reserved menu item.
     * @param numberOfPeople The number of people for the reservation.
     * @param reservationDate The date of the reservation.
     * @param reservationTime The time of the reservation.
     * @throws SQLException If a database access error occurs.
     */
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


   /**
    * Removes a reservation from the database.
    *
    * @param reservationID The ID of the reservation to be removed.
    * @throws SQLException If a database access error occurs.
    */
   public void removeReservation(int reservationID) throws SQLException {
       String sql = "DELETE FROM Reservations WHERE ReservationID = ?";
       try (PreparedStatement statement = connection.prepareStatement(sql)) {
           statement.setInt(1, reservationID);
           statement.executeUpdate();
       }
   }


   /**
    * Adds an employee to the database.
    *
    * @param employeeName The name of the employee.
    * @param position     The position of the employee.
    * @param salary       The salary of the employee.
    * @throws SQLException If a database access error occurs.
    */
   public void addEmployee(String employeeName, String position, double salary) throws SQLException {
       String sql = "INSERT INTO EmployeeInfo (EmployeeName, Position, Salary) VALUES (?, ?, ?)";


       try (PreparedStatement statement = connection.prepareStatement(sql)) {
           statement.setString(1, employeeName);
           statement.setString(2, position);
           statement.setDouble(3, salary);
           statement.executeUpdate();
       }
   }


   /**
    * Removes an employee from the database.
    *
    * @param employeeID The ID of the employee to be removed.
    * @throws SQLException If a database access error occurs.
    */
   public void removeEmployee(int employeeID) throws SQLException {
       String sql = "DELETE FROM EmployeeInfo WHERE EmployeeID = ?";


       try (PreparedStatement statement = connection.prepareStatement(sql)) {
           statement.setInt(1, employeeID);
           statement.executeUpdate();
       }
   }


   /**
    * Retrieves information about all employees from the database.
    *
    * @throws SQLException If a database access error occurs.
    */
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

    /**
    * Retrieves a list of menu items from the database.
    *
    * @return A list containing menu item information.
    * @throws SQLException If a database access error occurs.
    */
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

   /**
    * Retrieves information about all reservations from the database.
    *
    * @throws SQLException If a database access error occurs.
    */
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
           System.out.println("Reservation Date: " + resultSet.getDate("ReservationDate"));
           System.out.println("----------------------");
      
    }
   }
   }

   /**
    * Retrieves a list of menu items from the database in alphabetical order.
    *
    * @return A list containing menu item information.
    * @throws SQLException If a database access error occurs.
    */
    public List<String> getMenuItemsAlphabetical() throws SQLException {
        List<String> menuItems = new ArrayList<>();
        String sql = "SELECT DISTINCT MenuItemID, ItemName, Category, Price, AvailableTime FROM Menu ORDER BY ItemName";
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
}

