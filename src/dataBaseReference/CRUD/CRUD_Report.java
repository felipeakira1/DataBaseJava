package dataBaseReference.CRUD;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dataBaseReference.DAO.AbstractCustomerDAO;
import dataBaseReference.DAO.AbstractOrderDAO;
import dataBaseReference.DTO.Customer;
import dataBaseReference.DTO.Orders;
import dataBaseReference.System.Controller;

public class CRUD_Report {
	private Scanner scanner;
	private AbstractCustomerDAO customerDAO;
	private AbstractOrderDAO orderDAO;
	
	public CRUD_Report(Controller selectedDB) {
		scanner = new Scanner(System.in);
		customerDAO = selectedDB.getCustomerDAO();
		orderDAO = selectedDB.getOrdersDAO();
	}
	
	public void displayTitle(String title) {
    	System.out.println();
    	System.out.println("=".repeat(title.length() + 4));
    	System.out.println("| " + title + " |");
    	System.out.println("=".repeat(title.length() + 4));
    }
	
	public void displayOrdersTableHeader() {
	    System.out.println("=".repeat(64));
	    System.out.format("%-12s | %-12s | %-20s | %-10s%n", "Order Number", "Customer ID", "Description", "Price");
	    System.out.println("=".repeat(64));
	}

	public void displayOrdersTableRow(Orders order) {
	    System.out.format("%-12s | %-12s | %-20s | %-10s%n",
	            order.getNumber(), order.getCustomerId(), order.getDescription(), "US$ " + order.getPrice());
	}
	
	public void displayCustomerTableHeader() {
	    System.out.println("=".repeat(60));
	    System.out.format("%-7s | %-20s | %-15s | %-10s%n", "ID", "Name", "City", "State");
	    System.out.println("=".repeat(60));
	}

	public void displayCustomerTableRow(Customer customer) {
	    System.out.format("%-7s | %-20s | %-15s | %-10s%n",
	            customer.getId(), customer.getName(), customer.getCity(), customer.getState());
	}

	
	public void queryCustomersOrderedById() {
    	displayTitle("List of Customers Ordered by Id");
	    try {
	    	List<Customer> listOfCustomers = customerDAO.getAllCustomersOrderedById();
	    	if(!listOfCustomers.isEmpty()) {
	    		displayCustomerTableHeader();
	    		for(Customer customer : listOfCustomers) {
		    		displayCustomerTableRow(customer);
		    		System.out.println("-".repeat(60));
		    	}
	    	} else {
	    		System.out.println("No customers found.");
	    	}
	    	
	    } catch (SQLException e) {
	        System.err.println("Error querying report: " + e.getMessage());
	    }
	}
	
	public void queryCustomersOrderedByName() {
    	displayTitle("List of Customers Ordered by Name");
    	
	    try {
	    	List<Customer> listOfCustomers = new ArrayList<>();
	    	listOfCustomers = customerDAO.getAllCustomersOrderedByName();
	    	if(!listOfCustomers.isEmpty()) {
	    		displayCustomerTableHeader();
	    		for(Customer customer : listOfCustomers) {
		    		displayCustomerTableRow(customer);
		    		System.out.println("-".repeat(60));
		    	}
	    	} else {
	    		System.out.println("No customers found.");
	    	}
	    } catch (SQLException e) {
	        System.err.println("Error querying report: " + e.getMessage());
	    }
	}
	
	public void queryOrdersOrderedByNumber() {
    	displayTitle("List of Orders Ordered by Number");
	    try {
			List<Orders> listOfOrders = new ArrayList<>();
	    	listOfOrders = orderDAO.getAllOrdersOrderedByNumber();
	    	if(!listOfOrders.isEmpty()) {
	    		displayOrdersTableHeader();
	    		for(Orders order : listOfOrders) {
	    			displayOrdersTableRow(order);
		    		System.out.println("-".repeat(64));
	    		}
	    	} else {
	    		System.out.println("No orders found.");
	    	}
	    } catch (SQLException e) {
	        System.err.println("Error querying report: " + e.getMessage());
	    }
	}
	
	public void getOrdersOrderedByCustomersName() {
		displayTitle("List of Orders of Customers ordered by name");
		try {
			List<Customer> customers = customerDAO.getAllCustomersOrderedByName();
			if(!customers.isEmpty()) {
				for(Customer customer : customers) {
					float totalPrice = 0;
					displayTitle("Customer: " + customer.getName());
					System.out.println("ID: " + customer.getId() + " | Name: " + customer.getName() + 
					           " | City: " + customer.getCity() + " | State: " + customer.getState());
					
					System.out.println();
					List<Orders> listOfOrders = orderDAO.getAllOrdersByCustomerIdOrderedByNumber(customer.getId());
					if(!listOfOrders.isEmpty()) {
						displayOrdersTableHeader();
						for(Orders order : listOfOrders) {
							displayOrdersTableRow(order);
							totalPrice += order.getPrice();
						}
					    System.out.println("=".repeat(64));
					    System.out.println("Total Price: US$ " + totalPrice);
					} else {
						System.out.println("No orders found for this customer.");
					}
				}
			} else {
				System.out.println("No customers found.");
			}
			
		} catch (SQLException e) {
	        System.err.println("Error querying report: " + e.getMessage());
	    }
	}
	
	
	public void deleteOrderByNumber(int orderNumber) {
		try {
			orderDAO.deleteOrder(orderNumber);
		} catch (SQLException e) {
	        System.err.println("Error deleting customer: " + e.getMessage());
	    }
	}
	
	public void close() {
		scanner.close();
		// customerDAO.close();
		// orderDAO.close();
	}
}
