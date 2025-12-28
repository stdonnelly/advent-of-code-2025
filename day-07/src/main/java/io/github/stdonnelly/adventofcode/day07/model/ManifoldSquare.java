package io.github.stdonnelly.adventofcode.day07.model;

/// One piece of the "tachyon manifold"
public enum ManifoldSquare {
  /// An empty square
  EMPTY('.'),
  /// The starting point
  START('S'),
  /// A splitter, which splits the beam to the left and right.
  SPLITTER('^'),
  /// The beam
  BEAM('|');

  private final char mapChar;

  private ManifoldSquare(final char mapChar) {
    this.mapChar = mapChar;
  }

  /// Parse the given character on the manifold map as a manifold square
  ///
  /// @param mapChar The character on the manifold map. Allowed values (and their outputs) are:
  /// - '.' -> [EMPTY][ManifoldSquare#EMPTY]
  /// - 'S' -> [START][ManifoldSquare#START]
  /// - '^' -> [SPLITTER][ManifoldSquare#SPLITTER]
  /// - '|' -> [BEAM][ManifoldSquare#BEAM]
  ///
  /// @return The corresponding `ManifoldSquare`
  /// @throws IllegalArgumentException If the input is not one of the above
  /// allowed values
  public static ManifoldSquare parse(char mapChar) {
    return switch (mapChar) {
      case '.' -> EMPTY;
      case 'S' -> START;
      case '^' -> SPLITTER;
      case '|' -> BEAM;
      default ->
          throw new IllegalArgumentException("Unable to parse '" + mapChar + "as a FloorSquare");
    };
  }

  /// Get the corresponding character on the map
  public char getMapChar() {
    return mapChar;
  }

  @Override
  public String toString() {
    return String.valueOf(mapChar);
  }
}
