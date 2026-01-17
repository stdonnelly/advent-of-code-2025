package io.github.stdonnelly.adventofcode.day09.model;

import java.util.List;

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

  /// Get a [List] of lines for the given [List] of [Point2d]s
  ///
  /// The list will contain a line between each adjacent point in the list, as well as a line from
  /// either end.
  public static List<Line> ofPoints(List<Point2d> points) {
    // Size is known, so just start with an array
    Line[] lines = new Line[points.size()];
    // All adjacent points
    for (int i = 0; i < points.size() - 1; i++) {
      lines[i] = Line.ofPoints(points.get(i), points.get(i + 1));
    }
    // Line from last to first
    lines[points.size() - 1] = Line.ofPoints(points.getLast(), points.getFirst());
    return List.of(lines);
  }

  /// Check if this line intersects with the other line
  ///
  /// This operation is *symmetric*: `x.intersects(y)` is equivalent to `y.intersects(x)`.
  ///
  /// It doesn't count as an intersection when then end of a line falls on this line or vice-versa.
  /// It is never true that a line can intersect another line in the same direction.
  ///
  /// - [HorizontalLine] never intersects another [HorizontalLine]
  /// - [VerticalLine] never intersects another [VerticalLine]
  public abstract boolean intersects(final Line other);

  /// Checks if any [Line] in the given list intersects with this one
  ///
  /// @see [intersects(Line)][#intersects(Line)]
  public boolean intersectsAny(final List<Line> other) {
    return other.stream().anyMatch(this::intersects);
  }

  /// Checks if any [Line] in the given list intersects with this one
  ///
  /// @see [intersects(Line)][#intersects(Line)]
  public boolean intersectsAny(final Line... other) {
    return intersectsAny(List.of(other));
  }
}
