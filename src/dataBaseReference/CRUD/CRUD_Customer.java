package dataBaseReference.CRUD;
import java.sql.SQLException;
import java.util.List;
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
	        
	        System.out.print("\nEnter customer name: ");
	        String name = scanner.nextLine();
	        newCustomer.setName(name);
	        
	        System.out.print("Enter customer city: ");
	        String city = scanner.nextLine();
	        newCustomer.setCity(city);
	        
	        System.out.print("Enter customer state: ");
	        String state = scanner.nextLine();
	        newCustomer.setState(state);
	        
	        while(true) {
		        System.out.print("Enter customer identifier (" + lowerBound + " - " + upperBound + "): ");
		        int id = scanner.nextInt();
		        scanner.nextLine(); //consume the newline character
		        
		        if (id < lowerBound || id > upperBound) {
		            System.err.println("\nInvalid identifier. It must be in the range of " + lowerBound + " - " + upperBound + ".\n"); 
		        } else {
		        	newCustomer.setId(id);
		        	break;
		        }
	        }
	        
	        //insert the customer into the database
	        try {
	            customerDAO.addCustomer(newCustomer);
	            System.out.println("Customer added successfully. Id: " + newCustomer.getId());
	        } catch (SQLException e) {
	            System.err.println("Error inserting customer: " + e.getMessage());
	        }
	    }
	}
	
	
	public void queryCustomerById(AbstractCustomerDAO customerDAO, int customerId) {
	    try {
	        Customer customer = customerDAO.getCustomerById(customerId);

	        if (customer != null) {
	        	System.out.println("\n=========================");
	        	System.out.println("| Customer Information: |");
	        	System.out.println("=========================");
	            System.out.println("ID: " 		+ customer.getId());
	            System.out.println("Name: " 	+ customer.getName());
	            System.out.println("City: " 	+ customer.getCity());
	            System.out.println("State: " 	+ customer.getState());
	            System.out.println("-------------------------");

	        } else {
	            System.out.println("Customer with ID " + customerId + " not found.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Error querying customer: " + e.getMessage());
	    }

	}
	
	public void queryCustomerByName(AbstractCustomerDAO customerDAO, String customerName) {
		try {
			List<Customer> customers = customerDAO.getCustomersByName(customerName);
			if(!customers.isEmpty()) {
				for(Customer costumer : customers) {
		        	System.out.println("\n=========================");
		        	System.out.println("| Customer Information: |");
		        	System.out.println("=========================");
		            System.out.println("ID: " 		+ costumer.getId());
		            System.out.println("Name: " 	+ costumer.getName());
		            System.out.println("City: " 	+ costumer.getCity());
		            System.out.println("State: " 	+ costumer.getState());
		            System.out.println("-------------------------");
				}
			} else {
				System.out.println("Customer with name " + customerName + " not found.");
			}
			
		} catch (SQLException e) {
	        System.err.println("Error querying customer: " + e.getMessage());
	    }
	}
	
	public void deleteCustomerById(AbstractCustomerDAO customerDAO, int customerId) {
		try {
			customerDAO.deleteCustomer(customerId);
		} catch (SQLException e) {
	        System.err.println("Error deleting customer: " + e.getMessage());
	    }
	}




}