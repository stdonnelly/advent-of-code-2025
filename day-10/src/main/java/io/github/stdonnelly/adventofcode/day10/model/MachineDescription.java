package io.github.stdonnelly.adventofcode.day10.model;

import java.util.Arrays;

/// An entry in the manual describing one machine
///
/// @param indicatorLights Indicator light diagram
/// @param buttons Button wiring schematics
/// @param joltageRequirements The specified joltage requirements
public record MachineDescription(
    IndicatorLightDiagram indicatorLights,
    ButtonSchematic[] buttons,
    JoltageRequirements joltageRequirements) {
  /// Parse an input object
  ///
  /// @param input The input string to parse
  /// @return The input after parsing
  /// @throws IllegalArgumentException If the input is not parsable
  public static MachineDescription parse(String input) throws IllegalArgumentException {
    throw new java.lang.UnsupportedOperationException("TODO: write input parser");
  }

  @Override
  public final boolean equals(Object other) {
    return other
            instanceof
            MachineDescription(
                IndicatorLightDiagram otherIndicatorLights,
                ButtonSchematic[] otherButtons,
                JoltageRequirements otherJoltageRequirements)
        && this.indicatorLights.equals(otherIndicatorLights)
        && Arrays.equals(this.buttons, otherButtons)
        && this.joltageRequirements.equals(otherJoltageRequirements);
  }

  @Override
  public final int hashCode() {
    return indicatorLights.hashCode() ^ Arrays.hashCode(buttons) ^ joltageRequirements.hashCode();
  }

  @Override
  public final String toString() {
    // TODO
    throw new UnsupportedOperationException("TODO");
  }
}
