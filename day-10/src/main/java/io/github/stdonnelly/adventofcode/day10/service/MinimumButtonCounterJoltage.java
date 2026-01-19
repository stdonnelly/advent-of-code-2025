package io.github.stdonnelly.adventofcode.day10.service;

import io.github.stdonnelly.adventofcode.day10.error.ImpossibleJoltageException;
import io.github.stdonnelly.adventofcode.day10.model.ButtonSchematic;
import io.github.stdonnelly.adventofcode.day10.model.MachineDescription;
import java.util.Arrays;
import java.util.Comparator;

public class MinimumButtonCounterJoltage {
  /// Count the minimum number of button presses to get the required joltage values
  public int getMinimumButtonPresses(MachineDescription machineDescription) {
    // Start by getting a sorted version of the input array where the buttons that affect the most
    // are first
    ButtonSchematic[] sortedButtonSchematics =
        Arrays.copyOf(machineDescription.buttons(), machineDescription.buttons().length);
    Arrays.sort(
        sortedButtonSchematics,
        Comparator.comparingInt((ButtonSchematic b) -> b.connectedLights().length).reversed());

    // TODO: Find a better order
    // Just sorting from buttons that affect the most to buttons that affect the least is not enough
    // because the greedy algorithm backs itself into a corner.
    // Perhaps a greedy algorithm using the highest joltage requirement first will work?

    try {
      return getMinimumButtonPressesOrdered(
          sortedButtonSchematics, machineDescription.joltageRequirements().requiredJoltage());
    } catch (ImpossibleJoltageException _) {
      System.out.println(
          "Unable to find a sequence of button presses for " + machineDescription.toString());
      return 0;
    }
  }

  /// Get the number of button presses using
  /// [pressButtonGreedily(int\[\],ButtonSchematic)][#pressButtonGreedily(int\[\],ButtonSchematic)]
  /// in order
  ///
  /// @return The number of button presses
  /// @throws ImpossibleJoltageException If the joltage cannot be achieved with this setup
  private int getMinimumButtonPressesOrdered(
      ButtonSchematic[] buttonSchematics, int[] joltageRequirements)
      throws ImpossibleJoltageException {
    int[] remainingJoltage = Arrays.copyOf(joltageRequirements, joltageRequirements.length);

    // Press buttons and get the sum
    int totalPresses = 0;
    for (ButtonSchematic buttonSchematic : buttonSchematics) {
      totalPresses += pressButtonGreedily(remainingJoltage, buttonSchematic);
    }

    // Ensure the requirements are all met
    for (int requirement : remainingJoltage) {
      if (requirement != 0) {
        throw new ImpossibleJoltageException();
      }
    }

    return totalPresses;
  }

  /// Press the button as many times as possible
  ///
  /// It is not possible for a joltage requirement to be negative, so this will stop when any
  /// affected requirement reaches 0.
  ///
  /// @param joltageRequirements The array of joltage requirements. Will be modified.
  /// @param buttonSchematic The button to press
  /// @return The number of times the button was pressed
  private int pressButtonGreedily(int[] joltageRequirements, ButtonSchematic buttonSchematic) {
    final int minAffectedRequirement =
        Arrays.stream(buttonSchematic.connectedLights())
            .map(i -> joltageRequirements[i])
            .min()
            .orElse(0);

    for (int i : buttonSchematic.connectedLights()) {
      joltageRequirements[i] -= minAffectedRequirement;
    }

    return minAffectedRequirement;
  }
}
