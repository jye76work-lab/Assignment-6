package assignment6;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateReaderTest {

    @Test
    public void testReadTemplate() throws Exception {
        String path = getClass()
                .getClassLoader()
                .getResource("email-template.txt")
                .getPath();

        String content = TemplateReader.readTemplate(path);

        assertNotNull(content);
        assertFalse(content.isEmpty());
    }
}