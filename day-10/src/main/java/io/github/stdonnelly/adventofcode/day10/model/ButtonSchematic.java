package io.github.stdonnelly.adventofcode.day10.model;

import java.util.Arrays;
import java.util.stream.Collectors;

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
    final String[] splitInput = MachineDescription.stripBrackets(input).split(",");
    int[] lights = new int[splitInput.length];

    for (int i = 0; i < splitInput.length; i++) {
      lights[i] = Integer.parseInt(splitInput[i]);
    }

    return new ButtonSchematic(lights);
  }

  /// Get the representation of this as a bitmask
  public short toBits() {
    short bits = 0;
    for (int light : connectedLights) {
      bits |= 1 << light;
    }

    return bits;
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
    return Arrays.stream(connectedLights)
        .mapToObj(Integer::toString)
        .collect(Collectors.joining(",", "(", ")"));
  }
}
