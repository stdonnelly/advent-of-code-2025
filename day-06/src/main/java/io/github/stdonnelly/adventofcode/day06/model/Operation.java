package io.github.stdonnelly.adventofcode.day06.model;

import java.util.function.LongBinaryOperator;

/// A mathematical operation
public enum Operation {
  /// Add
  ADD(0, Long::sum),
  /// Multiply
  MUL(1, (a, b) -> a * b),
  /// Subtract
  SUB(0, null),
  /// Divide
  DIV(1, null);

  private final long identity;
  private final LongBinaryOperator op;

  private Operation(long identity, LongBinaryOperator op) {
    this.identity = identity;
    this.op = op;
  }

  public static Operation parse(String input) {
    return switch (input) {
      case "+" -> ADD;
      case "*" -> MUL;
      case "-" -> SUB;
      case "/" -> DIV;
      default ->
          throw new IllegalArgumentException("Unable to parse '" + input + "' as math operation");
    };
  }

  /// Get the identity value for the accumulating function
  public long getIdentity() {
    return identity;
  }

  /// An associative, pure function for combining two values
  public LongBinaryOperator getOp() {
    return op;
  }
}
