package io.github.stdonnelly.adventofcode.day10.model;

import java.util.Arrays;

/// A diagram containing the desired end state of the indicator lights
///
/// - `.` means "off" and will be represented by `false`
/// - `#` means "on" and will be represented by `true`
///
/// @param indicatorLights An array of booleans where `true` is on and `false` is off
public record IndicatorLightDiagram(boolean[] indicatorLights) {
  /// Parse an input object
  ///
  /// @param input The input string to parse
  /// @return The input after parsing
  /// @throws IllegalArgumentException If the input is not parsable
  public static IndicatorLightDiagram parse(String input) throws IllegalArgumentException {
    final String trimmedInput = MachineDescription.stripBrackets(input);
    boolean[] lights = new boolean[trimmedInput.length()];

    for (int i = 0; i < trimmedInput.length(); i++) {
      lights[i] =
          switch (trimmedInput.charAt(i)) {
            case '.' -> false;
            case '#' -> true;
            default ->
                throw new IllegalArgumentException(
                    "Unexpected character while parsing indicator light diagram: "
                        + trimmedInput.charAt(i));
          };
    }

    return new IndicatorLightDiagram(lights);
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
    char[] indicatorLightChars = new char[indicatorLights.length];
    for (int i = 0; i < indicatorLights.length; i++) {
      indicatorLightChars[i] = indicatorLights[i] ? '#' : '.';
    }
    return "[" + String.valueOf(indicatorLightChars) + "]";
  }
}
