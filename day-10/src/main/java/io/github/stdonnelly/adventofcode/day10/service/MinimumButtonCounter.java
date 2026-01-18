package io.github.stdonnelly.adventofcode.day10.service;

import io.github.stdonnelly.adventofcode.day10.model.MachineDescription;
import java.util.Arrays;

public class MinimumButtonCounter {
  /// Count the minimum number of button presses to get the required indicator lights
  public int getMinimumButtonPresses(MachineDescription machine) {
    // Convert everything to bits
    final short indicatorLightBits = machine.indicatorLights().toBits();
    short[] buttonBits = new short[machine.buttons().length];
    for (int i = 0; i < machine.buttons().length; i++) {
      buttonBits[i] = machine.buttons()[i].toBits();
    }

    for (int i = 0; i <= buttonBits.length; i++) {
      if (checkPressingNButtons(i, buttonBits, indicatorLightBits)) {
        return i;
      }
    }

    throw new IllegalArgumentException(
        "Unable to find any combination of button presses for " + machine);
  }

  /// See if the given indicator lights are possible with a given number of button pressed
  ///
  /// @param numberOfButtons The number of button presses
  /// @param buttonBits The bit fields representing the buttons
  /// @param lightBits The bit field representing the indicator lights
  private boolean checkPressingNButtons(int numberOfButtons, short[] buttonBits, short lightBits) {
    // Base case: no buttons left to press
    // If there are no buttons left, this is only possible if the lights are all off
    if (numberOfButtons == 0) {
      return lightBits == 0;
    }

    // Validation, throw an error if there are negative button presses left, or if there are more
    // button presses than there are buttons.
    if (numberOfButtons < 0) {
      throw new IllegalArgumentException("Cannot press button negative number of times");
    }
    if (numberOfButtons > buttonBits.length) {
      throw new IllegalArgumentException("Cannot press more buttons than exist");
    }

    // Main recursive case, do or do not press a button, then recurse
    short[] subArray = Arrays.copyOfRange(buttonBits, 1, buttonBits.length);

    // If the number of button presses left is the number of buttons, we must press all of them.
    // Don't try to not press this button in that case.
    return (numberOfButtons < buttonBits.length
            // Try without pressing this button if possible
            && checkPressingNButtons(numberOfButtons, subArray, lightBits))
        // Try with pressing this button
        || checkPressingNButtons(
            numberOfButtons - 1, subArray, (short) (lightBits ^ buttonBits[0]));
  }
}
