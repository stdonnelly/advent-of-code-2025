package io.github.stdonnelly.adventofcode.day07.model;

import java.util.List;

/// A row on the tachyon manifold
public record ManifoldRow(List<ManifoldSquare> manifoldSquareList) {
  /// Parse an input object
  ///
  /// @param input The input string to parse
  /// @return The input after parsing
  /// @throws IllegalArgumentException If the input is not parsable
  public static ManifoldRow parse(String input) throws IllegalArgumentException {
    final List<ManifoldSquare> manifoldSquareList =
        input.codePoints().mapToObj(c -> ManifoldSquare.parse((char) c)).toList();

    return new ManifoldRow(manifoldSquareList);
  }
}
