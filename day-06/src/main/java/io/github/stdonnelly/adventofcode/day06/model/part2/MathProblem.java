package io.github.stdonnelly.adventofcode.day06.model.part2;

import io.github.stdonnelly.adventofcode.day06.model.Operation;
import java.util.ArrayList;
import java.util.List;

/// An individual math problem
///
/// @param operandList The list of numbers in the problem
/// @param operation The operation to perform on `operandList`
public record MathProblem(List<Long> operandList, Operation operation) {
  /// Parse the given section of the input as a [MathProblem]
  ///
  /// @param rows The section to parse, including the line with the operation.
  public static MathProblem parse(List<String> rows) {
    // Get the row count minus one because the last row is the operation
    final int rowCount = rows.size() - 1;
    final int rowLength = rows.get(0).length();

    // The outputs
    final List<Long> operandList = new ArrayList<>();
    final Operation operation = Operation.parse(rows.getLast().trim());

    // From right to left, parse columns as numbers
    for (int column = rowLength - 1; column >= 0; column--) {
      // Number for this column
      long thisNumber = 0;
      // This flag exists just in case there is a blank column
      boolean numberThisColumn = false;

      // Parse each character in the row
      for (int row = 0; row < rowCount; row++) {
        final int thisDigit = rows.get(row).codePointAt(column);
        // If this is a digit, shift the number and add it's value
        if ('0' <= thisDigit && thisDigit <= '9') {
          numberThisColumn = true;
          thisNumber = (thisNumber * 10) + (thisDigit - '0');
        } else if (thisDigit != ' ') {
          // The only other character should be a space
          throw new IllegalArgumentException("Unexpected character: '" + (char) thisDigit + "'");
        }
      }

      // Add the newly parse number
      if (numberThisColumn) {
        operandList.add(thisNumber);
      }
    }

    return new MathProblem(operandList, operation);
  }

  /// Computes the answer for this math problem
  public long compute() {
    return operandList.stream()
        .mapToLong(Long::longValue)
        .reduce(operation.getIdentity(), operation.getOp());
  }
}
