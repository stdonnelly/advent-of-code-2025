package io.github.stdonnelly.adventofcode.day10.model;

import java.util.Arrays;
import java.util.stream.Collectors;

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
    final String[] splitInput = MachineDescription.stripBrackets(input).split(",");
    int[] joltage = new int[splitInput.length];

    for (int i = 0; i < splitInput.length; i++) {
      joltage[i] = Integer.parseInt(splitInput[i]);
    }

    return new JoltageRequirements(joltage);
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
    return Arrays.stream(requiredJoltage)
        .mapToObj(Integer::toString)
        .collect(Collectors.joining(",", "{", "}"));
  }
}
