package io.github.stdonnelly.adventofcode.day05.loader;

import io.github.stdonnelly.adventofcode.day05.model.FreshRange;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/** Loads the input file */
public class InputLoader {
  private final String inFileName;

  /**
   * Create an input file loader to load from the specified file
   *
   * @param inFileName The file to load from
   */
  public InputLoader(final String inFileName) {
    this.inFileName = inFileName;
  }

  /**
   * Takes the input from the resource named in {@link inFileName} and parses it into a list of
   * {@link FreshRange}
   *
   * @return A List of FreshRange from the file
   * @throws IOException if there is a problem loading the input.txt file, or if there is a problem
   *     reading the file
   */
  public List<FreshRange> load() throws IOException {
    try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(inFileName);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr)) {
      if (is == null) {
        throw new FileNotFoundException(inFileName + " not found");
      }

      return reader.lines().map(FreshRange::parse).toList();
    }
  }
}
