package io.github.stdonnelly.adventofcode.day07.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/// A row on the tachyon manifold
public record ManifoldRow(List<ManifoldSquare> manifoldSquareList) {
  /// Create a copy with interior mutability
  public ManifoldRow(ManifoldRow other) {
    this(new ArrayList<>(other.manifoldSquareList()));
  }

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

  /// Check if all of the manifold squares are blank. At least for part 1, blank lines can be
  /// ignored.
  ///
  /// @return `true` if all [ManifoldSquare]s in the row are [ManifoldSquare.Empty]
  public boolean isBlank() {
    return manifoldSquareList.stream().allMatch(o -> o instanceof ManifoldSquare.Empty);
  }

  @Override
  public final String toString() {
    return manifoldSquareList.stream().map(ManifoldSquare::toString).collect(Collectors.joining());
  }
}
