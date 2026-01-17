package io.github.stdonnelly.adventofcode.day10.model;

import java.util.Arrays;

/// A button wiring schematic
///
/// @param connectedLights The lights in a light in an [IndicatorLightDiagram] that are toggled by
/// this button
public record ButtonSchematic(int[] connectedLights) {
  /// Parse an input object
  ///
  /// @param input The input string to parse
  /// @return The input after parsing
  /// @throws IllegalArgumentException If the input is not parsable
  public static ButtonSchematic parse(String input) throws IllegalArgumentException {
    throw new java.lang.UnsupportedOperationException("TODO: write input parser");
  }

  @Override
  public final boolean equals(Object other) {
    return other instanceof ButtonSchematic(int[] otherConnectedLights)
        && Arrays.equals(connectedLights, otherConnectedLights);
  }

  @Override
  public final int hashCode() {
    return Arrays.hashCode(connectedLights);
  }

  @Override
  public final String toString() {
    // TODO
    throw new UnsupportedOperationException("TODO");
  }
}
