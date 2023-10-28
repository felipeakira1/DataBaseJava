package dataBaseReference.CRUD;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dataBaseReference.DAO.AbstractOrderDAO;
import dataBaseReference.DTO.Orders;

public class CRUD_Order {
	private Scanner scanner;
	
	public CRUD_Order() {
		scanner = new Scanner(System.in);
	}
	
	public void insertOrder(AbstractOrderDAO orderDAO) {
	    System.out.print("Enter the number of orders you want to insert: ");
	    int numOrders = scanner.nextInt();
	    scanner.nextLine(); // Consume the newline character

	    for (int i = 0; i < numOrders; i++) {
	        Orders newOrder = new Orders();
	        
	        System.out.print("\nEnter order number: ");
	        int number = scanner.nextInt();
	        newOrder.setNumber(number);
	        
	        System.out.print("Enter customer ID: ");
	        int costumerId = scanner.nextInt();
	        newOrder.setCustomerId(costumerId);
	        
	        System.out.print("Enter order description: ");
	        scanner.nextLine();
	        String description = scanner.nextLine();
	        newOrder.setDescription(description);
	        
	        System.out.print("Enter order price: US$ ");
	        float price = new Float(scanner.nextLine());
	        newOrder.setPrice(price);	
	        
	        //insert the order into the database
	        try {
	            orderDAO.addOrder(newOrder);
	            System.out.println("Order added successfully. Order Number: " + newOrder.getNumber());
	        } catch (SQLException e) {
	            System.err.println("Error inserting customer: " + e.getMessage());
	        }
	    }
	}
	
	
	public void queryOrderByNumber(AbstractOrderDAO orderDAO) {
		System.out.print("Enter the order's number to query: ");
        int orderNumber = scanner.nextInt();
		try {
	    	Orders order = orderDAO.getOrderByNumber(orderNumber);

	        if (order != null) {
	        	System.out.println("\n======================");
	        	System.out.println("| Order Information: |");
	        	System.out.println("======================");
	            System.out.println("Order Nº: "     + order.getNumber());
	            System.out.println("Customer ID: " 	+ order.getCustomerId());
	            // Adicionar Customer name
	            System.out.println("Description: " 	+ order.getDescription());
	            System.out.println("Price: US$ " 	+ order.getPrice());
	            System.out.println("-----------------------");
	        } else {
	            System.out.println("Order with number " + orderNumber + " not found.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Error querying order: " + e.getMessage());
	    }
	}
	
	
	public void deleteOrderByNumber(AbstractOrderDAO orderDAO) {
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
	
	public void close() {
		scanner.close();
	}
}
