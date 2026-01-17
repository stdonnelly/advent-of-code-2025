package io.github.stdonnelly.adventofcode.day09.model;

import java.util.List;

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

  /// Check if the perimeter if this [Rectangle] intersects with a [Line]
  public boolean intersects(final Line line) {
    return linesIntersect(
        line,
        new HorizontalLine[] {
          new HorizontalLine(point1.y(), point1.x(), point2.x()),
          new HorizontalLine(point2.y(), point1.x(), point2.x())
        },
        new VerticalLine[] {
          new VerticalLine(point1.x(), point1.y(), point2.y()),
          new VerticalLine(point2.x(), point1.y(), point2.y())
        });
  }

  /// Check if the perimeter if this [Rectangle] intersects with a [Line]
  public boolean intersectsAny(final List<Line> lines) {
    final HorizontalLine[] horizontalLines =
        new HorizontalLine[] {
          new HorizontalLine(point1.y(), point1.x(), point2.x()),
          new HorizontalLine(point2.y(), point1.x(), point2.x())
        };
    final VerticalLine[] verticalLines =
        new VerticalLine[] {
          new VerticalLine(point1.x(), point1.y(), point2.y()),
          new VerticalLine(point2.x(), point1.y(), point2.y())
        };

    return lines.stream().anyMatch(line -> linesIntersect(line, horizontalLines, verticalLines));
  }

  /// Helper for [intersects(Line)][#intersects(Line)] and
  /// [intersectsAny(List<Line>)][#intersectsAny(List)]
  private boolean linesIntersect(
      final Line line, final HorizontalLine[] horizontalLines, final VerticalLine[] verticalLines) {
    // Only check the necessary lines
    return switch (line) {
      case VerticalLine vertical -> vertical.intersectsAny(horizontalLines);
      case HorizontalLine horizontal -> horizontal.intersectsAny(verticalLines);
      default -> throw new IllegalArgumentException("Unexpected line type");
    };
  }
}
