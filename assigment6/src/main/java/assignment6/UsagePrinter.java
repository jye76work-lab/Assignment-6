package assignment6;

/**
 * Provides the usage/help message for the program.
 */
public final class UsagePrinter {

  private UsagePrinter() {
  }

  /**
   * Returns the usage/help message.
   *
   * @return usage message
   */
  public static String getUsageMessage() {
    return """
                Usage:
                --email
                    Generate email messages. If this option is provided,
                    then --email-template must also be provided.

                --email-template <path/to/file>
                    A filename for the email template.

                --letter
                    Generate letters. If this option is provided,
                    then --letter-template must also be provided.

                --letter-template <path/to/file>
                    A filename for the letter template.

                --output-dir <path/to/folder>
                    The folder to store all generated files. This option is required.

                --csv-file <path/to/file>
                    The CSV file to process. This option is required.

                Optional:
                --help
                    Print this help message.

                Examples:
                --email --email-template email-template.txt --output-dir emails --csv-file customer.csv

                --letter --letter-template letter-template.txt --output-dir letters --csv-file customer.csv

                --email --email-template email-template.txt --letter --letter-template letter-template.txt --output-dir output --csv-file customer.csv
                """;
  }
}