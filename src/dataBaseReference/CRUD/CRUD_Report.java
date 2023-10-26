package dataBaseReference.CRUD;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataBaseReference.DAO.AbstractCustomerDAO;
import dataBaseReference.DAO.AbstractOrderDAO;
import dataBaseReference.DTO.Customer;
import dataBaseReference.DTO.Orders;

public class CRUD_Report {
	
	/*public void queryCustomersOrderedById(AbstractOrderDAO orderDAO, int customerId) {
	    try {

	    } catch (SQLException e) {
	        System.err.println("Error querying report: " + e.getMessage());
	    }
	}*/
	
	
	public void queryCustomersOrderedByName(AbstractCustomerDAO customerDAO) {
	    try {
	    	List<Customer> listOfCustomers = new ArrayList<>();
	    	listOfCustomers = customerDAO.getAllCustomersOrderedByName();
	    	
        	System.out.println("\n======================================");
        	System.out.println("| List of Customers Ordered by Name: |");
        	System.out.println("======================================");
	    	for(Customer customer : listOfCustomers) {
	    		System.out.println("ID: " + customer.getId() + " | Name: " + customer.getName() + 
	    				           " | City: " + customer.getCity() + " | State: " + customer.getState());
	    	}
	    	System.out.println("--------------------------------------");
	    } catch (SQLException e) {
	        System.err.println("Error querying report: " + e.getMessage());
	    }
	}
	
	
	public void queryOrdersOrderedByNumber(AbstractOrderDAO orderDAO) {
	    try {
			List<Orders> listOfOrders = new ArrayList<>();
	    	listOfOrders = orderDAO.getAllOrdersOrderedByNumber();
	    	
        	System.out.println("\n=====================================");
        	System.out.println("| List of Orders Ordered by Number: |");
        	System.out.println("=====================================");
	    	for(Orders order : listOfOrders) {
	    		System.out.println("Order Nº: " + order.getNumber() + " | Customer ID: " + order.getCustomerId() + 
	    				           " | Description: " + order.getDescription() + " | Price: US$  " + order.getPrice());
	    	}
	    } catch (SQLException e) {
	        System.err.println("Error querying report: " + e.getMessage());
	    }
	}
	
	/*public void queryCustomersOrdersByName(AbstractOrderDAO orderDAO, String customerName) {
	    try {

	    } catch (SQLException e) {
	        System.err.println("Error querying report: " + e.getMessage());
	    }
	}*/
	
	
	public void deleteOrderByNumber(AbstractOrderDAO orderDAO, int orderNumber) {
		try {
			orderDAO.deleteOrder(orderNumber);
		} catch (SQLException e) {
	        System.err.println("Error deleting customer: " + e.getMessage());
	    }
	}
}
