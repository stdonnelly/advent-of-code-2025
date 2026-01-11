package io.github.stdonnelly.adventofcode.day09.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/// Represents the tiled floor
public record Floor(FloorTile[][] tiles) {
  // #region Constructors and factory methods
  public Floor {
    // All of this is validation except for the last line

    // tiles must be non-null
    Objects.requireNonNull(tiles);

    // If there are any rows, they must be non-null and the same length
    if (tiles.length > 0) {
      int rowLength = tiles[0].length;

      for (FloorTile[] row : tiles) {
        if (row.length != rowLength) {
          throw new IndexOutOfBoundsException("rows must all be the same length");
        }
      }
    }
  }

  /// Construct a new instance with the given parameters
  ///
  /// The initial instance will be completely white
  /// @param width The number of columns
  /// @param height The number of rows
  public static Floor ofSize(final int width, final int height) {
    return new Floor(getTilesForSize(width, height));
  }

  // #endregion

  // #region Public methods
  /// Returns a copy of this, cropped by removing fully white columns and rows on each side
  public Floor crop() {
    // The indexes to crop to
    int firstRow;
    int lastRow;
    int firstCol;
    int lastCol;

    // Get the first row
    for (firstRow = 0; firstRow < tiles.length; firstRow++) {
      if (getFirstNonWhiteIndex(tiles[firstRow]) != -1) {
        break;
      }
    }

    // If everything it white, no need to check anything else; Just return an empty floor
    if (firstRow == tiles.length) {
      return new Floor(new FloorTile[0][]);
    }

    // Get the last row
    for (lastRow = tiles.length - 1; lastRow >= 0; lastRow--) {
      if (getFirstNonWhiteIndex(tiles[lastRow]) != -1) {
        break;
      }
    }

    // Get the first and last columns
    // Start by assuming that this is completely empty, then grow as larger rows are found
    firstCol = tiles[0].length;
    lastCol = 0;
    for (int i = firstRow; i <= lastRow; i++) {
      final int thisFirstCol = getFirstNonWhiteIndex(tiles[i]);
      // Ignore this row if everything is white
      if (thisFirstCol == -1) {
        continue;
      }

      final int thisLastCol = getLastNonWhiteIndex(tiles[i]);

      // Grow as needed
      if (thisFirstCol < firstCol) {
        firstCol = thisFirstCol;
      }

      if (thisLastCol > lastCol) {
        lastCol = thisLastCol;
      }
    }

    // Finally, actually crop the floor.
    // The last row and columns are inclusive in this method, but exclusive in cropToDimensions(),
    // so we need to add one to the last row and column.
    return cropToDimensions(firstRow, lastRow + 1, firstCol, lastCol + 1);
  }

  /// Crop to the specified dimensions
  ///
  /// @param rowFrom The first row in the floor to be copied (inclusive)
  /// @param rowTo The last row in the floor to be copied (exclusive)
  /// @param colFrom The first column in the floor to be copied (inclusive)
  /// @param colTo The last column in the floor to be copied (exclusive)
  public Floor cropToDimensions(
      final int rowFrom, final int rowTo, final int colFrom, final int colTo) {
    final FloorTile[][] cropped =
        Arrays.stream(tiles)
            // Trim the top rows
            .skip(rowFrom)
            // Trim the bottom rows
            .limit(rowTo - rowFrom)
            // Trim the left and right columns
            .map(row -> Arrays.copyOfRange(row, colFrom, colTo))
            .toArray(FloorTile[][]::new);
    return new Floor(cropped);
  }

  /// Get the number of rows in this floor
  public int getHeight() {
    return tiles.length;
  }

  /// Get the number of tiles in each row
  public int getWidth() {
    if (tiles.length <= 0) {
      return 0;
    } else {
      return tiles[0].length;
    }
  }

  /// Draw the closed polygon represented by the list of points
  public void drawShape(List<Point2d> points) {
    for (int i = 1; i < points.size(); i++) {
      drawLine(points.get(i - 1), points.get(i));
    }

    drawLine(points.getLast(), points.getFirst());
  }

  /// Draw the closed polygon represented by the list of points and fill it green
  public void drawFilledShape(List<Point2d> points) {
    drawShape(points);
    fill();
  }

  /// Draw a line between two points
  ///
  /// The resulting line will be green with red ends. Diagonal lines are not supported
  ///
  /// @throws ArithmeticException if either coordinate in either point is outside of the range of an
  /// integer
  public void drawLine(Point2d from, Point2d to) {
    int fromX = Math.toIntExact(from.x());
    int fromY = Math.toIntExact(from.y());
    int toX = Math.toIntExact(to.x());
    int toY = Math.toIntExact(to.y());
    // Find out if it is vertical or horizontal
    if (fromX == toX) {
      // Vertical
      drawLineVertical(fromX, fromY, toY);
    } else if (fromY == toY) {
      // Horizontal
      drawLineHorizontal(fromY, fromX, toX);
    } else {
      throw new UnsupportedOperationException("Diagonal lines are not supported");
    }
  }

