package dataBaseReference.CRUD;
import java.sql.SQLException;
import java.util.Scanner;

import dataBaseReference.DAO.AbstractCustomerDAO;
import dataBaseReference.DTO.Customer;


public class CRUD_Customer {
	
																//group 5
	public void insertCustomers(AbstractCustomerDAO customerDAO, int group) {
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.print("Enter the number of customers you want to insert: ");
	    int numCustomers = scanner.nextInt();
	    scanner.nextLine(); // Consume the newline character

	    for (int i = 0; i < numCustomers; i++) {
	        Customer newCustomer = new Customer();
	        
	        
	        int lowerBound = group * 10000;
	        int upperBound = (group + 1) * 10000 - 1;
	        
	        System.out.print("Enter customer name: ");
	        String name = scanner.nextLine();
	        newCustomer.setName(name);
	        
	        System.out.print("Enter customer city: ");
	        String city = scanner.nextLine();
	        newCustomer.setCity(city);
	        
	        System.out.print("Enter customer state: ");
	        String state = scanner.nextLine();
	        newCustomer.setState(state);
	        
	        System.out.print("Enter customer identifier (" + lowerBound + " - " + upperBound + "): ");
	        int id = scanner.nextInt();
	        scanner.nextLine(); //consume the newline character
	        
	        if (id < lowerBound || id > upperBound) {
	            System.out.println("Invalid identifier. It must be in the range of " + lowerBound + " - " + upperBound + ".");
	            continue; 
	        }
	        
	        newCustomer.setId(id);
	        
	        //insert the customer into the database
	        try {
	            customerDAO.addCustomer(newCustomer);
	            System.out.println("Customer added successfully.");
	        } catch (SQLException e) {
	            System.err.println("Error inserting customer: " + e.getMessage());
	        }
	    }
	    scanner.close();
	}

	// Agora você pode chamar essa função no menu de clientes para inserir clientes

}