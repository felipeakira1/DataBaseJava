package dataBaseReference.Menu;

import java.util.Scanner;
import dataBaseReference.CRUD.CRUD_Customer;
import dataBaseReference.DAO.AbstractCustomerDAO;
import dataBaseReference.System.Controller;
import dataBaseReference.DAO.AbstractOrderDAO;

public class CustomerMenu {
	private Scanner scanner;
	public CRUD_Customer customerCRUD;
	
	public CustomerMenu(Controller selectedDB) {
		this.scanner = new Scanner(System.in);
		this.customerCRUD = new CRUD_Customer(selectedDB);
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
	
	public void displayCustomerMenu() {
		System.out.println();
        System.out.println("\n==========================================");
        System.out.println("|              Customer Menu             |");
        System.out.println("==========================================");
        System.out.println("| 1. Insert customers                    |");
        System.out.println("| 2. Query customer by identifier        |");
        System.out.println("| 3. Query customer by name              |");
        System.out.println("| 4. Delete customer by identifier       |");
        System.out.println("| 5. Back to Main Menu                   |");
        System.out.println("==========================================");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); //consume newline character
        switch(choice) {
        	case 1:
        		System.out.println("=======================");
        		System.out.println("| Inserting Customers |");
        		System.out.println("=======================");
	            customerCRUD.insertCustomers(5);
	            displayCustomerMenu();
	            break;
        	case 2:
	        	displayTitle("Querying Customer by ID");
	        	customerCRUD.queryCustomerById();
                displayCustomerMenu();
                break;
            case 3:
            	displayTitle("Querying Customer by name");
            	customerCRUD.queryCustomerByName();
                displayCustomerMenu();
                break;
            case 4:
            	displayTitle("Deleting Customer by ID");
           	 	customerCRUD.deleteCustomerById();         	 	
           	 	displayCustomerMenu();
                break;
            case 5:
            	return;
            default:
                System.err.println("\nInvalid option. Please try again.");
                displayCustomerMenu();
        }
	}
	
	public void close() {
		scanner.close();
		customerCRUD.close();
	}
}
