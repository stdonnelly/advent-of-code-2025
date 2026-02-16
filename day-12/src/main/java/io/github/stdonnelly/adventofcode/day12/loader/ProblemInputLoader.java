package io.github.stdonnelly.adventofcode.day12.loader;

import io.github.stdonnelly.adventofcode.day12.model.ProblemInput;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

public class ProblemInputLoader {
  protected final String inFileName;

  /// Create an input file loader to load from the specified file
  ///
  /// @param inFileName The file to load from
  public ProblemInputLoader(final String inFileName) {
    this.inFileName = inFileName;
  }

  /// Takes the input from the resource named in the constructor and parse each line as a
  /// [ProblemInput]
  ///
  /// @return The parsed input
  /// @throws IOException if there is a problem loading the input.txt file, or if there is a problem
  ///     reading the file
  /// @throws ParseException If there is a problem parsing the input
  public ProblemInput load() throws IOException, ParseException {
    try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(inFileName);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr)) {
      if (is == null) {
        throw new FileNotFoundException(inFileName + " not found");
      }

      return load(reader);
    }
  }

  private ProblemInput load(Reader reader) throws IOException, ParseException {
    // TODO
    // Parse each present shape
    // Parse each region
    return null;
  }
}
