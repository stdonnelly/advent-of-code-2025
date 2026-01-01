package io.github.stdonnelly.adventofcode.day08.model;

/// A point in 3D space
public record Point3d(double x, double y, double z) implements Comparable<Point3d> {
  /// Parse an input object
  ///
  /// @param input The input string to parse
  /// @return The input after parsing
  /// @throws IllegalArgumentException If the input is not parsable
  public static Point3d parse(String input) throws IllegalArgumentException {
    // Split on the comma and assert that there must be exactly 3 coordinates
    final String[] splitInput = input.split(",");
    if (splitInput.length != 3) {
      throw new IllegalArgumentException(
          "Unexpected number of coordinates. Expected 3, but got " + splitInput.length);
    }

    return new Point3d(
        Double.parseDouble(splitInput[0]),
        Double.parseDouble(splitInput[1]),
        Double.parseDouble(splitInput[2]));
  }

  /// Get the magnitude of the vector from the origin to this point using the Pythagorean theorem
  public double getMagnitude() {
    return Math.sqrt((x * x) + (y * y) + (z * z));
  }

  /// Gets the difference between this point and the other point
  ///
  /// @return Point3d(other.x-this.x, other.y-this.y, other.z-this.z)
  public Point3d differenceTo(Point3d other) {
    return new Point3d(other.x() - x, other.y() - y, other.z() - z);
  }

  /// Gets the magnitude of the distance between this point and the other point using the
  /// Pythagorean theorem
  public double distanceTo(Point3d other) {
    return differenceTo(other).getMagnitude();
  }

  @Override
  public String toString() {
    return String.format("%.0f,%.0f,%.0f", x, y, z);
  }

  /// Compare by magnitude
  ///
  /// {@inheritDoc}
  @Override
  public int compareTo(Point3d o) {
    return Double.compare(getMagnitude(), o.getMagnitude());
  }
}
