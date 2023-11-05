package dataBaseReference.Menu;

import java.util.Scanner;

import dataBaseReference.CRUD.CRUD_Customer;
import dataBaseReference.CRUD.CRUD_Order;
import dataBaseReference.System.Controller;

public class OrderMenu {
	private Scanner scanner;
	private CRUD_Customer customerCRUD;
	private CRUD_Order orderCRUD;
	
	public OrderMenu(Controller selectedDB) {
		this.scanner = new Scanner(System.in);
		this.customerCRUD = new CRUD_Customer(selectedDB);
		this.orderCRUD = new CRUD_Order(selectedDB);
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
	
	public void displayOrderMenu() {
    	System.out.println();
        System.out.println("\n==========================================");
        System.out.println("|               Orders Menu              |");
        System.out.println("==========================================");
        System.out.println("| 1. Insert order for a customer         |");
        System.out.println("| 2. Query order by number               |");
        System.out.println("| 3. Delete order by number              |");
        System.out.println("| 4. Back to Main Menu                   |");
        System.out.println("==========================================");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        switch(choice) {
        	case 1:
        		displayTitle("Insert Order for Costumer");
        		int customerFound;
            	customerFound = customerCRUD.queryCustomerById();
            	if(customerFound == 404 || customerFound == 2) {
            		System.out.println("Cancelling Order insertion.");
            	}
            	else {
            		System.out.println("This Order refers to the Customer above\nID: " + customerFound);
            		orderCRUD.insertOrder(5,     customerFound);
            	}									//group		//customerID
            	displayOrderMenu();
                break;
        	case 2:
            	displayTitle("Querying Order by Number");
                orderCRUD.queryOrderByNumber();
            	displayOrderMenu();
                break;
            case 3:
           	 	displayTitle("Deleting Order by Number");
           	 	orderCRUD.deleteOrderByNumber();
            	displayOrderMenu();
                break;
            case 4:
            	return;
            default:
                System.out.println("\nInvalid option. Please try again.");
                displayOrderMenu();
        }
	}
	
	public void close() {
		scanner.close();
		customerCRUD.close();
	}
}
