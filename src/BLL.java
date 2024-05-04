package src;
//add workflows into here once done


import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;


public class BLL {




//create reservation
public static void addReservation(DAL dal, Scanner scanner) {
       System.out.println("Enter customer name:");
       String customerName = scanner.nextLine();
       System.out.println("Enter table number:");
       int tableNumber = scanner.nextInt();
       System.out.println("Enter menu item ID:");
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


   public static void removeReservation(DAL dal, Scanner scanner) {
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




//add en employee
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


//remove an employee


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


//print menu
public static void viewMenu(DAL dal) {
       try {
           List<String> menuItems = dal.getMenuItems();
           if (menuItems.isEmpty()) {
               System.out.println("No menu items available.");
           } else {
               System.out.println("Menu Items:");
               for (String item : menuItems) {
                   System.out.println(item);
               }
           }
       } catch (SQLException e) {
           System.out.println("Failed to retrieve menu items: " + e.getMessage());
       }
   }
  
}


