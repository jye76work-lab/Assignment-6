package assignment6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reads customer records from a CSV file.
 * Supports fields enclosed in double quotes and commas inside quoted fields.
 */
public class CSVReader {

  /**
   * Parses one line of CSV text into a list of field values.
   * Commas inside double quotes are treated as part of a field.
   *
   * @param line one line from a CSV file
   * @return a list of parsed field values
   */
  static List<String> parseCSVLine(String line) {
    List<String> fields = new ArrayList<>();
    StringBuilder current = new StringBuilder();
    boolean inQuotes = false;

    for (int i = 0; i < line.length(); i++) {
      char ch = line.charAt(i);

      if (ch == '"') {
        inQuotes = !inQuotes;
      } else if (ch == ',' && !inQuotes) {
        fields.add(current.toString().trim());
        current.setLength(0);
      } else {
        current.append(ch);
      }
    }

    fields.add(current.toString().trim());
    return fields;
  }

  /**
   * Reads a CSV file and converts each row into a Customer object.
   * The first line of the file is treated as the header row.
   *
   * @param filePath the path to the CSV file
   * @return a list of Customer objects read from the file
   * @throws IOException if the file cannot be opened or read
   */
  public static List<Customer> read(String filePath) throws IOException {
    List<Customer> customers = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String headerLine = reader.readLine();

      if (headerLine == null || headerLine.isEmpty()) {
        return customers;
      }

      List<String> headers = parseCSVLine(headerLine);

      String line;
      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty()) {
          continue;
        }

        List<String> values = parseCSVLine(line);
        Map<String, String> customerData = new HashMap<>();

        for (int i = 0; i < headers.size() && i < values.size(); i++) {
          customerData.put(headers.get(i), values.get(i));
        }

        customers.add(new Customer(customerData));
      }
    }

    return customers;
  }
}