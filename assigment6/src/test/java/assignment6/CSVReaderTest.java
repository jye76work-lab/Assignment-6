package assignment6;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest {

  @Test
  public void testParseCSVLineSimple() {
    String line = "\"James\",\"Butt\",\"jbutt@gmail.com\"";

    List<String> result = CSVReader.parseCSVLine(line);

    assertEquals(3, result.size());
    assertEquals("James", result.get(0));
    assertEquals("Butt", result.get(1));
    assertEquals("jbutt@gmail.com", result.get(2));
  }

  @Test
  public void testParseCSVLineWithCommaInsideQuotes() {
    String line = "\"Art\",\"Venere\",\"Chemel, James L Cpa\"";

    List<String> result = CSVReader.parseCSVLine(line);

    assertEquals(3, result.size());
    assertEquals("Art", result.get(0));
    assertEquals("Venere", result.get(1));
    assertEquals("Chemel, James L Cpa", result.get(2));
  }

  @Test
  public void testReadCSVFile() throws IOException {
    File tempFile = File.createTempFile("test-customers", ".csv");
    tempFile.deleteOnExit();

    try (FileWriter writer = new FileWriter(tempFile)) {
      writer.write("\"first_name\",\"last_name\",\"email\"\n");
      writer.write("\"James\",\"Butt\",\"jbutt@gmail.com\"\n");
      writer.write("\"Art\",\"Venere\",\"art@venere.org\"\n");
    }

    List<Customer> customers = CSVReader.read(tempFile.getAbsolutePath());

    assertEquals(2, customers.size());
    assertEquals("James", customers.get(0).getValue("first_name"));
    assertEquals("Butt", customers.get(0).getValue("last_name"));
    assertEquals("jbutt@gmail.com", customers.get(0).getValue("email"));

    assertEquals("Art", customers.get(1).getValue("first_name"));
    assertEquals("Venere", customers.get(1).getValue("last_name"));
    assertEquals("art@venere.org", customers.get(1).getValue("email"));
  }

  @Test
  public void testReadEmptyCSVFile() throws IOException {
    File tempFile = File.createTempFile("empty-customers", ".csv");
    tempFile.deleteOnExit();

    try (FileWriter writer = new FileWriter(tempFile)) {
      writer.write("");
    }

    List<Customer> customers = CSVReader.read(tempFile.getAbsolutePath());

    assertTrue(customers.isEmpty());
  }
}
