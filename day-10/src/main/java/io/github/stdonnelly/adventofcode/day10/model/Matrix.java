package io.github.stdonnelly.adventofcode.day10.model;

import java.util.Arrays;
import java.util.stream.Collectors;

/// Represents a mathematical matrix
///
/// The matrix is modifiable. The constructors copy the arguments into a modifiable version.
public class Matrix {
  // #region Fields
  private final Row[] rows;

  // #endregion

  // #region Constructors

  /// Constructor using array spread
  ///
  /// @param rows The rows of this matrix. Will be copied to a new array.
  public Matrix(Row... rows) {
    this.rows = Arrays.copyOf(rows, rows.length);
  }

  /// Copy constructor
  ///
  /// Each row will be copied as well, resulting in a deep copy
  public Matrix(Matrix matrix) {
    Row[] copiedRows = new Row[matrix.rows.length];
    for (int i = 0; i < matrix.rows.length; i++) {
      copiedRows[i] = new Row(matrix.rows[i]);
    }
    this.rows = copiedRows;
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
   * <p><strong>Note:</strong> Even though this is modifiable, this method will <em>not</em> modify
   * this instance. It will create a new copy.
   *
   * @return A matrix representing the RREF of this matrix
   */
  public Matrix getReducedForEchelonForm() {
    Matrix copiedMatrix = new Matrix(this);
    copiedMatrix.applyReducedRowEchelonForm();
    return copiedMatrix;
  }

  /// Apply reduced row echelon form (RREF) to this instance
  ///
  /// See [getReducedRowEchelonForm()][#getReducedForEchelonForm()] for details. The only
  /// difference is that this method *will* modify this instance, rather than returning a new
  /// instance.
  public void applyReducedRowEchelonForm() {
    throw new UnsupportedOperationException("TODO");
  }

  // #endregion

  // #region Public methods

  /// Swap the rows at `index1` and `index2`
  public void swapRows(final int index1, final int index2) {
    // Not the most efficient way to do this, but it should be easier to read.
    Row temp = rows[index1];
    rows[index1] = rows[index2];
    rows[index2] = temp;
  }

  // #endregion

  // #region Accessors

  /// Getter for `rows`
  ///
  /// @return An array representing the rows in this matrix
  public Row[] getRows() {
    return rows;
  }

  // #endregion

  // #region Object overrides

  @Override
  public boolean equals(Object o) {
    return o instanceof Matrix other && Arrays.equals(this.rows, other.rows);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(rows);
  }

  @Override
  public String toString() {
    return Arrays.stream(rows).map(Row::toString).collect(Collectors.joining("\n"));
  }

  // #endregion

  // #region Inner classes

  /// Represents a row in a [Matrix]
  public static class Row {
    private final int[] values;

    /// Constructor using array spread operation
    ///
    /// @param values The values in this row. Will be copied to a new array
    public Row(int... values) {
      this.values = Arrays.copyOf(values, values.length);
    }

    /// Copy constructor
    public Row(Row row) {
      this(row.values);
    }

    /// Find the product of this row multiplied by a scalar factor
    ///
    /// This method will modify the values in this row
    ///
    /// @param scalar The scalar to multiply by
    public void multiply(final int scalar) {
      for (int i = 0; i < this.values.length; i++) {
        this.values[i] *= scalar;
      }
    }

    /// Subtract the given row from this row
    ///
    /// This method will modify the values in this row
    ///
    /// @param subtrahend The row of values to subtract from this
    public void subtract(final Row subtrahend) {
      final int size = this.values.length;
      if (size != subtrahend.values.length) {
        throw new IllegalArgumentException("Cannot subtract rows of different sizes");
      }

      for (int i = 0; i < size; i++) {
        this.values[i] -= subtrahend.values[i];
      }
    }

    /// Getter for `values`
    ///
    /// @return An array representing the values in this row
    public int[] getValues() {
      return this.values;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof Row other && Arrays.equals(this.values, other.values);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(values);
    }

    @Override
    public String toString() {
      return Arrays.stream(values).boxed().map("%2d"::formatted).collect(Collectors.joining(" "));
    }
  }

  // #endregion
}
