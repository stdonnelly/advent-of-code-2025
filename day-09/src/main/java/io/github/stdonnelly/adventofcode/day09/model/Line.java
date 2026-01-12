package io.github.stdonnelly.adventofcode.day09.model;

public abstract sealed class Line permits HorizontalLine, VerticalLine {
  /// Get a new line with the ends at the given points
  ///
  /// Diagonal lines are not supported
  ///
  /// @param from The starting point
  /// @param to The ending point
  public static Line ofPoints(Point2d from, Point2d to) {
    if (from.x() == to.x()) {
        return new VerticalLine(from.x(), from.y(), to.y());
    } else if (from.y() == to.y()) {
        return new HorizontalLine(from.y(), from.x(), to.x());
    } else {
      throw new IllegalArgumentException("Diagonal lines are not supported");
    }
  }

  /// Check if this line intersects with the other line
  /// 
  /// This operation is *symmetric*: `x.intersects(y)` is equivalent to `y.intersects(x)`.
  /// 
  /// It is never true that a line can intersect another line in the same direction.
  /// 
  /// - [HorizontalLine] never intersects another [HorizontalLine]
  /// - [VerticalLine] never intersects another [VerticalLine]
  public abstract boolean intersects(final Line other);
}
