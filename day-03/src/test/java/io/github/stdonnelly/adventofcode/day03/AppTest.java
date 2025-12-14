package io.github.stdonnelly.adventofcode.day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.day03.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day03.model.BatteryBank;
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
    final int EXPECTED = 357;
    final InputLoader inputLoader = new InputLoader(EXAMPLE_FILE_NAME);
    final List<BatteryBank> input = inputLoader.load();
    assertEquals(EXPECTED, App.part1(input));
  }

  /**
   * Test part 2 with the example input
   *
   * @throws IOException if the input loading fails
   */
  @Test
  void part2Test() throws IOException {
    final long EXPECTED = 3121910778619L;
    final InputLoader inputLoader = new InputLoader(EXAMPLE_FILE_NAME);
    final List<BatteryBank> input = inputLoader.load();
    assertEquals(EXPECTED, App.part2(input));
  }
}
