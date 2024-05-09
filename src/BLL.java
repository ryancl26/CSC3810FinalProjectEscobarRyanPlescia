package src;
//add workflows into here once done

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

/**
 * Business Logic Layer class contains methods for handling business logic operations
 * related to the Restaurant Management System.
 */
public class BLL {

    /**
     * Retrieves and displays information about available tables from the database.
     * @param dal the Data Access Layer object used to interact with the database
     */
    public static void showTableInfo(DAL dal) {
        try {
            List<String> tables = dal.getTables();
            if (tables.isEmpty()) {
                System.out.println("No tables available.");
            } else {
                System.out.println("Available Tables:");
                tables.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve tables: " + e.getMessage());
        }
    }



    /**
     * Adds a reservation to the database.
     * @param dal the Data Access Layer object used to interact with the database
     * @param scanner the Scanner object used to read user input
     */
    public static void addReservation(DAL dal, Scanner scanner) {
       System.out.println("Enter customer name:");
       String customerName = scanner.nextLine();
       System.out.println("Enter table number:");
       int tableNumber = scanner.nextInt();
       System.out.println("Enter the ID of menu item:");
       int menuItemID = scanner.nextInt();
       System.out.println("Enter number of people:");
       int numberOfPeople = scanner.nextInt();
       System.out.println("Enter reservation date (YYYY-MM-DD):");
       String date = scanner.next();
       System.out.println("Enter reservation time (HH:MM:SS):");
       String time = scanner.next();
       scanner.nextLine();  // Consume newline


       try {
           dal.addReservation(customerName, tableNumber, menuItemID, numberOfPeople, Date.valueOf(date), Time.valueOf(time));
           System.out.println("Reservation added successfully.");
       } catch (SQLException e) {
           System.out.println("Failed to add reservation: " + e.getMessage());
       }
   }


   /**
     * Removes a reservation from the database.
     * @param dal the Data Access Layer object used to interact with the database
     * @param scanner the Scanner object used to read user input
     */
   public static void removeReservation(DAL dal, Scanner scanner) {
    System.out.println("Listing all reservations:");
    try {
       dal.getAllReservations();
    } catch (SQLException exception) {
       System.out.println("Failed to retrieve employees: " + exception.getMessage());
       return;
    }
       System.out.println("Enter the reservation ID to remove:");
       int reservationID = scanner.nextInt();
       scanner.nextLine();  // Consume newline


       try {
           dal.removeReservation(reservationID);
           System.out.println("Reservation removed successfully.");
       } catch (SQLException e) {
           System.out.println("Failed to remove reservation: " + e.getMessage());
       }
   }





   /**
     * Adds an employee to the database.
     * @param dal the Data Access Layer object used to interact with the database
     * @param scanner the Scanner object used to read user input
     */
    public static void addEmployee(DAL dal, Scanner scanner) {
        System.out.println("Adding a new employee...");
        System.out.println("Enter employee name:");
        String employeeName = scanner.nextLine();
        System.out.println("Enter employee position:");
        String position = scanner.nextLine();
        System.out.println("Enter employee salary:");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character


    try {
       dal.addEmployee(employeeName, position, salary);
       System.out.println("Employee added successfully.");
   } catch (SQLException exception) {
       System.out.println("Failed to add employee: " + exception.getMessage());
   }
}

    /**
     * Removes an employee from the database.
     * @param dal the Data Access Layer object used to interact with the database
     * @param scanner the Scanner object used to read user input
     */
    public static void removeEmployee(DAL dal, Scanner scanner) {
        System.out.println("Listing all employees:");
    try {
       dal.getAllEmployees();
   } catch (SQLException exception) {
       System.out.println("Failed to retrieve employees: " + exception.getMessage());
       return;
   }


   System.out.println("Enter the ID of the employee to remove:");
   int employeeID = scanner.nextInt();
   scanner.nextLine(); // Consume newline character


   try {
       dal.removeEmployee(employeeID);
       System.out.println("Employee removed successfully.");
   } catch (SQLException exception) {
       System.out.println("Failed to remove employee: " + exception.getMessage());
   }
}


    /**
     * Retrieves and displays the menu items from the database.
     * @param dal the Data Access Layer object used to interact with the database
     */
    public static void viewMenu(DAL dal) {
        try {
            List<String> menuItems = dal.getMenuItems();
            if (menuItems.isEmpty()) {
                System.out.println("No menu items available.");
            } else {
                System.out.println("Menu Items:");
                menuItems.forEach(System.out::println);
            }
    } catch (SQLException e) {
        System.out.println("Failed to retrieve menu items: " + e.getMessage());
    }
}

    /**
     * Retrieves and displays the menu items from the database in alphabetical order.
     *
     * @param dal the Data Access Layer object used to interact with the database
     */
    public static void viewMenuAlphabetical(DAL dal) {
        try {
            List<String> menuItems = dal.getMenuItemsAlphabetical();
            if (menuItems.isEmpty()) {
                System.out.println("No menu items available.");
            } else {
                System.out.println("Menu Items (Alphabetical Order):");
                menuItems.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve menu items: " + e.getMessage());
        }
    }

}


