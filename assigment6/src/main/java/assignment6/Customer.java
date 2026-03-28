package assignment6;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents one customer record read from the CSV file.
 * Each customer stores column header names and their corresponding values.
 */
public class Customer {
  private final Map<String, String> data;

  /**
   * Constructs a Customer object using the given data map.
   *
   * @param data a map of CSV header names to field values
   */
  public Customer(Map<String, String> data) {
    this.data = new HashMap<>(data);
  }

  /**
   * Returns the value associated with the given header name.
   *
   * @param key the CSV header name
   * @return the corresponding value, or null if the key does not exist
   */
  public String getValue(String key) {
    return data.get(key);
  }

  /**
   * Returns a copy of the customer's data map.
   *
   * @return a copy of the customer data
   */
  public Map<String, String> getData() {
    return new HashMap<>(data);
  }
}