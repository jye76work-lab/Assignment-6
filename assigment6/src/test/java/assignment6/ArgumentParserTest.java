package assignment6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for ArgumentParser.
 */
public class ArgumentParserTest {

  @Test
  public void testValidEmailArguments() {
    String[] args = {
        "--email",
        "--email-template", "email-template.txt",
        "--output-dir", "emails",
        "--csv-file", "customers.csv"
    };

    ArgumentParser parser = new ArgumentParser(args);

    assertDoesNotThrow(parser::validate);
    assertTrue(parser.shouldGenerateEmail());
    assertFalse(parser.shouldGenerateLetter());
    assertEquals("email-template.txt", parser.getEmailTemplate());
    assertEquals("emails", parser.getOutputDir());
    assertEquals("customers.csv", parser.getCsvFile());
  }

  @Test
  public void testValidLetterArguments() {
    String[] args = {
        "--letter",
        "--letter-template", "letter-template.txt",
        "--output-dir", "letters",
        "--csv-file", "customers.csv"
    };

    ArgumentParser parser = new ArgumentParser(args);

    assertDoesNotThrow(parser::validate);
    assertFalse(parser.shouldGenerateEmail());
    assertTrue(parser.shouldGenerateLetter());
    assertEquals("letter-template.txt", parser.getLetterTemplate());
  }

  @Test
  public void testValidEmailAndLetterArguments() {
    String[] args = {
        "--email",
        "--email-template", "email-template.txt",
        "--letter",
        "--letter-template", "letter-template.txt",
        "--output-dir", "output",
        "--csv-file", "customers.csv"
    };

    ArgumentParser parser = new ArgumentParser(args);

    assertDoesNotThrow(parser::validate);
    assertTrue(parser.shouldGenerateEmail());
    assertTrue(parser.shouldGenerateLetter());
  }

  @Test
  public void testMissingOutputDirThrowsException() {
    String[] args = {
        "--email",
        "--email-template", "email-template.txt",
        "--csv-file", "customers.csv"
    };

    ArgumentParser parser = new ArgumentParser(args);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, parser::validate);

    assertEquals("--output-dir is required.", exception.getMessage());
  }

  @Test
  public void testMissingCsvFileThrowsException() {
    String[] args = {
        "--email",
        "--email-template", "email-template.txt",
        "--output-dir", "emails"
    };

    ArgumentParser parser = new ArgumentParser(args);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, parser::validate);

    assertEquals("--csv-file is required.", exception.getMessage());
  }

  @Test
  public void testEmailWithoutTemplateThrowsException() {
    String[] args = {
        "--email",
        "--output-dir", "emails",
        "--csv-file", "customers.csv"
    };

    ArgumentParser parser = new ArgumentParser(args);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, parser::validate);

    assertEquals("--email provided but no --email-template was given.",
        exception.getMessage());
  }

  @Test
  public void testLetterWithoutTemplateThrowsException() {
    String[] args = {
        "--letter",
        "--output-dir", "letters",
        "--csv-file", "customers.csv"
    };

    ArgumentParser parser = new ArgumentParser(args);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, parser::validate);

    assertEquals("--letter provided but no --letter-template was given.",
        exception.getMessage());
  }

  @Test
  public void testNoModeSpecifiedThrowsException() {
    String[] args = {
        "--output-dir", "output",
        "--csv-file", "customers.csv"
    };

    ArgumentParser parser = new ArgumentParser(args);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, parser::validate);

    assertEquals("You must specify at least one of --email or --letter.",
        exception.getMessage());
  }

  @Test
  public void testUnknownArgumentThrowsException() {
    String[] args = {"--unknown"};

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new ArgumentParser(args));

    assertEquals("Unknown argument: --unknown", exception.getMessage());
  }

  @Test
  public void testMissingValueAfterOptionThrowsException() {
    String[] args = {"--email-template"};

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new ArgumentParser(args));

    assertEquals("Missing value for --email-template.", exception.getMessage());
  }

  @Test
  public void testArgumentsCanAppearInAnyOrder() {
    String[] args = {
        "--csv-file", "customers.csv",
        "--output-dir", "output",
        "--letter-template", "letter-template.txt",
        "--letter"
    };

    ArgumentParser parser = new ArgumentParser(args);

    assertDoesNotThrow(parser::validate);
    assertTrue(parser.shouldGenerateLetter());
    assertEquals("customers.csv", parser.getCsvFile());
    assertEquals("output", parser.getOutputDir());
    assertEquals("letter-template.txt", parser.getLetterTemplate());
  }
}