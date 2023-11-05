package dataBaseReference.System;

import java.sql.Connection;

import dataBaseReference.DAO.AbstractCustomerDAO;
import dataBaseReference.DAO.AbstractOrderDAO;
import dataBaseReference.DAO.Customer_DB_DAO;
import dataBaseReference.DAO.Customer_Mem_DAO;
import dataBaseReference.DAO.Order_DB_DAO;
import dataBaseReference.DAO.Order_Mem_DAO;
import dataBaseReference.RDBMS.MariaDBConnection;
import dataBaseReference.RDBMS.MemoryDBConnection;

public class Controller
   {
   private AbstractCustomerDAO customerDAO        = null;
   private AbstractOrderDAO    ordersDAO          = null;
   private MariaDBConnection   myDBConnection     = null;
   private MemoryDBConnection  memoryDBConnection = null;
   
   public Controller()
      {
      super();
      }
   
   private void openMariaDBConnection(String serverAdress, int port, String databaseName, String username, String password) {
	   myDBConnection = new MariaDBConnection(serverAdress, port, databaseName, username, password);
       this.customerDAO = new Customer_DB_DAO(myDBConnection.getConnection());
       this.ordersDAO = new Order_DB_DAO(myDBConnection.getConnection());
   }
   
   private void openMemoryConnection() {
	   memoryDBConnection = new MemoryDBConnection();
       this.customerDAO = new Customer_Mem_DAO(memoryDBConnection);
       this.ordersDAO = new Order_Mem_DAO(memoryDBConnection);
      }
   
   
   public Connection getMariaDBConnection() {
	   return myDBConnection.getConnection();
   }
   
   private void closeConnection()
      {
      if (myDBConnection != null)
         {
         myDBConnection.close();
         }
      if (memoryDBConnection != null)
         {
         memoryDBConnection.close();
         }
      }
   
   public void initializeConnection() {
	   openMemoryConnection();
   }
   
   public void initializeConnection(String serverAdress, int port, String databaseName, String username, String password)
   {
	   openMariaDBConnection(serverAdress, port, databaseName, username, password);
   }
   
   public void endConnection()
   {
	   closeConnection();
   }

	public AbstractCustomerDAO getCustomerDAO() {
		return customerDAO;
	}
	
	public AbstractOrderDAO getOrdersDAO() {
		return ordersDAO;
	}
}
