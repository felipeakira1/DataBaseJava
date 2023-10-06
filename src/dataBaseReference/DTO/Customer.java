package dataBaseReference.DTO;

public class Customer implements Comparable<Customer>
   {
   private int    id;
   private String name;
   private String city;
   private String state;

   public Customer()
      {
      super();
      }

   public Customer(int id, String name, String city, String state)
      {
      super();
      this.id    = id;
      this.name  = name;
      this.city  = city;
      this.state = state;
      }

   public final int getId()
      {
      return id;
      }

   public final String getName()
      {
      return name;
      }

   public final String getCity()
      {
      return city;
      }

   public final String getState()
      {
      return state;
      }

   public final void setId(int id)
      {
      this.id = id;
      }

   public final void setName(String name)
      {
      this.name = name;
      }

   public final void setCity(String city)
      {
      this.city = city;
      }

   public final void setState(String state)
      {
      this.state = state;
      }

   @Override
   public String toString()
      {
      return "Customer [id=" + id + ", name=" + name + ", city=" + city + ", state=" + state + "]";
      }

   @Override
   public int compareTo(Customer other)
      {
      return (this.name.compareTo(other.name));
      }

   }
