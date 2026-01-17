package io.github.stdonnelly.adventofcode.day09.model;

/// A vertical line
public final class VerticalLine extends Line {
  private final long column;
  private final long top;
  private final long bottom;

  /// Construct a new instance
  ///
  /// If `top` is greater than `bottom`, they will be swapped
  ///
  /// @param column The column that this line exists in
  /// @param top The top of the line. Must be less than `bottom`
  /// @param bottom The bottom of the line. Must be greater than `top`
  public VerticalLine(long column, long top, long bottom) {
    if (top >= bottom) {
      long temp = top;
      top = bottom;
      bottom = temp;
    }

    this.column = column;
    this.top = top;
    this.bottom = bottom;
  }

  public long getColumn() {
    return column;
  }

  public long getTop() {
    return top;
  }

  public long getBottom() {
    return bottom;
  }

  @Override
  public boolean intersects(final Line other) {
    // Just defer to the HorizontalLine implementation, unless these are both vertical
    return other instanceof HorizontalLine && other.intersects(this);
  }
}
