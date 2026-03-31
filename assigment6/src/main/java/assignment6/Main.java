package assignment6;

import java.io.IOException;
import java.util.List;

/**
 * Entry point for the insurance company communication automation program.
 */
public class Main {

  /**
   * Runs the program using command line arguments.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    try {
      ArgumentParser parser = new ArgumentParser(args);
      parser.validate();

      String csvFile = parser.getCsvFile();
      String outputDir = parser.getOutputDir();

      List<Customer> customers = CSVReader.read(csvFile);

      if (parser.shouldGenerateEmail()) {
        String emailTemplate = TemplateReader.readTemplate(parser.getEmailTemplate());
        FileGenerator.generateFiles(customers, emailTemplate, outputDir, "email");
      }

      if (parser.shouldGenerateLetter()) {
        String letterTemplate = TemplateReader.readTemplate(parser.getLetterTemplate());
        FileGenerator.generateFiles(customers, letterTemplate, outputDir, "letter");
      }

      System.out.println("All files generated successfully!");

    } catch (IllegalArgumentException e) {
      System.err.println("Error: " + e.getMessage());
      System.err.println();
      System.err.println(UsagePrinter.getUsageMessage());
      System.exit(1);
    } catch (IOException e) {
      System.err.println("File error: " + e.getMessage());
      System.exit(1);
    }
  }
}