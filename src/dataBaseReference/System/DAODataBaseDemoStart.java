package dataBaseReference.System;

public final class DAODataBaseDemoStart
   {
   public static void main(String[] args)
      {
      System.out.println("Database demonstration running now...");
      
      try
         {
         (new Controller(DataBaseType.MEMORY)).start();
         }
      catch (Exception myException)
         {
         System.out.println("Exception launched. Programa aborted.");
         System.out.println(myException.getMessage());
         myException.printStackTrace();
         System.exit(1);
         }

      System.out.println("Database demonstration stopping now...");
      System.exit(0);
      }
   }
