package io.github.stdonnelly.adventofcode.day10.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.day10.model.MachineDescription;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MinimumButtonCounterJoltageTest {
  @ParameterizedTest
  @CsvSource(
      delimiter = ';',
      textBlock =
          """
          10;[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
          12;[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
          11;[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
          """)
  void testGetMinimumButtonPresses(final int expected, final String machineString) {
    final MachineDescription machineDescription = MachineDescription.parse(machineString);
    final MinimumButtonCounterJoltage minimumButtonCounterJoltage =
        new MinimumButtonCounterJoltage();
    assertEquals(expected, minimumButtonCounterJoltage.getMinimumButtonPresses(machineDescription));
  }
}
