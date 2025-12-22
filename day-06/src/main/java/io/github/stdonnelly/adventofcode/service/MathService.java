package io.github.stdonnelly.adventofcode.service;

import io.github.stdonnelly.adventofcode.day06.model.LongTable;
import io.github.stdonnelly.adventofcode.day06.model.Operation;
import java.util.Arrays;
import java.util.List;

/// The class that performs the calculations
public class MathService {
  /// Performs the given operations on the row in `array` that has the same index, then returns the
  /// sum of each of these operations
  ///
  /// @throws IllegalArgumentException If the size of `operationList` and the number of rows in
  /// `array` is not the same.
  /// @see MathService#performOperationOnOneRow(Operation,long[])
  public long performAllOperationsAndSum(List<Operation> operationList, LongTable array) {
    final int operationCount = operationList.size();
    if (operationCount != array.arr().length) {
      throw new IllegalArgumentException(
          "The number of operations and rows in the table must be the same");
    }

    long sum = 0;
    for (int i = 0; i < operationCount; i++) {
      sum += performOperationOnOneRow(operationList.get(i), array.arr()[i]);
    }
    return sum;
  }

  /// Perform the given operation to reduce the given array of `long`s to a single `long`
  ///
  /// @param operation The operation to perform
  /// @param row The array of numbers to perform the operation on
  /// @return The input row, reduced using the operation
  public long performOperationOnOneRow(Operation operation, long[] row) {
    return Arrays.stream(row).reduce(operation.getIdentity(), operation.getOp());
  }
}
