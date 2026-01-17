package io.github.stdonnelly.adventofcode.day09.model;

/// A point in 2D space
/// @param x The column
/// @param y The row
public record Point2d(long x, long y) {
  /// Parse an input object
  ///
  /// @param input The input string to parse
  /// @return The input after parsing
  /// @throws IllegalArgumentException If the input is not parsable
  public static Point2d parse(String input) throws IllegalArgumentException {
    // Split on the comma and assert that there must be exactly 2 coordinates
    final String[] splitInput = input.split(",");
    if (splitInput.length != 2) {
      throw new IllegalArgumentException(
          "Unexpected number of coordinates. Expected 2, but got " + splitInput.length);
    }

    return new Point2d(Long.parseLong(splitInput[0]), Long.parseLong(splitInput[1]));
  }
}
