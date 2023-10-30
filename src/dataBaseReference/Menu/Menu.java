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
    
    public Menu() {
    	scanner = new Scanner(System.in);
        selectedDB.initializeConnection();
    }
    
    public void displayMenu() {
        System.out.println("\n==========================================");
        System.out.println("|               Main Menu                |");
        System.out.println("==========================================");
    	System.out.println("| 1. Customer Menu                       |");
        System.out.println("| 2. Order Menu                          |");
        System.out.println("| 3. Reports Menu                        |");
        System.out.println("| 4. Information Menu                    |");
        System.out.println("| 5. Exit                                |");
        System.out.println("==========================================");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
            	new CustomerMenu(selectedDB).displayCustomerMenu();
                displayMenu();
                break;
            case 2:
            	new OrderMenu(selectedDB).displayOrderMenu();
                displayMenu();
                break;
            case 3:
            	new ReportMenu(selectedDB).displayReportMenu();
                displayMenu();
                break;
            case 4:
            	new InformationMenu(selectedDB).displayInformationMenu();
                displayMenu();
                break;
            case 5:
                System.out.println("\nFinishing program. Thank you for using it!");
                break;
            default:
                System.err.println("\nInvalid option. Please try again.");
                displayMenu();
        }
    }
    
    public void close() {
    	scanner.close();
        selectedDB.endConnection();
    }
}
