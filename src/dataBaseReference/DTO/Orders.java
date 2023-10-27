package dataBaseReference.DTO;

public class Orders
   {
   private int        number;
   private int        customerId;
   private String     description;
   private float price;

   public Orders()
      {
      super();
      }

   public Orders(int number, int customerId, String description, float price)
      {
      super();
      this.number      = number;
      this.customerId  = customerId;
      this.description = description;
      this.price       = price;
      }

   public final int getNumber()
      {
      return number;
      }

   public final int getCustomerId()
      {
      return customerId;
      }

   public final String getDescription()
      {
      return description;
      }

   public final float getPrice()
      {
      return price;
      }

   public final void setNumber(int number)
      {
      this.number = number;
      }

   public final void setCustomerId(int customerId)
      {
      this.customerId = customerId;
      }

   public final void setDescription(String description)
      {
      this.description = description;
      }

   public final void setPrice(float price)
      {
      this.price = price;
      }

   @Override
   public String toString()
      {
      return "Order [number=" + number + ", customerId=" + customerId + ", description=" + description + "]";
      }

   }
