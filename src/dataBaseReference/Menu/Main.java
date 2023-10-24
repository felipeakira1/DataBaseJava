package dataBaseReference.Menu;

import java.util.Scanner;

import dataBaseReference.CRUD.CRUD_Customer;
import dataBaseReference.DAO.AbstractCustomerDAO;
import dataBaseReference.System.DataBaseType;
import dataBaseReference.System.Controller;

public class Main {
    private Scanner scanner;
    public Controller selectedDB = new Controller(DataBaseType.MEMORY); //chose MemoryDB
    public CRUD_Customer customerCRUD;
    
    public Main() {
        scanner = new Scanner(System.in);
        selectedDB.initializeConnection();
    }

    public void displayMainMenu() {
        boolean exit = false;

        System.out.println("Welcome to the Customer and Order System");
        System.out.println("Version: 1.0 (Last Update Date: 10/23/2023)");
        System.out.println("Main Menu:");

        while (!exit) {
            System.out.println("1. Customer Menu");
            System.out.println("2. Order Menu");
            System.out.println("3. Reports Menu");
            System.out.println("4. Information Menu");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    displayCustomerMenu();
                    break;
                case 2:
                    displayOrderMenu();
                    break;
                case 3:
                    displayReportsMenu();
                    break;
                case 4:
                    displayInformationMenu();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the program. Thank you for using it!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void displayCustomerMenu() {
        boolean exit = false;

        
        while (!exit) {
        	System.out.println("Customer Menu:");
            System.out.println("1. Insert customers | Check value range of the group");
            System.out.println("2. Query customer by identifier");
            System.out.println("3. Query customer by name");
            System.out.println("4. Delete customer by identifier");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); //consume newline character
            switch (choice) {
                case 1:
                     System.out.println("MemoryDB: Inserting customers");
                     customerCRUD = new CRUD_Customer();

                     //inserting customers
                     customerCRUD.insertCustomers(selectedDB.getCustomerDAO(), 5);
                        
              
                    break;
                case 2:
                    System.out.println("Querying by ID");

                    System.out.print("Enter the customer ID to query: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine(); //consume the newline character
                    customerCRUD = new CRUD_Customer();
                    AbstractCustomerDAO customerDAO = selectedDB.getCustomerDAO();
                    customerCRUD.queryCustomerById(customerDAO, customerId);
                    break;
                case 3:
                    // Implement customer query by name
                    break;
                case 4:
                    // Implement customer deletion by identifier
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Implement similar methods for the other menus: Order, Reports, and Information

    public void displayOrderMenu() {
        boolean exit = false;

        System.out.println("Order Menu:");
        while (!exit) {
            System.out.println("1. Insert order for a customer");
            System.out.println("2. Query order by number");
            System.out.println("3. Delete order by number");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Implement order insertion for a customer
                    break;
                case 2:
                    // Implement order query by number
                    break;
                case 3:
                    // Implement order deletion by number
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void displayReportsMenu() {
        boolean exit = false;

        System.out.println("Reports Menu:");
        while (!exit) {
            System.out.println("1. Customers ordered by identifier");
            System.out.println("2. Customers ordered by name");
            System.out.println("3. Orders ordered by number");
            System.out.println("4. Orders of customers ordered by name");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Implement customer insertion and group value check
                    break;
                case 2:
                    // Implement customer query by identifier
                    break;
                case 3:
                    // Implement customer query by name
                    break;
                case 4:
                    // Implement customer deletion by identifier
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void displayInformationMenu() {
        boolean exit = false;

        System.out.println("Information Menu:");
        while (!exit) {
            System.out.println("1. Help");
            System.out.println("2. About");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Implement help information
                    break;
                case 2:
                    // Implement about information
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void close() {
        scanner.close();
        selectedDB.endConnection();
    }

    public static void main(String[] args) {
        Main menu = new Main();
        menu.displayMainMenu();
        menu.close();
    }
}
