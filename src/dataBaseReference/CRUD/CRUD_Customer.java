package dataBaseReference.CRUD;
import java.sql.SQLException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dataBaseReference.DAO.AbstractCustomerDAO;
import dataBaseReference.DAO.AbstractOrderDAO;
import dataBaseReference.DTO.Customer;
import dataBaseReference.System.Controller;


public class CRUD_Customer {
	private Scanner scanner;
	private AbstractCustomerDAO customerDAO;
	private AbstractOrderDAO orderDAO;
	
	public CRUD_Customer(Controller selectedDB) {
		scanner = new Scanner(System.in);
		customerDAO = selectedDB.getCustomerDAO();
		orderDAO = selectedDB.getOrdersDAO();
	}
	
	public void autoInsertCustomers() {
		 	Customer customer1 = new Customer(50001, "John Doe", "New York", "NY");
		    Customer customer2 = new Customer(50002, "Alice Johnson", "Los Angeles", "CA");
		    Customer customer3 = new Customer(50003, "Bob Smith", "Chicago", "IL");
		    Customer customer4 = new Customer(50004, "Emily Davis", "Miami", "FL");
		    
			try {
		        customerDAO.addCustomer(customer1);
				customerDAO.addCustomer(customer2);
				customerDAO.addCustomer(customer3);
				customerDAO.addCustomer(customer4);
				
				System.out.println("\nSucessfully inserted generated customers!");
	        } catch (SQLException e) {
	            System.err.println("Error inserting auto generated customers: " + e.getMessage());
	        }
	}
	
	//group 5
	public void insertCustomers(int group) {
		while(true) {
			try {
				System.out.print("Enter the number of customers you want to insert (0 to exit): ");
			    int numCustomers = scanner.nextInt();
			    scanner.nextLine(); // Consume the newline character
			    if(numCustomers == 0) {
			    	break;
			    }

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
			        	try {
			        		System.out.print("Enter customer identifier (" + lowerBound + " - " + upperBound + "): ");
					        int id = scanner.nextInt();
					        scanner.nextLine(); // Consume the newline character
					        
					        if (id < lowerBound || id > upperBound) {
					            System.err.println("\nInvalid identifier. It must be in the range of " + lowerBound + " - " + upperBound + ".\n"); 
					        } else {
					        	// Check if the customer ID already exists
					        	try {
							        Customer existingCustomer = customerDAO.getCustomerById(id);
						        	if(existingCustomer != null) {
						        		System.err.println("Customer with this ID already exists. Please choose a different ID.");
						        	} else {
						        		newCustomer.setId(id);
						        		break;
						        	}
						        } catch(SQLException e) {
						        	System.err.println("Error quering customer to insert: " + e.getMessage());
						        }
					        }
			        	} catch(InputMismatchException e) {
			        		System.err.println("Invalid input. Please enter a valid integer for the customer ID.");
						    scanner.nextLine(); // Consume the newline character
			        	}
			        }
			        
			        // Insert the customer into the database
			        try {
			            customerDAO.addCustomer(newCustomer);
			            System.out.println("Customer added successfully. Id: " + newCustomer.getId() + "\n");
			        } catch (SQLException e) {
			            System.err.println("Error inserting customer: " + e.getMessage());
			        }
			    }
			} catch(InputMismatchException e) {
				System.err.println("Invalid input. Please enter a valid integer for the numbers of customers.");
			    scanner.nextLine(); // Consume the newline character
			}
		}
	}
	
	
	public int queryCustomerById() {
		int customerId;
		while(true) {
			try {
				System.out.print("Enter the customer ID to query: ");
			    customerId = scanner.nextInt();
			    scanner.nextLine();
			    
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
			            
			            return customerId; //customer found
			        } else {
			            System.out.println("Customer with ID " + customerId + " not found.");
			            return 404; //customer not found
			        }
			    } catch (SQLException e) {
			        System.err.println("Error querying customer: " + e.getMessage());
			        return 2;
			    }
			    
			} catch(InputMismatchException e) {
				System.err.println("Invalid input. Please enter a valid integer for the customer ID.");
			    scanner.nextLine();
			}
		}
	}
	
	public void queryCustomerByName() {
		System.out.print("Enter the customer name to query: ");
		String customerName = scanner.nextLine();
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
	
	public void deleteCustomerById() {
		boolean inputValid = false;
		int customerId = 0;
		while(!inputValid) {
       	 	try {
       	 		System.out.print("Enter the customer ID to delete (0 to exit): ");
       	 		customerId = scanner.nextInt();
       	 		scanner.nextLine();
       	 		
       	 		if(customerId == 0) {
       	 			break;
       	 		}
       	 		
       	 		Customer customer = customerDAO.getCustomerById(customerId);
       	 		if(customer != null) {
    	       	 	try {
    	       	 		orderDAO.deleteAllOrdersFromCustomer(customerId);
    	    			customerDAO.deleteCustomer(customerId);
    	    			System.out.println("Customer " + customerId + " deleted successfully");
    	       	 	} catch (SQLException e) {
    	    	        System.err.println("Error deleting customer: " + e.getMessage());
    	    	    }
           	 		
           	 		inputValid = true;
       	 		} else {
       	 			System.err.println("Customer ID not found in system! Please try again.");
       	 		}
       	 	} catch(InputMismatchException e) {
       	 		System.err.println("Invalid input. Please enter a valid integer for the customer ID.");
       	 		scanner.nextLine();
       	 	} catch(SQLException e) {
       	 		System.err.println("Error querying customer: " + e.getMessage());
       	 	}
   	 	}
	}
	
	public void deleteAllCustomers() {
		try {
			customerDAO.deleteAllCustomers();
			System.out.println("Sucessfully cleared all customers!");
		} catch (SQLException e) {
			System.err.println("Error clearing all customers: " + e.getMessage());
		}
	}
	
	public void close() {
		scanner.close();
	}
}