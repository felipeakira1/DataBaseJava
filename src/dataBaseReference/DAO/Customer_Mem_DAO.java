package dataBaseReference.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;

import dataBaseReference.DTO.Customer;
import dataBaseReference.RDBMS.MemoryDBConnection;


public class Customer_Mem_DAO extends AbstractCustomerDAO {
	private MemoryDBConnection databaseRef;
   
   public Customer_Mem_DAO(MemoryDBConnection databaseRef)
      {
      super();
      this.databaseRef = databaseRef;
      }

   @Override
   public List<Customer> getAllCustomersOrderedById() throws SQLException {
	   List<Customer> customers = databaseRef.getCustomerList();
	   Collections.sort(customers, new Comparator<Customer>() {
		   @Override
		   public int compare(Customer customer1, Customer customer2) {
			   return Integer.compare(customer1.getId(), customer2.getId());
		   }
	   });
	   return customers;
   }
   
   @Override
   public List<Customer> getAllCustomersOrderedByName() throws SQLException
      {
	   List<Customer> customers = databaseRef.getCustomerList();
	   Collections.sort(customers, new Comparator<Customer>() {
		   @Override
		   public int compare(Customer customer1, Customer customer2) {
			   return customer1.getName().compareTo(customer2.getName());
		   }
	   	});
	   return customers;
      }

   @Override
   public Customer getCustomerById(int customerId) throws SQLException
      {
      Customer customer = null;
      Iterator<Customer> iterator = databaseRef.getCustomerList().iterator();
      /*
      System.out.println("IDs in the customer list: ");
      for (Customer c : databaseRef.getCustomerList()) { //usar esse trecho se quiser testar o estado da lista a cada consulta
          System.out.println(c.getId());
      }*/
      while (iterator.hasNext())
         {
         Customer buffer = iterator.next();
         if (buffer.getId() == customerId)
            {
            customer = buffer;
            break;
            }
         }
      return customer;
      }
   
   @Override
   public List<Customer> getCustomersByName(String customerName) throws SQLException {
	   Iterator<Customer> iterator = databaseRef.getCustomerList().iterator();
	   List<Customer> customersByName = new ArrayList<>();
	   
	   while(iterator.hasNext()) {
		   Customer buffer = iterator.next();
		   if(buffer.getName().equals(customerName)) {
			   customersByName.add(buffer);
		   }
	   }
	   return customersByName;
   }
   
   @Override
   public void addCustomer(Customer customer) throws SQLException
      {
      databaseRef.getCustomerList().add(customer);
      }

   @Override
   public void deleteCustomer(int customerId) throws SQLException
      {
      ArrayList<Customer> customers = databaseRef.getCustomerList();

      for (int index = 0; index < customers.size(); index++)
         {
         if (customers.get(index).getId() == customerId)
            {
            customers.remove(index);
            break;
            }
         }
      // throw new CustomerNotFoundException
      }

   @Override
   public void deleteAllCustomers() throws SQLException
      {
	  databaseRef.getOrderList().clear();
      databaseRef.getCustomerList().clear();
      }
}
