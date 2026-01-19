package io.github.stdonnelly.adventofcode.day10.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.day10.model.MachineDescription;
import io.github.stdonnelly.adventofcode.day10.testutil.MachineProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

class MinimumButtonCounterJoltageTest {
  @ParameterizedTest
  @ArgumentsSource(MachineProvider.class)
  void testGetMinimumButtonPresses(
      final MachineDescription machineDescription, final int expected) {
    final MinimumButtonCounterJoltage minimumButtonCounterJoltage =
        new MinimumButtonCounterJoltage();
    assertEquals(expected, minimumButtonCounterJoltage.getMinimumButtonPresses(machineDescription));
  }
}
