package io.github.stdonnelly.adventofcode.day10.model;

import java.util.Arrays;

/// A diagram containing the desired end state of the indicator lights
///
/// @param indicatorLights An array of booleans where `true` is on and `false` is off
public record IndicatorLightDiagram(boolean[] indicatorLights) {
  /// Parse an input object
  ///
  /// @param input The input string to parse
  /// @return The input after parsing
  /// @throws IllegalArgumentException If the input is not parsable
  public static IndicatorLightDiagram parse(String input) throws IllegalArgumentException {
    throw new java.lang.UnsupportedOperationException("TODO: write input parser");
  }

  @Override
  public final boolean equals(Object other) {
    return other instanceof IndicatorLightDiagram(boolean[] otherIndicatorLights)
        && Arrays.equals(indicatorLights, otherIndicatorLights);
  }

  @Override
  public final int hashCode() {
    return Arrays.hashCode(indicatorLights);
  }

  @Override
  public final String toString() {
    // TODO
    throw new UnsupportedOperationException("TODO");
  }
}
