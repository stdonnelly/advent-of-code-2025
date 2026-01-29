package io.github.stdonnelly.adventofcode.day10.model;

import java.util.Arrays;
import java.util.OptionalInt;
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

  // #region Public methods

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
    for (int i = 0; i < rows.length; i++) {
      final OptionalInt pivotColumn = swapWithNextPivot(i);
      if (pivotColumn.isEmpty()) {
        // All remaining rows are zero
        break;
      }
      scaleRowToOne(i, pivotColumn.getAsInt());
      subtractFromAllOtherRows(i, pivotColumn.getAsInt());
      normalizeZeros();
    }
  }

  /// Swap the rows at `index1` and `index2`
  public void swapRows(final int index1, final int index2) {
    // Not the most efficient way to do this, but it should be easier to read.
    Row temp = rows[index1];
    rows[index1] = rows[index2];
    rows[index2] = temp;
  }

  /// Converts all `-0.0` into `0.0` in this matrix
  ///
  /// This is necessary because floating point numbers have a negative 0, which
  /// Arrays.equals(double[], double[]) treats differently.
  public void normalizeZeros() {
    for (Row row : rows) {
      row.normalizeZeros();
    }
  }

  /// Determine if this row contains only integers
  ///
  /// @return `true` if all numbers in this row are integers or `false` if any value is fractional
  public boolean isIntegral() {
    return Arrays.stream(rows).allMatch(Row::isIntegral);
  }

  /// Determine the degrees of freedom of the system of linear equations represented by this matrix
  public int findDegreesOfFreedom() {
    // Create a copy in RREF
    Matrix matrix = getReducedForEchelonForm();

    // Count the number of pivots in matrix
    int pivotCount = 0;
    for (Row row : matrix.rows) {
      if (row.findPivotColumn().isPresent()) {
        pivotCount++;
      } else {
        break;
      }
    }

    // Subtract the number of total variables (excluding the last value in the row, because that is
    // the constant in the equation)
    return rows[0].values.length - 1 - pivotCount;
  }

  // #endregion

  // #region Helpers

  /// Swap this row with the next row that has the first leading value (i.e the pivot)
  ///
  /// @param rowIndex The row to work on
  /// @return The pivot column, which is the first nonzero column, or `Optional.empty()` if all
  /// remaining rows are all zero
  private OptionalInt swapWithNextPivot(final int rowIndex) {
    int pivotRow = rowIndex;
    OptionalInt pivotColumn = rows[rowIndex].findPivotColumn();

    // See if there is any row with an earlier pivot
    for (int i = rowIndex + 1; i < rows.length; i++) {
      OptionalInt thisPivotColumn = rows[i].findPivotColumn();
      // This only counts if this is before the currently-known best
      // That could be the case if this is the first known pivot column, or if it is better than the
      // last-known one.
      if (thisPivotColumn.isPresent()
          && (pivotColumn.isEmpty() || (thisPivotColumn.getAsInt() < pivotColumn.getAsInt()))) {
        pivotRow = i;
        pivotColumn = thisPivotColumn;
      }
    }

    if (pivotRow != rowIndex) {
      swapRows(rowIndex, pivotRow);
    }
    return pivotColumn;
  }

  /// Scale the swapped-in row
  private void scaleRowToOne(final int rowIndex, final int pivotColumn) {
    final double scale = rows[rowIndex].getValues()[pivotColumn];

    if (scale != 1) {
      rows[rowIndex].divide(scale);
    }
  }

  /// Subtract a scaled version of that row from all others that have a value in the same column
  private void subtractFromAllOtherRows(final int rowIndex, final int pivotColumn) {
    for (int i = 0; i < rows.length; i++) {
      // Skip this row, as subtracting would just eliminate it.
      if (i == rowIndex) {
        continue;
      }

      final Row thisRow = rows[i];
      final double scale = thisRow.getValues()[pivotColumn];

      // Only subtract if this column has a value here
      if (scale != 0) {
        Row scaledPivotRow = rows[rowIndex];
        // If the scale is not one, create a scaled copy of the row
        if (scale != 1) {
          scaledPivotRow = new Row(scaledPivotRow);
          scaledPivotRow.multiply(scale);
        }

        // Subtract
        thisRow.subtract(scaledPivotRow);
      }
    }
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
    private final double[] values;

    /// Constructor using array spread operation
    ///
    /// @param values The values in this row. Will be copied to a new array
    public Row(double... values) {
      this.values = Arrays.copyOf(values, values.length);
    }

    /// Constructor using ints
    ///
    /// @param values The values in this row. Will be copied into an array of `double`
    public Row(int... values) {
      double[] doubleValues = new double[values.length];

      for (int i = 0; i < values.length; i++) {
        doubleValues[i] = values[i];
      }
      this.values = doubleValues;
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
    public void multiply(final double scalar) {
      for (int i = 0; i < this.values.length; i++) {
        this.values[i] *= scalar;
      }
    }

    /// Find the quotient by dividing this row by a scalar factor
    ///
    /// This method will modify the values in this row
    ///
    /// @param scalar The scalar to multiply by
    public void divide(final double scalar) {
      for (int i = 0; i < this.values.length; i++) {
        this.values[i] /= scalar;
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

    /// Converts all `-0.0` into `0.0` in this row
    ///
    /// This is necessary because floating point numbers have a negative 0, which
    /// Arrays.equals(double[], double[]) treats differently.
    public void normalizeZeros() {
      for (int i = 0; i < values.length; i++) {
        if (values[i] == -0.0) {
          values[i] = 0.0;
        }
      }
    }

    /// Determine if this row contains only integers
    ///
    /// @return `true` if all numbers in this row are integers or `false` if any value is fractional
    public boolean isIntegral() {
      for (double value : values) {
        if (value % 1.0 != 0.0) {
          return false;
        }
      }
      return true;
    }

    /// Find the index of the first nonzero element
    ///
    /// @return The index of the first nonzero element, or `Optional.empty()` if the row is all 0
    public OptionalInt findPivotColumn() {
      for (int i = 0; i < values.length; i++) {
        if (values[i] != 0) {
          return OptionalInt.of(i);
        }
      }
      return OptionalInt.empty();
    }

    /// Getter for `values`
    ///
    /// @return An array representing the values in this row
    public double[] getValues() {
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
      return Arrays.stream(values).boxed().map("%5.2f"::formatted).collect(Collectors.joining(" "));
    }
  }

  // #endregion
}
