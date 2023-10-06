package dataBaseReference.RDBMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class MariaDBConnection
   {
   private static final String JDBC_URL   = "jdbc:mariadb://143.106.243.64:3306/SI400";
   private static final String USERNAME   = "si400_2023";
   private static final String PASSWORD   = "si400_2023";
   private Connection          connection = null;

   public Connection getConnection()
      {
      return (connection);
      }

   public MariaDBConnection()
      {
      super();

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
