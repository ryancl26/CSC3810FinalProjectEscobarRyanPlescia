package src;
//add workflows into here once done

import java.sql.SQLException;
import java.util.Scanner;

public class BLL {


//create reservation
public static void addReservation(DAL dal, Scanner scanner) {
    System.out.println("Creating a new reservation");
    
    System.out.println("Enter customer name:");
    String customerName = scanner.nextLine();

    System.out.println("Enter table number:");
    String tableNumber = scanner.nextLine();

    System.out.println("Enter number of people");
    String numberOfPeople = scanner.nextLine();
    scanner.nextLine();

    System.out.println("Enter time of reservation");
    String TIME = scanner.nextLine();
    scanner.nextLine();

    try {
        dal.getAllReservation(customerName, 1, numberOfPeople, 1, 1, "18:30");
        System.out.println("Reservation created successfully.");
    } catch (SQLException exception) {
        System.out.println("Failed to create reservation.");
    }
}
//remove reservation
public static void removeReservation(DAL dal, Scanner scanner) {
    try {
        System.out.println("List all reservations");
        dal.getAllReservation(null, 0, null, 0, 0, null);
    } catch (SQLException exception) {
        System.out.println("Failed top get list of reservations: " + exception.getMessage());
        return;
    }
    System.out.println("Enter the ID of customer to remove:");
    int customerID = scanner.nextInt();
    scanner.nextLine();

    try {
        dal.removeReservation(customerID);
        System.out.println("Customer reservation removed successfully.");
    } catch (SQLException exception) {
        System.out.println("Failed to remove reservation: " + exception.getMessage());
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
    try {
        System.out.println("Listing all employees:");
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
public static void getAllMenuItems(DAL dal, Scanner scanner) {
    try {
        System.out.println("List all of the menu items");;
        dal.getAllMenuItems();
    } catch (SQLException exception) {
        System.out.println("Failed to list Menu items: " + exception.getMessage());
        return;
    }
    System.out.println("Enter the ID o item to view the item: ");
    int MenuItemID = scanner.nextInt();
    scanner.nextLine();

    try {
        dal.getAllMenuItems();
        System.out.println("Menu item removed successfully.");
    } catch (SQLException exception) {
        System.out.println("Failed to remove item from menu: " + exception.getMessage());
    }
}
}