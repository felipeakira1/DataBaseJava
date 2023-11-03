package dataBaseReference.Menu;

import java.util.Scanner;
import java.sql.Connection;

import dataBaseReference.System.Controller;
import dataBaseReference.System.DataBaseType;

public class Menu {
	private Scanner scanner;
    public Controller selectedDB;
    
    public Menu() {
    	scanner = new Scanner(System.in);
    }
    
    public void displayChoiceMenu() {
    	System.out.println();
    	do {
    		System.out.println("Choose a database connection type:");
            System.out.println("1 - In-Memory Database");
            System.out.println("2 - MariaDB Connection");
            
            System.out.print("Enter your choice: ");
            int connectionType = Integer.parseInt(scanner.nextLine());
            
            if(connectionType == 1) {
            	selectedDB = new Controller(DataBaseType.MEMORY);
            	selectedDB.initializeConnection();
            	displayMainMenu();
            	break;
            } else if(connectionType == 2) {
            	selectedDB = new Controller(DataBaseType.MARIADB);
            	displayConnectionMenu();
            	if(selectedDB.getMariaDBConnection() == null) {
            		break;
            	}
            	displayMainMenu();
            	break;
            } else {
            	System.out.println("Invalid choice. Please choose 1 or 2.");
            }
    	} while(true);
    	
    }
    public void displayConnectionMenu() {
        System.out.println("\n============================================");
        System.out.println("|      Connection Configuration Menu       |");
        System.out.println("============================================");
    	
        String serverAdress;
        int port;
        String databaseName;
        String username;
        String password;
        
        do {
        	System.out.print("Server IP Address: ");
            serverAdress = scanner.nextLine();
            
            System.out.print("Service Port: ");
            port = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Database Name: ");
            databaseName = scanner.nextLine();
            
            System.out.print("Username: " );
            username = scanner.nextLine();
            
            System.out.print("Password: ");
            password = scanner.nextLine();
            
    		selectedDB.initializeConnection(serverAdress, port, databaseName, username, password);
    		
    		Connection myDBConnection = selectedDB.getMariaDBConnection();
    		if(myDBConnection == null) {
    			System.out.println("Database connection failed. Do you want to enter the data again?");
    			System.out.println("1 - Yes");
    			System.out.println("2 - No");
    			System.out.print("Enter your choice: ");
    			int choice = scanner.nextInt();
    			scanner.nextLine();
    			if(choice == 2) {
    				break;
    			}
    		}
        } while (selectedDB.getMariaDBConnection() == null);
    }
    
    public void displayMainMenu() {
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
                displayMainMenu();
                break;
            case 2:
            	new OrderMenu(selectedDB).displayOrderMenu();
                displayMainMenu();
                break;
            case 3:
            	new ReportMenu(selectedDB).displayReportMenu();
                displayMainMenu();
                break;
            case 4:
            	new InformationMenu(selectedDB).displayInformationMenu();
                displayMainMenu();
                break;
            case 5:
                break;
            default:
                System.err.println("\nInvalid option. Please try again.");
                displayMainMenu();
        }
    }
    
    public void close() {
        System.out.println("\nFinishing program. Thank you for using it!");
    	scanner.close();
        selectedDB.endConnection();
    }
}
