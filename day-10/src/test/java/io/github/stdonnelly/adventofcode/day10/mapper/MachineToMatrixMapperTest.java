package io.github.stdonnelly.adventofcode.day10.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.day10.MachineProvider;
import io.github.stdonnelly.adventofcode.day10.model.ButtonSchematic;
import io.github.stdonnelly.adventofcode.day10.model.MachineDescription;
import io.github.stdonnelly.adventofcode.day10.model.Matrix;
import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;

class MachineToMatrixMapperTest {
  // The mapper to test
  private static final MachineToMatrixMapper MAPPER = new MachineToMatrixMapper();

  @ParameterizedTest
  @ArgumentsSource(MachineProvider.class)
  void testMap(
      final MachineDescription machineDescription, final int unused, final Matrix expectedMatrix) {
    assertEquals(expectedMatrix, MAPPER.map(machineDescription));
  }

  // Use the first in the example for this
  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          3,0,0:0:0:0:1:1:3
          5,1,0:1:0:0:0:1:5
          4,2,0:0:1:1:1:0:4
          7,3,1:1:0:1:0:0:7
          """)
  void testMapRow(
      final int joltageRequirement, final int joltageIndex, final String expectedRowString) {
    // Hardcoded instead of using the parameter source because this is the same every time.
    final ButtonSchematic[] buttonSchematics =
        new ButtonSchematic[] {
          new ButtonSchematic(new int[] {3}),
          new ButtonSchematic(new int[] {1, 3}),
          new ButtonSchematic(new int[] {2}),
          new ButtonSchematic(new int[] {2, 3}),
          new ButtonSchematic(new int[] {0, 2}),
          new ButtonSchematic(new int[] {0, 1}),
        };
    final Matrix.Row expected =
        new Matrix.Row(
            Arrays.stream(expectedRowString.split(":")).mapToInt(Integer::parseInt).toArray());

    assertEquals(expected, MAPPER.mapRow(buttonSchematics, joltageRequirement, joltageIndex));
  }
}
