package dataBaseReference.RDBMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class MariaDBConnection
   {
   private String JDBC_URL;
   private String USERNAME;
   private String PASSWORD;
   private Connection          connection = null;
   
   public Connection getConnection()
      {
      return (connection);
      }

   public MariaDBConnection(String serverAdress, int port, String databaseName, String username, String password)
      {
      super();
      
      JDBC_URL = "jdbc:mariadb://" + serverAdress + ":" + port + "/" + databaseName;
      USERNAME = username;
      PASSWORD = password;
      
      try
         {
         connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
         if (connection != null)
            {
            System.out.println("Database connection successful!");
            // You can use the 'connection' object for your database operations.
            }
         else
            {
            System.out.println("Failed to connect to the database.");
            }
         }
      catch (SQLException e)
         {
         System.err.println("Database connection error: " + e.getMessage());
         }
      }

   public void close()
      {
      try
         {
         if (connection != null)
            {
            connection.close();
            }
         }
      catch (SQLException e)
         {
         System.err.println("Error closing database connection: " + e.getMessage());
         }
      }
   }
