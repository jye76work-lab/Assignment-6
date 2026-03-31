package assignment6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileGenerator {

    public static void generateFiles(List<Customer> customers,
                                     String template,
                                     String outputDir,
                                     String prefix) throws IOException {

        File folder = new File(outputDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        int fileIndex = 1;

        for (Customer c : customers) {
            String content = TemplateProcessor.process(template, c);

            String path = outputDir + File.separator + prefix + fileIndex + ".txt";

            try (FileWriter fw = new FileWriter(path)) {
                fw.write(content);
            }

            fileIndex++;
        }
    }
}