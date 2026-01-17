package io.github.stdonnelly.adventofcode.day09.model;

/// A vertical line
public final class HorizontalLine extends Line {
  private final long row;
  private final long left;
  private final long right;

  /// Construct a new instance
  ///
  /// If left is greater than right, the values will be swapped
  ///
  /// @param row The row that this line exists in
  /// @param left The left end of the line
  /// @param right The right end of the line
  public HorizontalLine(long row, long left, long right) {
    if (left >= right) {
      long temp = left;
      left = right;
      right = temp;
    }

    this.row = row;
    this.left = left;
    this.right = right;
  }

  public long getRow() {
    return row;
  }

  public long getLeft() {
    return left;
  }

  public long getRight() {
    return right;
  }

  @Override
  public boolean intersects(final Line other) {
    // other is a vertical line (this is a horizontal line)
    return other instanceof final VerticalLine verticalLine
        // other's column is within this left and right
        && this.getLeft() < verticalLine.getColumn()
        && verticalLine.getColumn() < this.getRight()
        // this row is within other's top and bottom
        && verticalLine.getTop() < this.getRow()
        && this.getRow() < verticalLine.getBottom();
  }
}
