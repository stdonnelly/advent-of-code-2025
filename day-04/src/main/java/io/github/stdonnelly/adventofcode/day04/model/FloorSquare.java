package io.github.stdonnelly.adventofcode.day04.model;

/// A square on the [FloorMap]
public enum FloorSquare {
  /// A floor square that is empty
  ///
  /// Marked by a "." on the map
  EMPTY('.'),

  /// A floor square that contains a roll of paper
  PAPER('@'),

  /// A floor square that used to contain a roll of paper, but is now empty
  REMOVED('x');

  /// The character that describes this space on the map
  private final char mapChar;

  private FloorSquare(char mapChar) {
    this.mapChar = mapChar;
  }

  /// Parse the given character on the floor map as a floor square
  ///
  /// @param mapChar The character on the floor map. Allowed values (and their
  /// outputs) are:
  /// - '.' -> [EMPTY][FloorSquare#EMPTY]
  /// - '@' -> [PAPER][FloorSquare#PAPER]
  /// - 'x' -> [REMOVED][FloorSquare#REMOVED]
  ///
  /// @return The corresponding `FloorSquare`
  /// @throws IllegalArgumentException If the input is not one of the above
  /// allowed values
  public static FloorSquare parse(char mapChar) {
    return switch (mapChar) {
      case '.' -> EMPTY;
      case '@' -> PAPER;
      case 'x' -> REMOVED;
      default ->
          throw new IllegalArgumentException("Unable to parse '" + mapChar + "as a FloorSquare");
    };
  }

  /// Get the corresponding character on the floor map
  public char getMapChar() {
    return mapChar;
  }
}
