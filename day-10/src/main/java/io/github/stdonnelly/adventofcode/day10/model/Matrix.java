package io.github.stdonnelly.adventofcode.day10.model;

import java.util.List;
import java.util.stream.Collectors;

/// Represents a mathematical matrix
public class Matrix {
  // #region Fields
  private final List<Row> rows;

  // #endregion

  // #region Constructors

  /// Constructor
  ///
  /// @param rows The rows of this matrix. Will be copied into an immutable [List]
  public Matrix(List<Row> rows) {
    this.rows = List.copyOf(rows);
  }

  // #endregion

  // #region RREF implementation

  /**
   * Find the reduced row echelon form (RREF) of this matrix
   *
   * <p>A definition of reduced row echelon form can be found on Wikipedia at <a
   * href="https://en.wikipedia.org/wiki/Row_echelon_form#Reduced_row_echelon_form">Row echelon form
   * (section Reduced row echelon form)</a>
   *
   * <p>This is found using <a href="https://en.wikipedia.org/wiki/Gaussian_elimination">Gaussian
   * elimination</a>
   *
   * @return A matrix representing the RREF of this matrix
   */
  public Matrix getReducedForEchelonForm() {
    // TODO: Implement RREF
    return this;
  }

  // #endregion

  // #region Accessors

  /// Getter for `rows`
  ///
  /// @return An immutable [List] representing the rows in this matrix
  public List<Row> getRows() {
    return rows;
  }

  // #endregion

  // #region Object overrides

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

  // #endregion

  // #region Inner classes

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

  // #endregion
}
