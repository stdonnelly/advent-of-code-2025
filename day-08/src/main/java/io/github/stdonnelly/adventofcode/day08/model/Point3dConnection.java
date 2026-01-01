package io.github.stdonnelly.adventofcode.day08.model;

/// Represents a connection between two points
///
/// @param index1 The index of the first point in `allPoints`. Must be in the range
// `[0,allPoints.size())`
/// @param index2 The index of the second point in `allPoints`. Must be in the range
// `[0,allPoints.size())`
/// @param allPoints The entire list of points
public record Point3dConnection(Point3d point1, Point3d point2)
    implements Comparable<Point3dConnection> {

  /// Get the distance between the two points in this connection
  ///
  /// @see Point3d#distanceTo(Point3d)
  public double getDistance() {
    return point1.distanceTo(point2);
  }

  @Override
  public int compareTo(Point3dConnection o) {
    return Double.compare(getDistance(), o.getDistance());
  }
}
