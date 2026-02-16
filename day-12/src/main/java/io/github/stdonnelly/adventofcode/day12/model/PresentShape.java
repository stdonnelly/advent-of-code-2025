package io.github.stdonnelly.adventofcode.day12.model;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public record PresentShape(boolean[][] grid) {
  public PresentShape {
    Objects.requireNonNull(grid);
  }

  /// Parse a string into a new instance
  ///
  /// @param input The string to parse
  /// @return The [PresentShape] represented by `input`
  /// @throws ParseException If the input cannot be parsed
  public static PresentShape parse(String input) throws ParseException {
    final String[] rows = input.split("\\s");
    boolean[][] grid = new boolean[rows.length][];

    for (int i = 0; i < rows.length; i++) {
      grid[i] = new boolean[rows[i].length()];
      for (int j = 0; j < rows[i].length(); j++) {
        char ch = rows[i].charAt(j);
        grid[i][j] =
            switch (ch) {
              case '#' -> true;
              case '.' -> false;
              default ->
                  throw new ParseException(
                      "Unable to parse present shape with character '" + ch + "'",
                      input.indexOf(ch));
            };
      }
    }

    return new PresentShape(grid);
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
