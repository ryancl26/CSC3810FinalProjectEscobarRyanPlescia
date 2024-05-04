package src;
//Connect to database, implement and run main method


import java.sql.SQLException;
import java.util.Scanner;




public class PresentationLayer {


   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       System.out.println("Enter database username:");
       String username = scanner.nextLine();
       System.out.println("Enter database password:");
       String password = scanner.nextLine();


       DAL dal = new DAL("RestaurantManagement", username, password);


       int choice;
       do {
           displayMenu();
           choice = getIntInput(scanner);


           switch (choice) {
               case 1:
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
                   System.out.println("Exiting...");
                   break;
               default:
                   System.out.println("Invalid choice. Please enter a number between 1 and 6.");
           }
       } while (choice != 6);
       // Close connection
    
       try {
           dal.closeConnection();
       } catch (SQLException exception) {
           System.out.println("Failed to close connection: " + exception.getMessage());
       } finally {
           scanner.close();
       }
   }


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


