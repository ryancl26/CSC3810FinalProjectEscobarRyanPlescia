//add workflows into here once done

import java.sql.SQLException;
import java.util.Scanner;

public class BLL {

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

//create reservation

//remove reservation

//print menu




    
}
