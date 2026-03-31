package assignment6;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.util.*;
import assignment6.Customer;
import assignment6.FileGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class FileGeneratorTest {

    @Test
    public void testFileGeneration() throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("first_name", "Alice");

        Customer c = new Customer(data);
        List<Customer> list = new ArrayList<>();
        list.add(c);

        String template = "Hi [[first_name]]";

        String outputDir = "test_output";

        FileGenerator.generateFiles(list, template, outputDir, "email");

        File file = new File(outputDir + File.separator + "email1.txt");

        assertTrue(file.exists());

        String content = Files.readString(file.toPath());
        assertTrue(content.contains("Hi Alice"));

        file.delete();
        new File(outputDir).delete();
    }
}