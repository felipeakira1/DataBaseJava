package dataBaseReference.CRUD;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import dataBaseReference.DAO.AbstractCustomerDAO;
import dataBaseReference.DAO.AbstractOrderDAO;
import dataBaseReference.DTO.Customer;
import dataBaseReference.DTO.Orders;
import dataBaseReference.System.Controller;

public class CRUD_Order {
	private Scanner scanner;
	private AbstractCustomerDAO customerDAO;
	private AbstractOrderDAO orderDAO;
	
	public CRUD_Order(Controller selectedDB) {
		scanner = new Scanner(System.in);
		customerDAO = selectedDB.getCustomerDAO();
		orderDAO = selectedDB.getOrdersDAO();
	}
	
	public void autoInsertOrders() {
		Orders order1 = new Orders(50001, 50001, "Sample Order 1", 100.0f);
        Orders order2 = new Orders(50002, 50002, "Sample Order 2", 75.0f);
        Orders order3 = new Orders(50003, 50003, "Sample Order 3", 120.0f);
        Orders order4 = new Orders(50004, 50004, "Sample Order 4", 50.0f);
		Orders order5 = new Orders(50005, 50001, "Sample Order 5", 80.0f);
        Orders order6 = new Orders(50006, 50002, "Sample Order 6", 90.0f);
        Orders order7 = new Orders(50007, 50003, "Sample Order 7", 65.0f);
        Orders order8 = new Orders(50008, 50004, "Sample Order 8", 95.0f);
        
        try {
            orderDAO.addOrder(order1);
            orderDAO.addOrder(order2);
            orderDAO.addOrder(order3);
            orderDAO.addOrder(order4);
            orderDAO.addOrder(order5);
            orderDAO.addOrder(order6);
            orderDAO.addOrder(order7);
            orderDAO.addOrder(order8);
            
            System.out.println("Sucessfully inserted generated orders!");
        } catch(SQLException e) {
        	System.err.println("Error inserting auto generated orders: " + e.getMessage());
        }
	}
														//group 5
	public void insertOrder(int group, int customerFound) {
		boolean inputValid = false;
		int lowerBound = group * 10000;
        int upperBound = (group + 1) * 10000 - 1;
        
        while(true) {
        	try {
        		System.out.print("\nEnter the number of orders you want to insert (0 to exit): ");
        	    int numOrders = scanner.nextInt();
        	    scanner.nextLine(); // Consume the newline character
        	    
        	    if(numOrders == 0) {
        	    	break;
        	    }

        	    for (int i = 0; i < numOrders; i++) {
        	    	inputValid = false;
        	        Orders newOrder = new Orders();
        	        
        	        while(!inputValid) {
        	        	try {
        	        		System.out.print("\nEnter order number (" + lowerBound + " - " + upperBound + "): ");
            		        int number = scanner.nextInt();
            		        
            		        if (number < lowerBound || number > upperBound) {
            		            System.err.println("\nInvalid identifier. It must be in the range of " + lowerBound + " - " + upperBound + ".\n"); 
            		        }else {
            		        	try {
            		        		Orders existingOrder = orderDAO.getOrderByNumber(number);
            		        		if(existingOrder != null) {
            		        			System.err.println("Order with this number already existis. Please chooce a differente number.");
            		        		} else {
                    		        	newOrder.setNumber(number);
                    		        	inputValid = true;
            		        		}
            		        	} catch(SQLException e) {
            		        		System.err.println("Error quering order to insert: " + e.getMessage());
            		        	}
            		        }
        	        	} catch(InputMismatchException e) {
        					System.err.println("Invalid input. Please enter a valid integer for the order number.");
        				    scanner.nextLine();
        	        	}
        	        }
        	        
        	        newOrder.setCustomerId(customerFound);
        	        
        	        System.out.print("Enter order description: ");
        	        scanner.nextLine();
        	        String description = scanner.nextLine();
        	        newOrder.setDescription(description);
        	        
        	        System.out.print("Enter order price: US$ ");
        	        float price = new Float(scanner.nextLine());
        	        newOrder.setPrice(price);	
        	        
        	        // Insert the order into the database
        	        try {
        	            orderDAO.addOrder(newOrder);
        	            System.out.println("Order added successfully. Order Number: " + newOrder.getNumber());
        	        } catch (SQLException e) {
        	            System.err.println("Error inserting customer: " + e.getMessage());
        	        }
        	    }
        	} catch(InputMismatchException e) {
				System.err.println("Invalid input. Please enter a valid integer for the number of orders.");
			    scanner.nextLine();
        	}
        }
	}
	
	public void queryOrderByNumber() {
		boolean inputValid = false;
		int orderNumber;
		while(!inputValid) {
			try {
				System.out.print("Enter the order's number to query: ");
		        orderNumber = scanner.nextInt();
			    scanner.nextLine();
			    
			    try {
			    	Orders order = orderDAO.getOrderByNumber(orderNumber);
			        if (order != null) {
			        	Customer customer = customerDAO.getCustomerById(order.getCustomerId());
			        	
			        	System.out.println("\n======================");
			        	System.out.println("| Order Information: |");
			        	System.out.println("======================");
			            System.out.println("Order Nº: "     + order.getNumber());
			            System.out.println("Customer ID: " 	+ order.getCustomerId());
			            System.out.println("Customer Name: " 	+ customer.getName());
			            System.out.println("Description: " 	+ order.getDescription());
			            System.out.println("Price: US$ " 	+ order.getPrice());
			            System.out.println("-----------------------");
			        } else {
			            System.out.println("Order with number " + orderNumber + " not found.");
			        }
			    } catch (SQLException e) {
			        System.err.println("Error querying order: " + e.getMessage());
			    }
	       	 	inputValid = true;
			} catch(InputMismatchException e) {
				System.err.println("Invalid input. Please enter a valid integer for the order number.");
			    scanner.nextLine();
			}
		}
	}
	
	public void deleteOrderByNumber() {
		boolean inputValid = false;
   	 	int orderNumber = 0;
   	 	
   	 	while(!inputValid) {
       	 	try {
       	 		System.out.print("Enter the order's number to delete: ");
       	 		orderNumber = scanner.nextInt();
       	 		scanner.nextLine();
	       	 	try {
	    			orderDAO.deleteOrder(orderNumber);
	    			System.out.println("Order " + orderNumber + " deleted successfully");
	    		} catch (SQLException e) {
	    	        System.err.println("Error deleting customer: " + e.getMessage());
	    	    }
	       	 	inputValid = true;
       	 	} catch(InputMismatchException e) {
       	 		System.err.println("Invalid input. Please enter a valid integer for the order number.");
       	 		scanner.nextLine();
       	 	}
   	 	}
		
	}
	
	public void deleteAllOrders() {
		try {
			orderDAO.deleteAllOrders();
			System.out.println("\nSucessfully cleared all orders!");
		} catch (SQLException e) {
			System.err.println("Error clearing all orders: " + e.getMessage());
		}
	}
	
	public void close() {
		scanner.close();
	}
}
