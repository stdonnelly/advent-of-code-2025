package io.github.stdonnelly.adventofcode.day09.model;

/// A rectangle defined by opposite corners
///
/// The bounds of the rectangle include points, so the length of a side is actually the difference
/// between coordinates on the points +1
///
/// @param point1 A corner of the rectangle opposite of `point2`
/// @param point2 A corner of the rectangle opposite of `point1`
public record Rectangle(Point2d point1, Point2d point2) implements Comparable<Rectangle> {
  /// Get the area of this rectangle
  public long getArea() {
    final long width = Math.abs(point2.x() - point1.x()) + 1;
    final long height = Math.abs(point2.y() - point1.y()) + 1;

    return width * height;
  }

  /// Compare based on area
  ///
  /// {@inheritDoc}
  @Override
  public int compareTo(Rectangle o) {
    return Long.compare(getArea(), o.getArea());
  }
}
