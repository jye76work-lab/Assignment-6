package assignment6;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

  @Test
  public void testGetValue() {
    Map<String, String> data = new HashMap<>();
    data.put("first_name", "James");
    data.put("last_name", "Butt");
    data.put("email", "jbutt@gmail.com");

    Customer customer = new Customer(data);

    assertEquals("James", customer.getValue("first_name"));
    assertEquals("Butt", customer.getValue("last_name"));
    assertEquals("jbutt@gmail.com", customer.getValue("email"));
  }

  @Test
  public void testGetValueReturnsNullWhenKeyNotFound() {
    Map<String, String> data = new HashMap<>();
    data.put("first_name", "James");

    Customer customer = new Customer(data);

    assertNull(customer.getValue("phone"));
  }

  @Test
  public void testGetDataReturnsCopy() {
    Map<String, String> data = new HashMap<>();
    data.put("first_name", "James");

    Customer customer = new Customer(data);

    Map<String, String> copy = customer.getData();
    copy.put("first_name", "Changed");

    assertEquals("James", customer.getValue("first_name"));
  }
}