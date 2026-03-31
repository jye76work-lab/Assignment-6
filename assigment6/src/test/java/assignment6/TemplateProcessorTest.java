package assignment6;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateProcessorTest {

    @Test
    public void testBasicReplacement() {
        Map<String, String> data = new HashMap<>();
        data.put("first_name", "John");
        data.put("last_name", "Doe");

        Customer c = new Customer(data);

        String template = "Hello [[first_name]] [[last_name]]";
        String result = TemplateProcessor.process(template, c);

        assertEquals("Hello John Doe", result);
    }

    @Test
    public void testMissingField() {
        Map<String, String> data = new HashMap<>();
        data.put("first_name", "John");

        Customer c = new Customer(data);

        String template = "Hello [[first_name]] [[age]]";
        String result = TemplateProcessor.process(template, c);

        assertEquals("Hello John [[age]]", result);
    }
}