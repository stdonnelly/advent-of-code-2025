package io.github.stdonnelly.adventofcode.day07.model;

/// One piece of the "tachyon manifold"
public sealed class ManifoldSquare {
  private ManifoldSquare() {}

  /// Get the number of beams, if any, that occupy this square
  public long getBeamCount() {
    return 0L;
  }

  /// Parse the given character on the manifold map as a manifold square
  ///
  /// @param mapChar The character on the manifold map. Allowed values (and their outputs) are:
  /// - '.' -> [Empty]
  /// - 'S' -> [Start]
  /// - '^' -> [Splitter]
  /// - '|' -> [Beam]
  ///
  /// @return The corresponding `ManifoldSquare`
  /// @throws IllegalArgumentException If the input is not one of the above
  /// allowed values
  public static ManifoldSquare parse(char mapChar) {
    return switch (mapChar) {
      case '.' -> new Empty();
      case 'S' -> new Start();
      case '^' -> new Splitter();
      case '|' -> new Beam();
      default ->
          throw new IllegalArgumentException("Unable to parse '" + mapChar + "as a FloorSquare");
    };
  }

  /// An empty square
  public static final class Empty extends ManifoldSquare {
    @Override
    public String toString() {
      return ".";
    }
  }

  /// The starting point
  public static final class Start extends ManifoldSquare {
    @Override
    public long getBeamCount() {
      return 1L;
    }

    @Override
    public String toString() {
      return "S";
    }
  }

  /// A splitter, which splits the beam to the left and right.
  public static final class Splitter extends ManifoldSquare {
    @Override
    public String toString() {
      return "^";
    }
  }

  /// The beam
  public static final class Beam extends ManifoldSquare {
    private long beamCount;

    /// Add `additionalBeams` to the current `beamCount`
    public void addBeams(long additionalBeams) {
      beamCount += additionalBeams;
    }

    @Override
    public long getBeamCount() {
      return beamCount;
    }

    public void setBeamCount(long beamCount) {
      this.beamCount = beamCount;
    }

    @Override
    public String toString() {
      return "|";
    }
  }
}
