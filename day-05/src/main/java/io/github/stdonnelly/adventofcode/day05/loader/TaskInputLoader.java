package io.github.stdonnelly.adventofcode.day05.loader;

import io.github.stdonnelly.adventofcode.common.model.InclusiveRange;
import io.github.stdonnelly.adventofcode.day05.model.TaskInput;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/// Loads the input file
public class TaskInputLoader {
  protected final String inFileName;

  /// Create an input file loader to load from the specified file
  ///
  /// @param inFileName The file to load from
  public TaskInputLoader(final String inFileName) {
    this.inFileName = inFileName;
  }

  /// Takes the input from the resource named in the constructor and parse each line as [TaskInput]
  ///
  /// @return The task input
  /// @throws IOException if there is a problem loading the input.txt file, or if there is a problem
  ///     reading the file
  public TaskInput load() throws IOException {
    try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(inFileName);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr)) {
      if (is == null) {
        throw new FileNotFoundException(inFileName + " not found");
      }

      final List<InclusiveRange> freshIngredientRanges = new ArrayList<>();
      final List<Long> availableIngredients = new ArrayList<>();

      // Parse the file using loops instead of stream because of how the file is laid out
      String line = reader.readLine();

      // Parse the inclusive ranges first
      // This loop ends when the line is the empty string, because that indicates the type is
      // changing
      while (line != null && !"".equals(line)) {
        freshIngredientRanges.add(InclusiveRange.parse(line));
        line = reader.readLine();
      }

      // Read one more line because there is an empty line separating the sections
      line = reader.readLine();

      // Parse the section containing the list of ingredients
      while (line != null && !"".equals(line)) {
        availableIngredients.add(Long.valueOf(line));
        line = reader.readLine();
      }

      return new TaskInput(freshIngredientRanges, availableIngredients);
    }
  }
}
