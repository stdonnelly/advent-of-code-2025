package io.github.stdonnelly.adventofcode.day06.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/// A 2D array of `long`s
///
/// @param arr The internal array. Must be non-null and rectangular. I.e. all rows must be the same
/// length.
public record LongTable(long[][] arr) {
  public LongTable {
    Objects.requireNonNull(arr, "array must not be null");

    // Validate the array is rectangular
    if (arr.length > 0) {
      final int firstRowLength = arr[0].length;

      for (long[] row : arr) {
        if (row == null || row.length != firstRowLength) {
          throw new IllegalArgumentException(
              "array must be rectangular. At least one row has a different length than the first"
                  + " row");
        }
      }
    }
  }

  /// Pivots rows into columns
  ///
  /// @return A pivoted version of this
  public LongTable pivot() {
    // Get lengths and perform input validation
    final int inputRowCount = arr.length;
    if (inputRowCount == 0) {
      return new LongTable(new long[0][]);
    }
    final int inputColumnCount = arr[0].length;
    if (inputColumnCount == 0) {
      return new LongTable(new long[0][]);
    }

    // Create and allocate the pivoted array
    long[][] pivoted = new long[inputColumnCount][];

    // Populate the pivoted array
    for (int pivotedRow = 0; pivotedRow < inputColumnCount; pivotedRow++) {
      pivoted[pivotedRow] = new long[inputRowCount];
      for (int pivotedCol = 0; pivotedCol < inputRowCount; pivotedCol++) {
        pivoted[pivotedRow][pivotedCol] = arr[pivotedCol][pivotedRow];
      }
    }

    return new LongTable(pivoted);
  }

  @Override
  public final boolean equals(Object o) {
    return o instanceof LongTable(long[][] otherArr) && Arrays.deepEquals(arr, otherArr);
  }

  @Override
  public final int hashCode() {
    return Arrays.deepHashCode(arr);
  }

  @Override
  public final String toString() {
    return Arrays.stream(arr)
        .map(
            // For each row, map the row
            row ->
                Arrays.stream(row)
                    .mapToObj(l -> String.format("%4d", l))
                    .collect(Collectors.joining(" ")))
        .collect(Collectors.joining("\n"));
  }
}
