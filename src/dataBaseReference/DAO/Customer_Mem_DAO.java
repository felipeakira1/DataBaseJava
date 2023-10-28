package dataBaseReference.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import dataBaseReference.DTO.Customer;
import dataBaseReference.RDBMS.MemoryDBConnection;


public class Customer_Mem_DAO extends AbstractCustomerDAO
   {
   private MemoryDBConnection databaseRef;

   public Customer_Mem_DAO(MemoryDBConnection databaseRef)
      {
      super();
      this.databaseRef = databaseRef;
      }

   @Override
   public List<Customer> getAllCustomersOrderedByName() throws SQLException
      {
      List<Customer> customers = new ArrayList<>();
      customers = databaseRef.getCustomerList();
      Collections.sort(customers);
      customers.addAll(customers);
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
   public void updateCustomer(Customer customer) throws SQLException
      {
      ArrayList<Customer> customers = databaseRef.getCustomerList();

      for (int index = 0; index < customers.size(); index++)
         {
         if (customers.get(index).getId() == customer.getId())
            {
            customers.set(index, customer);
            break;
            }
         }
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
      databaseRef.getCustomerList().clear();
      }
   }
