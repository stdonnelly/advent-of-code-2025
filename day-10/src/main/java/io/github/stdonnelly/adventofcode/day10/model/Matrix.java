package io.github.stdonnelly.adventofcode.day10.model;

import java.util.List;
import java.util.stream.Collectors;

/// Represents a mathematical matrix
public class Matrix {
  private final List<Row> rows;

  /// Constructor
  ///
  /// @param rows The rows of this matrix. Will be copied into an immutable [List]
  public Matrix(List<Row> rows) {
    this.rows = List.copyOf(rows);
  }

  /// Getter for `rows`
  ///
  /// @return An immutable [List] representing the rows in this matrix
  public List<Row> getRows() {
    return rows;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Matrix other && this.rows.equals(other.rows);
  }

  @Override
  public int hashCode() {
    return rows.hashCode();
  }

  @Override
  public String toString() {
    return rows.stream().map(Matrix.Row::toString).collect(Collectors.joining("\n"));
  }

  /// Represents a row in a [Matrix]
  public static class Row {
    private final List<Integer> values;

    /// Constructor
    ///
    /// @param values The values in this row. Will be copied into an immutable [List]
    public Row(List<Integer> values) {
      this.values = List.copyOf(values);
    }

    /// Getter for `values`
    ///
    /// @return An immutable [List] representing the values in this row
    public List<Integer> getValues() {
      return this.values;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof Row other && this.values.equals(other.values);
    }

    @Override
    public int hashCode() {
      return values.hashCode();
    }

    @Override
    public String toString() {
      return values.stream().map("%d"::formatted).collect(Collectors.joining(" "));
    }
  }
}
