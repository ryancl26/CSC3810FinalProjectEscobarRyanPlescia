package src;
//add workflows into here once done

import java.sql.SQLException;
import java.util.Scanner;

public class BLL {


//create reservation

//remove reservation

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




    
}
