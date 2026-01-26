package io.github.stdonnelly.adventofcode.day10.model;

import io.github.stdonnelly.adventofcode.day10.error.NonIntegerDivisionException;
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
   * @throws NonIntegerDivisionException If the the division will result in a non-integer value
   */
  public Matrix getReducedForEchelonForm() throws NonIntegerDivisionException {
    Matrix copiedMatrix = new Matrix(this);
    copiedMatrix.applyReducedRowEchelonForm();
    return copiedMatrix;
  }

  /// Apply reduced row echelon form (RREF) to this instance
  ///
  /// See [getReducedRowEchelonForm()][#getReducedForEchelonForm()] for details. The only
  /// difference is that this method *will* modify this instance, rather than returning a new
  /// instance.
  /// @throws NonIntegerDivisionException If the the division will result in a non-integer value
  public void applyReducedRowEchelonForm() throws NonIntegerDivisionException {
    for (int i = 0; i < rows.length; i++) {
      final OptionalInt pivotColumn = swapWithNextPivot(i);
      if (pivotColumn.isEmpty()) {
        // All remaining rows are zero
        break;
      }
      // TODO: Remove this and allow martices to be non-integer
      try {
        scaleRowToOne(i, pivotColumn.getAsInt());
      } catch (NonIntegerDivisionException e) {
        throw new NonIntegerDivisionException("While getting RREF of matrix\n" + this, e);
      }
      subtractFromAllOtherRows(i, pivotColumn.getAsInt());
    }
  }

  /// Swap the rows at `index1` and `index2`
  public void swapRows(final int index1, final int index2) {
    // Not the most efficient way to do this, but it should be easier to read.
    Row temp = rows[index1];
    rows[index1] = rows[index2];
    rows[index2] = temp;
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
      // Also, prioritize -1 and 1 as pivots.
      if (thisPivotColumn.isPresent()) {
        final int thisPivotColumnValue = thisPivotColumn.getAsInt();
        final int oldPivotColumnValue = pivotColumn.orElse(-1);

        if (pivotColumn.isEmpty() || (thisPivotColumnValue < oldPivotColumnValue)) {
          pivotRow = i;
          pivotColumn = thisPivotColumn;
        } else {
          // TODO: Remove this and allow martices to be non-integer
          // Also, prioritize -1 and 1 as pivots.
          final int oldPivotValue = rows[pivotRow].getValues()[oldPivotColumnValue];
          final int newPivotValue = rows[i].getValues()[thisPivotColumnValue];
          if (oldPivotValue != 1
              && oldPivotValue != -1
              && ((newPivotValue == 1) || (newPivotValue == -1))) {
            pivotRow = i;
            pivotColumn = thisPivotColumn;
          }
        }
      }
    }

    if (pivotRow != rowIndex) {
      swapRows(rowIndex, pivotRow);
    }
    return pivotColumn;
  }

  /// Scale the swapped-in row
  /// @throws NonIntegerDivisionException If the the division will result in a non-integer value
  private void scaleRowToOne(final int rowIndex, final int pivotColumn)
      throws NonIntegerDivisionException {
    final int scale = rows[rowIndex].getValues()[pivotColumn];

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
      final int scale = thisRow.getValues()[pivotColumn];

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

    /// Find the quotient by dividing this row by a scalar factor
    ///
    /// This method will modify the values in this row
    ///
    /// @param scalar The scalar to multiply by
    /// @throws NonIntegerDivisionException If the the division will result in a non-integer value
    public void divide(final int scalar) throws NonIntegerDivisionException {
      // First, ensure the division is actually possible. This is done in a separate for loop to
      // ensure no changes are made before committing
      // TODO: Remove this and allow martices to be non-integer
      if (Arrays.stream(values).anyMatch(v -> (v % scalar) != 0)) {
        throw new NonIntegerDivisionException(
            String.format("Cannot divide integer row [%s] by scalar %d", this, scalar));
      }
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
