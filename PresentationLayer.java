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

     

        // Close connection
        try {
            dal.closeConnection();
        } catch (SQLException exception) {
            System.out.println("Failed to close connection: " + exception.getMessage());
        }
    }
    }
}