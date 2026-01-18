package io.github.stdonnelly.adventofcode.day10.model;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/// An entry in the manual describing one machine
///
/// @param indicatorLights Indicator light diagram
/// @param buttons Button wiring schematics
/// @param joltageRequirements The specified joltage requirements
public record MachineDescription(
    IndicatorLightDiagram indicatorLights,
    ButtonSchematic[] buttons,
    JoltageRequirements joltageRequirements) {
  // A regex that only includes the contents of an element, without the brackets
  private static final Pattern ELEMENT_CONTENTS = Pattern.compile("[0-9\\.#,]+");

  /// Parse an input object
  ///
  /// @param input The input string to parse
  /// @return The input after parsing
  /// @throws IllegalArgumentException If the input is not parsable
  public static MachineDescription parse(String input) throws IllegalArgumentException {
    final String[] splitInput = input.split(" ");
    // Parse single fields
    final IndicatorLightDiagram lights = IndicatorLightDiagram.parse(splitInput[0]);
    final JoltageRequirements joltage =
        JoltageRequirements.parse(splitInput[splitInput.length - 1]);

    // Parse the list of button schematics
    ButtonSchematic[] buttonSchematics = new ButtonSchematic[splitInput.length - 2];
    for (int i = 0; i < splitInput.length - 2; i++) {
      buttonSchematics[i] = ButtonSchematic.parse(splitInput[i + 1]);
    }

    return new MachineDescription(lights, buttonSchematics, joltage);
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
    return String.format(
        "%s %s %s",
        indicatorLights,
        Arrays.stream(buttons).map(ButtonSchematic::toString).collect(Collectors.joining(" ")),
        joltageRequirements);
  }

  /// Remove the leading and trailing brackets, if any
  ///
  /// This gets the first section of the input string that matches `/[0-9\.#,]*/`
  static final String stripBrackets(String input) {
    Matcher matcher = ELEMENT_CONTENTS.matcher(input);

    if (matcher.find()) {
      return matcher.group();
    } else {
      return "";
    }
  }
}
