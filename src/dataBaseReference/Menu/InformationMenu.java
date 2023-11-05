package dataBaseReference.Menu;

import java.util.Scanner;

import dataBaseReference.CRUD.CRUD_Customer;
import dataBaseReference.CRUD.CRUD_Information;
import dataBaseReference.CRUD.CRUD_Order;
import dataBaseReference.System.Controller;

public class InformationMenu {
	private Scanner scanner;
	private CRUD_Customer customerCRUD;
	private CRUD_Order orderCRUD;
	
	public InformationMenu(Controller selectedDB) {
		this.scanner = new Scanner(System.in);
		this.customerCRUD = new CRUD_Customer(selectedDB);
		this.orderCRUD = new CRUD_Order(selectedDB);
	}
	
	public void displayInformationMenu() {
        System.out.println("\n==========================================");
        System.out.println("|            Information Menu            |");
        System.out.println("==========================================");
        System.out.println("| 1. Help                                |");
        System.out.println("| 2. About                               |");
        System.out.println("| 3. Populate Database with Test Data    |");
        System.out.println("| 4. Clean Data                          |");
        System.out.println("| 5. Back to Main Menu                   |");
        System.out.println("==========================================");
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
            case 3:
            	customerCRUD.autoInsertCustomers();
            	orderCRUD.autoInsertOrders();
            	displayInformationMenu();
            	break;
            	
            case 4:
            	orderCRUD.deleteAllOrders(); 
            	customerCRUD.deleteAllCustomers();
            	displayInformationMenu();
            case 5:
            	return;
            default:
                System.err.println("\nInvalid option. Please try again.");
                displayInformationMenu();
        }
    }
	
	public void close() {
		scanner.close();
		customerCRUD.close();
		orderCRUD.close();
	}
}
