package io.github.stdonnelly.adventofcode.day10.model;

import java.util.Arrays;

/// Joltage requirements for the machine
///
/// @param requiredJoltage The joltage required for each indicator light?
public record JoltageRequirements(int[] requiredJoltage) {
  /// Parse an input object
  ///
  /// @param input The input string to parse
  /// @return The input after parsing
  /// @throws IllegalArgumentException If the input is not parsable
  public static JoltageRequirements parse(String input) throws IllegalArgumentException {
    throw new java.lang.UnsupportedOperationException("TODO: write input parser");
  }

  @Override
  public final boolean equals(Object other) {
    return other instanceof JoltageRequirements(int[] otherRequiredJoltage)
        && Arrays.equals(requiredJoltage, otherRequiredJoltage);
  }

  @Override
  public final int hashCode() {
    return Arrays.hashCode(requiredJoltage);
  }

  @Override
  public final String toString() {
    // TODO
    throw new UnsupportedOperationException("TODO");
  }
}
