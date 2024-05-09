package src;
//Connect to database, implement and run main method

import java.sql.SQLException;
import java.util.Scanner;


/**
 * PresentationLayer class represents the user interface for interacting with the Restaurant Management System.
 * It connects to the database, provides a menu for users to choose from, and executes corresponding actions.
 */
public class PresentationLayer {


    /**
    * The main method of the PresentationLayer class, which serves as the entry point of the application.
    * It prompts the user to enter database credentials, displays a menu, and handles user input to execute actions.
    * @param args command-line arguments passed to the program
    */
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       System.out.println("Enter database username:");
       String username = scanner.nextLine();
       System.out.println("Enter database password:");
       String password = scanner.nextLine();


       // Initialize Data Access Layer with provided credentials
       DAL dal = new DAL("RestaurantManagement", username, password);


       // Execute actions based on user's choice
       int choice;
       do {
           displayMenu();
           choice = getIntInput(scanner);


           switch (choice) {
               case 1:
                    BLL.showTableInfo(dal);  // Show table info before adding a reservation
                    BLL.addReservation(dal, scanner);
                   break;
               case 2:
                   BLL.removeReservation(dal, scanner);
                   break;
               case 3:
                   BLL.addEmployee(dal, scanner);
                   break;
               case 4:
                   BLL.removeEmployee(dal, scanner);
                   break;
               case 5:
                   BLL.viewMenu(dal);
                   break;
               case 6:
                   System.out.println("Exit Complete");
                   break;
               default:
                   System.out.println("Invalid choice. Please enter a number between 1 and 6.");
           }
       } while (choice != 6);
       
    
       // Close connection to the database
       try {
           dal.closeConnection();
       } catch (SQLException exception) {
           System.out.println("Failed to close connection: " + exception.getMessage());
       } finally {
           scanner.close();
       }
   }


   /**
    * Displays the menu options for the user.
    */
   private static void displayMenu() {
       System.out.println("Restaurant Management");
       System.out.println("1. Add A Reservation");
       System.out.println("2. Delete A Reservation");
       System.out.println("3. Add an Employee");
       System.out.println("4. Remove an Employee");
       System.out.println("5. View Menu");
       System.out.println("6. Exit");
       System.out.println("Enter your choice:");
   }


   /**
    * Reads an integer input from the user.
    * @param scanner the Scanner object used to read user input
    * @return the integer input provided by the user
    */
   private static int getIntInput(Scanner scanner) {
       while (true) {
           try {
               return Integer.parseInt(scanner.nextLine());
           } catch (NumberFormatException e) {
               System.out.println("Invalid input. Please enter a number.");
           }
       }
   }
}


