package dataBaseReference.Menu;

import java.util.Scanner;

import dataBaseReference.CRUD.CRUD_Customer;
import dataBaseReference.CRUD.CRUD_Information;
import dataBaseReference.CRUD.CRUD_Order;
import dataBaseReference.CRUD.CRUD_Report;
import dataBaseReference.DAO.AbstractCustomerDAO;
import dataBaseReference.DAO.AbstractOrderDAO;
import dataBaseReference.System.Controller;
import dataBaseReference.System.DataBaseType;

public class Menu {
	private Scanner scanner;
    public Controller selectedDB = new Controller(DataBaseType.MEMORY); //chose MemoryDB
    public CRUD_Customer customerCRUD;
    public CRUD_Order orderCRUD;
    public CRUD_Report reportCRUD;
    
    public Menu() {
    	scanner = new Scanner(System.in);
        selectedDB.initializeConnection();
        customerCRUD = new CRUD_Customer();
        orderCRUD = new CRUD_Order();
        reportCRUD = new CRUD_Report();
    }
    
    public void displayTitle(String title) {
    	System.out.println();
    	for(int i = 0; i < title.length() + 4; i++) {
    		System.out.print("=");
    	}
    	System.out.println("\n| " + title + " |");
    	for(int i = 0; i < title.length() + 4; i++) {
    		System.out.print("=");
    	}
    	System.out.println();
    }
    
    public void displayMenu() {
        displayTitle("Main Menu");
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
                displayMenu();
        }
    }
    
    public void displayCustomerMenu() {
        displayTitle("Customer Menu");
        System.out.println("1. Insert customers | Check value range of the group");
        System.out.println("2. Query customer by identifier");
        System.out.println("3. Query customer by name");
        System.out.println("4. Delete customer by identifier");
        System.out.println("5. Back to Main Menu");
        System.out.println("==================");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        AbstractCustomerDAO customerDAO = selectedDB.getCustomerDAO();
        AbstractOrderDAO orderDAO = selectedDB.getOrdersDAO();
        scanner.nextLine(); //consume newline character
        switch (choice) {
            case 1:
	        	displayTitle("Inserting Customers");
	            customerCRUD.insertCustomers(customerDAO, 5);
	            displayCustomerMenu();
                break;
            case 2:
	        	displayTitle("Querying Customer by ID");
	        	customerCRUD.queryCustomerById(customerDAO);
                displayCustomerMenu();
                break;
            case 3:
            	displayTitle("Querying Customer by name");
            	customerCRUD.queryCustomerByName(customerDAO);
                displayCustomerMenu();
                break;
            case 4:
            	displayTitle("Deleting Customer by ID");
           	 	customerCRUD.deleteCustomerById(customerDAO, orderDAO);         	 	
           	 	displayCustomerMenu();
                break;
            case 5:
            	displayMenu();
                break;
            default:
                System.err.println("\nInvalid option. Please try again.");
                displayCustomerMenu();
        }
    }
    
    public void displayOrderMenu() {
    	displayTitle("Orders Menu");
        System.out.println("1. Insert order for a customer");
        System.out.println("2. Query order by number");
        System.out.println("3. Delete order by number");
        System.out.println("5. Back to Main Menu");
        System.out.println("================");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        AbstractCustomerDAO customerDAO = selectedDB.getCustomerDAO();
        AbstractOrderDAO orderDAO = selectedDB.getOrdersDAO();
        switch (choice) {
            case 1:
            	displayTitle("Insert Order for Costumer");
            	//verifying customer existence
            	int customerFound;
            	customerFound = customerCRUD.queryCustomerById(customerDAO);
            	if(customerFound == 404 || customerFound == 2) {
            		System.out.println("Cancelling Order insertion.");
            	}
            	else {
            		System.out.println("This Order refers to the Customer above\nID: " + customerFound);
            		orderCRUD.insertOrder(orderDAO, 	5,     customerFound);
            	}									//group		//customerID
            	displayOrderMenu();
                break;
            case 2:
            	displayTitle("Querying Order by Number");
                orderCRUD.queryOrderByNumber(orderDAO, customerDAO);
            	displayOrderMenu();
                break;
            case 3:
           	 	displayTitle("Deleting Order by Number");
           	 	orderCRUD.deleteOrderByNumber(orderDAO);
            	displayOrderMenu();
                break;
            case 5:
            	displayMenu();
                break;
            default:
                System.out.println("\nInvalid option. Please try again.");
                displayOrderMenu();
        }
    }
    
    public void displayReportsMenu() {
    	displayTitle("Reports Menu");
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
            	displayMenu();
                break;
            default:
                System.out.println("\nInvalid option. Please try again.");
                displayReportsMenu();
        }
    }
    
    public void displayInformationMenu() {
    	displayTitle("Information Menu");
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
            	displayMenu();
                break;
            default:
                System.err.println("\nInvalid option. Please try again.");
                displayInformationMenu();
        }
    }
    
    public void close() {
    	scanner.close();
        selectedDB.endConnection();
    	customerCRUD.close();
    	orderCRUD.close();
    	// reportCRUD.close();
    }
}
