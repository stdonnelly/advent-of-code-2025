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

  /// Gets the two horizontal edges of this rectangle
  ///
  /// @return `HorizontalLine[]{topLine, bottomLine}`
  public HorizontalLine[] getHorizontalEdges() {
    // Get the top and bottom row and make sure they are ordered
    long topRow = point1.y();
    long bottomRow = point2.y();
    if (topRow > bottomRow) {
      long temp = topRow;
      topRow = bottomRow;
      bottomRow = temp;
    }

    return new HorizontalLine[] {
      new HorizontalLine(topRow, point1.x(), point2.x()),
      new HorizontalLine(bottomRow, point1.x(), point2.x())
    };
  }

  /// Gets the two vertical edges of this rectangle
  ///
  /// @return `VerticalLine[]{leftLine, rightLine}`
  public VerticalLine[] getVerticalEdges() {
    // Get the top and bottom row and make sure they are ordered
    long leftColumn = point1.x();
    long rightColumn = point2.x();
    if (leftColumn > rightColumn) {
      long temp = leftColumn;
      leftColumn = rightColumn;
      rightColumn = temp;
    }

    return new VerticalLine[] {
      new VerticalLine(leftColumn, point1.y(), point2.y()),
      new VerticalLine(rightColumn, point1.y(), point2.y())
    };
  }

  /// Check if the perimeter if this [Rectangle] intersects with a [Line]
  public boolean intersects(final Line line) {
    return linesIntersect(line, getHorizontalEdges(), getVerticalEdges());
  }

  /// Check if the perimeter if this [Rectangle] intersects with a [Line]
  public boolean intersectsAny(final List<Line> lines) {
    final HorizontalLine[] horizontalLines = getHorizontalEdges();
    final VerticalLine[] verticalLines = getVerticalEdges();

    return lines.stream().anyMatch(line -> linesIntersect(line, horizontalLines, verticalLines));
  }

  /// Helper for [intersects(Line)][#intersects(Line)] and
  /// [intersectsAny(List<Line>)][#intersectsAny(List)]
  @SuppressWarnings("java:S6880") // Converting the if statement to a switch expression triggers
  // a compiler bug
  private boolean linesIntersect(
      final Line line, final HorizontalLine[] horizontalLines, final VerticalLine[] verticalLines) {
    // Check the line for intersections
    if (switch (line) {
      // Only check the necessary lines
      case VerticalLine vertical -> vertical.intersectsAny(horizontalLines);
      case HorizontalLine horizontal -> horizontal.intersectsAny(verticalLines);
      default -> throw new IllegalArgumentException("Unexpected line type");
    }) {
      return true;
    }

    /*
      The above statement handles most cases, but not the case where an end of the given line fall
      on an edge and extends into this rectangle without intersecting the opposite edge.

      Example:
      +-+-+
      | | |
      |   |
      +---+

      We need to account for this because of shapes such as the following:
      +-+
      | |
      | +-+
      |   |
      +---+
    */

    if (line instanceof VerticalLine verticalLine) {
      // If vertical line, check if the top contains the top point, or if the bottom contains the
      // bottom point
      return horizontalLines[0].contains(
              new Point2d(verticalLine.getColumn(), verticalLine.getTop()))
          || horizontalLines[1].contains(
              new Point2d(verticalLine.getColumn(), verticalLine.getBottom()));
    } else if (line instanceof HorizontalLine horizontalLine) {
      // If horizontal line, check if the left contains the left point, or if the right contains
      // the right point
      return verticalLines[0].contains(
              new Point2d(horizontalLine.getLeft(), horizontalLine.getRow()))
          || verticalLines[1].contains(
              new Point2d(horizontalLine.getRight(), horizontalLine.getRow()));
    } else {
      throw new IllegalArgumentException("Unexpected line type");
    }
  }
}
