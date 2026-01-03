package io.github.stdonnelly.adventofcode.day09.model;

import java.util.Arrays;
import java.util.stream.Collectors;

/// Represents the tiled floor
public record Floor(FloorTile[][] tiles) {
  public static Floor ofSize(int width, int height) {
    FloorTile[][] rows = new FloorTile[height][];
    for (int i = 0; i < height; i++) {
      rows[height] = new FloorTile[width];
    }
    return new Floor(rows);
  }

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
}
