package dataBaseReference.CRUD;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dataBaseReference.DAO.AbstractOrderDAO;
import dataBaseReference.DTO.Orders;

public class CRUD_Order {
	public void insertOrder(AbstractOrderDAO orderDAO) {
	    Scanner scanner = new Scanner(System.in);
	    
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
	
	
	public void queryOrderByNumber(AbstractOrderDAO orderDAO, int orderNumber) {
	    try {
	    	Orders order = orderDAO.getOrderByNumber(orderNumber);

	        if (order != null) {
	        	System.out.println("\n======================");
	        	System.out.println("| Order Information: |");
	        	System.out.println("======================");
	            System.out.println("Order Nº: "     + order.getNumber());
	            System.out.println("Customer ID: " 	+ order.getCustomerId());
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
	
	
	public void deleteOrderByNumber(AbstractOrderDAO orderDAO, int orderNumber) {
		try {
			orderDAO.deleteOrder(orderNumber);
		} catch (SQLException e) {
	        System.err.println("Error deleting customer: " + e.getMessage());
	    }
	}
}
