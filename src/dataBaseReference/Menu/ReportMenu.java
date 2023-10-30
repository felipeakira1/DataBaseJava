package dataBaseReference.Menu;

import java.util.Scanner;
import dataBaseReference.DAO.AbstractCustomerDAO;
import dataBaseReference.DAO.AbstractOrderDAO;
import dataBaseReference.CRUD.CRUD_Report;
import dataBaseReference.System.Controller;

public class ReportMenu {
	private Scanner scanner;
	private CRUD_Report reportCRUD;
	
	public ReportMenu(Controller selectedDB) {
		scanner = new Scanner(System.in);
		reportCRUD = new CRUD_Report(selectedDB);
	}
	
	public void displayReportMenu() {
    	System.out.println();
        System.out.println("\n==========================================");
        System.out.println("|             Reports Menu               |");
        System.out.println("==========================================");
        System.out.println("| 1. Customers ordered by identifier     |");
        System.out.println("| 2. Customers ordered by name           |");
        System.out.println("| 3. Orders ordered by number            |");
        System.out.println("| 4. Orders of customers ordered by name |");
        System.out.println("| 5. Back to Main Menu                   |");
        System.out.println("==========================================");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        
        switch (choice) {
        case 1:
            reportCRUD.queryCustomersOrderedById();
        	displayReportMenu();
            break;
        case 2:            	
            reportCRUD.queryCustomersOrderedByName();
        	displayReportMenu();
            break;
        case 3:
            reportCRUD.queryOrdersOrderedByNumber();
        	displayReportMenu();
            break;
        case 4:
            reportCRUD.getOrdersOrderedByCustomersName();
        	displayReportMenu();
            break;
        case 5:
        	return;
        default:
            System.out.println("\nInvalid option. Please try again.");
            displayReportMenu();
    }
	}
	
	public void close() {
		scanner.close();
		reportCRUD.close();
	}
}
