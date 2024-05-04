package src;
//Connect to database, implement and run main method

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class PresentationLayer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter database username:");
        String username = scanner.nextLine();
        System.out.println("Enter database password:");
        String password = scanner.nextLine();

        DAL dal = new DAL("Restaurant Management", username, password);

        int choice;
        do {
            System.out.println("Restaurant Management");
            //make list of choices
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            System.out.println("Restauant Management");
            System.out.println("1. Add A Reservation");
            System.out.println("2. Delete A Reservation");
            System.out.println("3. Add an Employee");
            System.out.println("4. Remove an Employee");
            System.out.println("5. View Menu");
            System.out.println("6. Exit");
            System.out.println("Enter your choice:");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    //workflow 1 (dal);
                    break;
                case 2:
                    //workflow 2 (dal, scanner);
                    break;
                case 3:
                    //workflow 3 (dal, scanner);
                    break;
                case 4:
                    //workflow 4(dal);
                    break;
                case 5:
                    //workflow 5 (dal, scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
            }
        } while (choice != 6);

     

        // Close connection
        try {
            dal.closeConnection();
        } catch (SQLException exception) {
            System.out.println("Failed to close connection: " + exception.getMessage());
        }
    
    }
}