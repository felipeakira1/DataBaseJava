package dataBaseReference.DAO;

import java.sql.SQLException;
import java.util.List;

import dataBaseReference.DTO.Orders;

public abstract class AbstractOrderDAO {
	
	abstract public void addOrder(Orders order) throws SQLException;

	abstract public void deleteOrder(int orderNumber) throws SQLException;

	abstract public void deleteAllOrders() throws SQLException;
   
	abstract public void deleteAllOrdersFromCustomer(int customerId) throws SQLException;

	abstract public Orders getOrderByNumber(int orderNumber) throws SQLException;

	abstract public List<Orders> getAllOrdersByCustomerId(int customerId) throws SQLException;
	
	abstract public List<Orders> getAllOrdersByCustomerIdOrderedByNumber(int customerId) throws SQLException;
	
	abstract public List<Orders> getAllOrdersOrderedByNumber() throws SQLException;	
	
}