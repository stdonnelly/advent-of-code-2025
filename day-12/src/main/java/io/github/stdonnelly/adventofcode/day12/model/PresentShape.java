package io.github.stdonnelly.adventofcode.day12.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public record PresentShape(boolean[][] grid) {
  public PresentShape {
    Objects.requireNonNull(grid);
  }

  @Override
  public final boolean equals(Object other) {
    return other instanceof PresentShape(var otherGrid) && Arrays.deepEquals(grid, otherGrid);
  }

  @Override
  public final int hashCode() {
    return Arrays.deepHashCode(grid);
  }

  @Override
  public final String toString() {
    return Arrays.stream(grid)
        .map(
            row -> {
              char[] rowChars = new char[row.length];
              for (int i = 0; i < row.length; i++) {
                rowChars[i] = row[i] ? '#' : '.';
              }
              return String.valueOf(rowChars);
            })
        .collect(Collectors.joining("\n"));
  }
}