  /**
   * Fill the shape with green
   *
   * <p>This uses a modified version of the even-odd rule. Instead of drawing a ray from inside to
   * infinity, this draws rays from left to right, toggling if it fills green or not.
   *
   * @see <a href="https://en.wikipedia.org/wiki/Even%E2%80%93odd_rule">Even–odd rule</a>
   */
  public void fill() {
    Arrays.stream(tiles).forEach(this::fillRow);
  }

  // #endregion

  // #region Object method overrides
  @Override
  public final boolean equals(Object arg0) {
    return arg0 instanceof Floor(FloorTile[][] otherTiles) && Arrays.deepEquals(tiles, otherTiles);
  }

  @Override
  public final int hashCode() {
    return Arrays.deepHashCode(tiles);
  }

  @Override
  public final String toString() {
    return Arrays.stream(tiles)
        .map(row -> Arrays.stream(row).map(FloorTile::toString).collect(Collectors.joining()))
        .collect(Collectors.joining("\n"));
  }

  // #endregion

  // #region Private/protected helpers

  /// Helper for [drawLine(Point2d,Point2d)][#drawLine(Point2d,Point2d)]
  private void drawLineHorizontal(int row, int fromColumn, int toColumn) {
    final FloorTile[] rowTiles = tiles[row];

    // Swap the columns if necessary to make sure "from" is after "to"
    if (toColumn < fromColumn) {
      int temp = toColumn;
      toColumn = fromColumn;
      fromColumn = temp;
    }

    // Draw the points
    rowTiles[fromColumn] = FloorTile.RED;
    rowTiles[toColumn] = FloorTile.RED;

    // Draw the line
    for (int i = fromColumn + 1; i < toColumn; i++) {
      if (FloorTile.WHITE.equals(rowTiles[i])) {
        rowTiles[i] = FloorTile.GREEN;
      }
    }
  }

  /// Helper for [drawLine(Point2d,Point2d)][#drawLine(Point2d,Point2d)]
  private void drawLineVertical(int column, int fromRow, int toRow) {
    // Swap the columns if necessary to make sure "from" is after "to"
    if (toRow < fromRow) {
      int temp = toRow;
      toRow = fromRow;
      fromRow = temp;
    }

    // Draw the points
    tiles[fromRow][column] = FloorTile.RED;
    tiles[toRow][column] = FloorTile.RED;

    // Draw the line
    for (int i = fromRow + 1; i < toRow; i++) {
      if (FloorTile.WHITE.equals(tiles[i][column])) {
        tiles[i][column] = FloorTile.GREEN;
      }
    }
  }

  /// Helper for [fill()][#fill()]
  private void fillRow(FloorTile[] row) {
    boolean inside = false;

    for (int i = 1; i < row.length; i++) {
      // If this is red or green and the previous is not red or green
      if ((row[i] != FloorTile.GREEN && row[i] != FloorTile.RED)
          && (row[i - 1] == FloorTile.GREEN || row[i - 1] == FloorTile.RED)) {
        inside = !inside;
      }

      // If this is inside and white, fill it
      if (inside && row[i] == FloorTile.WHITE) {
        row[i] = FloorTile.GREEN;
      }
    }
  }

  /// Helper for [ofSize(int,int)][#ofSize(int, int)]
  protected static FloorTile[][] getTilesForSize(int width, int height) {
    FloorTile[][] rows = new FloorTile[height][];
    for (int i = 0; i < height; i++) {
      rows[i] = new FloorTile[width];

      // Fill with white
      for (int j = 0; j < width; j++) {
        rows[i][j] = FloorTile.WHITE;
      }
    }
    return rows;
  }

  /// Get the index of the first tile that is not [FloorTile#WHITE], or `-1` if all tiles are white
  private int getFirstNonWhiteIndex(FloorTile[] row) {
    for (int i = 0; i < row.length; i++) {
      if (!FloorTile.WHITE.equals(row[i])) {
        return i;
      }
    }

    return -1;
  }

  /// Get the index of the last tile that is not [FloorTile#WHITE], or `-1` if all tiles are white
  private int getLastNonWhiteIndex(FloorTile[] row) {
    for (int i = row.length - 1; i >= 0; i--) {
      if (!FloorTile.WHITE.equals(row[i])) {
        return i;
      }
    }

    return -1;
  }
  // #endregion
}
