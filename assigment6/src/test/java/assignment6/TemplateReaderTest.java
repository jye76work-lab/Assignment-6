package assignment6;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

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