package io.github.stdonnelly.adventofcode.day12.loader;

import io.github.stdonnelly.adventofcode.day12.model.PresentShape;
import io.github.stdonnelly.adventofcode.day12.model.ProblemInput;
import io.github.stdonnelly.adventofcode.day12.model.Region;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(is, StandardCharsets.UTF_8)) {
      if (is == null) {
        throw new FileNotFoundException(inFileName + " not found");
      }

      return load(scanner);
    }
  }

  private ProblemInput load(Scanner scanner) throws IOException, ParseException {
    scanner.useDelimiter("\n\n");

    // If there is nothing in the input, return an empty ProblemInput
    if (!scanner.hasNext()) {
      return new ProblemInput(List.of(), List.of());
    }

    List<PresentShape> presentShapes = new ArrayList<>();
    List<Region> regions = new ArrayList<>();

    // Parse each present shape
    // Getting the token before checking if the scanner has next is intentional, since the last
    // token is the regions section
    String token = scanner.next();
    while (scanner.hasNext()) {
      // Discard the first line because it is just an index.
      // This parser just assumes the present shapes are in order.
      token = token.substring(token.indexOf('\n') + 1);
      presentShapes.add(PresentShape.parse(token));

      token = scanner.next();
    }

    for (String line : token.split("\n")) {
      regions.add(Region.parse(line));
    }

    return new ProblemInput(List.copyOf(presentShapes), List.copyOf(regions));
  }
}
