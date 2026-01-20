package io.github.stdonnelly.adventofcode.day10.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.day10.MachineProvider;
import io.github.stdonnelly.adventofcode.day10.model.MachineDescription;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

class MinimumButtonCounterJoltageTest {
  @Disabled("Disabled until I actually work on the program itself")
  @ParameterizedTest
  @ArgumentsSource(MachineProvider.class)
  void testGetMinimumButtonPresses(
      final MachineDescription machineDescription, final int expected) {
    final MinimumButtonCounterJoltage minimumButtonCounterJoltage =
        new MinimumButtonCounterJoltage();
    assertEquals(expected, minimumButtonCounterJoltage.getMinimumButtonPresses(machineDescription));
  }
}
