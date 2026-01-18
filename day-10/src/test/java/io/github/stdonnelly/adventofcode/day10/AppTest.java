package io.github.stdonnelly.adventofcode.day10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day10.loader.MachineDescriptionLoader;
import io.github.stdonnelly.adventofcode.day10.model.MachineDescription;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class AppTest {
  private static final String EXAMPLE_FILE_NAME = "example_input.txt";

  /**
   * Test part 1 with the example input
   *
   * @throws IOException if the input loading fails
   */
  @Test
  void part1Test() throws IOException {
    final long EXPECTED = 7L;
    final InputLoader<MachineDescription> inputLoader =
        new MachineDescriptionLoader(EXAMPLE_FILE_NAME);
    final List<MachineDescription> input = inputLoader.load();
    assertEquals(EXPECTED, App.part1(input));
  }

  /**
   * Test part 2 with the example input
   *
   * @throws IOException if the input loading fails
   */
  @Test
  void part2Test() throws IOException {
    final long EXPECTED = -1L;
    final InputLoader<MachineDescription> inputLoader =
        new MachineDescriptionLoader(EXAMPLE_FILE_NAME);
    final List<MachineDescription> input = inputLoader.load();
    assertEquals(EXPECTED, App.part2(input));
  }
}
