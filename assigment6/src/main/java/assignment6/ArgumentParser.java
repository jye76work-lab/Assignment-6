package assignment6;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses and validates command line arguments for the program.
 */
public class ArgumentParser {

  private final Map<String, String> options;
  private boolean emailRequested;
  private boolean letterRequested;
  private boolean helpRequested;

  /**
   * Constructs an ArgumentParser and parses the given arguments.
   *
   * @param args command line arguments
   */
  public ArgumentParser(String[] args) {
    this.options = new HashMap<>();
    parse(args);
  }

  /**
   * Parses the command line arguments.
   *
   * @param args command line arguments
   */
  private void parse(String[] args) {
    if (args == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }

    for (int i = 0; i < args.length; i++) {
      String current = args[i];

      switch (current) {
        case "--email":
          emailRequested = true;
          break;

        case "--letter":
          letterRequested = true;
          break;

        case "--help":
          helpRequested = true;
          break;

        case "--email-template":
        case "--letter-template":
        case "--output-dir":
        case "--csv-file":
          i = handleOptionWithValue(args, i, current);
          break;

        default:
          throw new IllegalArgumentException("Unknown argument: " + current);
      }
    }
  }

  /**
   * Handles an option that requires a value immediately after it.
   *
   * @param args the full argument array
   * @param index current option index
   * @param option option name
   * @return updated index after consuming the value
   */
  private int handleOptionWithValue(String[] args, int index, String option) {
    if (index + 1 >= args.length) {
      throw new IllegalArgumentException("Missing value for " + option + ".");
    }

    String value = args[index + 1];

    if (value.startsWith("--")) {
      throw new IllegalArgumentException("Missing value for " + option + ".");
    }

    options.put(option, value);
    return index + 1;
  }

  /**
   * Validates the parsed arguments according to assignment rules.
   */
  public void validate() {
    if (helpRequested) {
      return;
    }

    if (!emailRequested && !letterRequested) {
      throw new IllegalArgumentException(
          "You must specify at least one of --email or --letter."
      );
    }

    if (!options.containsKey("--output-dir")) {
      throw new IllegalArgumentException("--output-dir is required.");
    }

    if (!options.containsKey("--csv-file")) {
      throw new IllegalArgumentException("--csv-file is required.");
    }

    if (emailRequested && !options.containsKey("--email-template")) {
      throw new IllegalArgumentException(
          "--email provided but no --email-template was given."
      );
    }

    if (letterRequested && !options.containsKey("--letter-template")) {
      throw new IllegalArgumentException(
          "--letter provided but no --letter-template was given."
      );
    }
  }

  public boolean shouldGenerateEmail() {
    return emailRequested;
  }

  public boolean shouldGenerateLetter() {
    return letterRequested;
  }

  public boolean hasHelpFlag() {
    return helpRequested;
  }

  public String getEmailTemplate() {
    return options.get("--email-template");
  }

  public String getLetterTemplate() {
    return options.get("--letter-template");
  }

  public String getOutputDir() {
    return options.get("--output-dir");
  }

  public String getCsvFile() {
    return options.get("--csv-file");
  }
}