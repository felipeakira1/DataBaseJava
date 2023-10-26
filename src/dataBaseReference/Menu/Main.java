package dataBaseReference.Menu;

import java.util.Scanner;
//import java.sql.SQLException;
import java.util.InputMismatchException;

import dataBaseReference.CRUD.CRUD_Customer;
import dataBaseReference.CRUD.CRUD_Information;
import dataBaseReference.CRUD.CRUD_Order;
import dataBaseReference.CRUD.CRUD_Report;
import dataBaseReference.DAO.AbstractCustomerDAO;
import dataBaseReference.DAO.AbstractOrderDAO;
import dataBaseReference.System.DataBaseType;
import dataBaseReference.System.Controller;

public class Main {
    private Scanner scanner;
    public Controller selectedDB = new Controller(DataBaseType.MEMORY); //chose MemoryDB
    public CRUD_Customer customerCRUD;
    public CRUD_Order orderCRUD;
    public CRUD_Report reportCRUD;
    
    public Main() {
        scanner = new Scanner(System.in);
        selectedDB.initializeConnection();
        customerCRUD = new CRUD_Customer();
        orderCRUD = new CRUD_Order();
        reportCRUD = new CRUD_Report();
    }

    public void displayMainMenu() {

    	System.out.println("\n==============");
    	System.out.println("| Main Menu: |");
    	System.out.println("==============");
        System.out.println("1. Customer Menu");
        System.out.println("2. Order Menu");
        System.out.println("3. Reports Menu");
        System.out.println("4. Information Menu");
        System.out.println("5. Exit");
        System.out.println("==============");
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
                System.out.println("\nFinishing program. Thank you for using it!");
                break;
            default:
                System.err.println("\nInvalid option. Please try again.");
                displayMainMenu();
        }
    }

    public void displayCustomerMenu() {

    	System.out.println("\n==================");
    	System.out.println("| Customer Menu: |");
    	System.out.println("==================");
        System.out.println("1. Insert customers | Check value range of the group");
        System.out.println("2. Query customer by identifier");
        System.out.println("3. Query customer by name");
        System.out.println("4. Delete customer by identifier");
        System.out.println("5. Back to Main Menu");
        System.out.println("==================");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); //consume newline character
        switch (choice) {
            case 1:
	        	System.out.println("\n=======================");
	            System.out.println("| Inserting Customers |");
	        	System.out.println("=======================");
	
	             //inserting customers
	            customerCRUD.insertCustomers(selectedDB.getCustomerDAO(), 5);
	            
	            displayCustomerMenu();
                break;
            case 2:
           	 	System.out.println("\n==================");
                System.out.println("| Querying by ID |");
           	 	System.out.println("==================");

                System.out.print("Enter the customer ID to query: ");
                int customerId = scanner.nextInt();
                scanner.nextLine(); //consume the newline character
                AbstractCustomerDAO customerDAO = selectedDB.getCustomerDAO();
                customerCRUD.queryCustomerById(customerDAO, customerId);
                
                displayCustomerMenu();
                break;
            case 3:
            	System.out.println("\n====================");
                System.out.println("| Querying by Name |");
           	 	System.out.println("====================");
                System.out.print("Enter the customer name to query: ");
                String customerName = scanner.nextLine();
                //scanner.nextLine(); ???
                customerCRUD.queryCustomerByName(selectedDB.getCustomerDAO(), customerName);
                
                displayCustomerMenu();
                break;
            case 4:
            	System.out.println("\n==================");
                System.out.println("| Deleting by ID |");
           	 	System.out.println("==================");
           	 	
           	 	boolean inputValid = false;
           	 	customerId = 0;
           	 	
           	 	while(!inputValid) {
               	 	try {
               	 		System.out.print("Enter the customer ID to delete: ");
               	 		customerId = scanner.nextInt();
               	 		scanner.nextLine();
               	 		customerCRUD.deleteCustomerById(selectedDB.getCustomerDAO(), customerId);
               	 		System.out.println("Customer " + customerId + " deleted successfully");
               	 		inputValid = true;
               	 	} catch(InputMismatchException e) {
               	 		System.err.println("Invalid input. Please enter a valid integer for the customer ID.");
               	 		scanner.nextLine();
               	 	}
               	 	// verificar se ele sairá do laço caso houver um sqlexception
           	 	}
           	 	
           	 	displayCustomerMenu();
                break;
            case 5:
            	displayMainMenu();
                break;
            default:
                System.err.println("\nInvalid option. Please try again.");
                displayCustomerMenu();
        }
    }

    // Implement similar methods for the other menus: Order, Reports, and Information

    public void displayOrderMenu() {

    	System.out.println("\n================");
    	System.out.println("| Orders Menu: |");
    	System.out.println("================");
        System.out.println("1. Insert order for a customer");
        System.out.println("2. Query order by number");
        System.out.println("3. Delete order by number");
        System.out.println("5. Back to Main Menu");
        System.out.println("================");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
            	System.out.println("\n==============================");
            	System.out.println("| Insert Order for Customer: |");
            	System.out.println("==============================");
            	
            	orderCRUD.insertOrder(selectedDB.getOrdersDAO());
            	displayOrderMenu();
                break;
            case 2:
            	System.out.println("\n=======================");
            	System.out.println("| Querying by Number: |");
            	System.out.println("=======================");
            	
                System.out.print("Enter the order's number to query: ");
                int orderNumber = scanner.nextInt();
                scanner.nextLine(); //consume the newline character
                
                AbstractOrderDAO orderDAO = selectedDB.getOrdersDAO();
                orderCRUD.queryOrderByNumber(orderDAO, orderNumber);
            	displayOrderMenu();
                break;
            case 3:
            	System.out.println("\n======================");
                System.out.println("| Deleting by Number |");
           	 	System.out.println("======================");
           	 	
           	 	boolean inputValid = false;
           	 	orderNumber = 0;
           	 	
           	 	while(!inputValid) {
               	 	try {
               	 		System.out.print("Enter the order's number to delete: ");
               	 		orderNumber = scanner.nextInt();
               	 		scanner.nextLine();
               	 		orderCRUD.deleteOrderByNumber(selectedDB.getOrdersDAO(), orderNumber);
               	 		System.out.println("Order " + orderNumber + " deleted successfully");
               	 		inputValid = true;
               	 	} catch(InputMismatchException e) {
               	 		System.err.println("Invalid input. Please enter a valid integer for the order number.");
               	 		scanner.nextLine();
               	 	}
           	 	}
            	displayOrderMenu();
                break;
            case 5:
            	displayMainMenu();
                break;
            default:
                System.out.println("\nInvalid option. Please try again.");
                displayOrderMenu();
        }
    }

    public void displayReportsMenu() {

    	System.out.println("\n=================");
    	System.out.println("| Reports Menu: |");
    	System.out.println("=================");
        System.out.println("1. Customers ordered by identifier");
        System.out.println("2. Customers ordered by name");
        System.out.println("3. Orders ordered by number");
        System.out.println("4. Orders of customers ordered by name");
        System.out.println("5. Back to Main Menu");
        System.out.println("=================");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                // Implement customer insertion and group value check
            	displayReportsMenu();
                break;
            case 2:            	
                AbstractCustomerDAO customerDAO = selectedDB.getCustomerDAO();
                reportCRUD.queryCustomersOrderedByName(customerDAO);
            	displayReportsMenu();
                break;
            case 3:
            	AbstractOrderDAO orderDAO = selectedDB.getOrdersDAO();
                reportCRUD.queryOrdersOrderedByNumber(orderDAO);
            	displayReportsMenu();
                break;
            case 4:
                // Implement customer deletion by identifier
            	displayReportsMenu();
                break;
            case 5:
            	displayMainMenu();
                break;
            default:
                System.out.println("\nInvalid option. Please try again.");
                displayReportsMenu();
        }
    }

    public void displayInformationMenu() {

    	System.out.println("\n=====================");
    	System.out.println("| Information Menu: |");
    	System.out.println("=====================");
        System.out.println("1. Help");
        System.out.println("2. About");
        System.out.println("5. Back to Main Menu");
        System.out.println("=====================");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                CRUD_Information.helpInformation();
            	displayInformationMenu();
                break;
            case 2:
            	CRUD_Information.aboutProgram();
            	displayInformationMenu();
                break;
            case 5:
            	displayMainMenu();
                break;
            default:
                System.err.println("\nInvalid option. Please try again.");
                displayInformationMenu();
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
