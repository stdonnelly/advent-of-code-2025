package io.github.stdonnelly.adventofcode.day05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.day05.loader.TaskInputLoader;
import io.github.stdonnelly.adventofcode.day05.model.TaskInput;
import java.io.IOException;
import org.junit.jupiter.api.Disabled;
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
    final long EXPECTED = 3;
    final TaskInputLoader inputLoader = new TaskInputLoader(EXAMPLE_FILE_NAME);
    final TaskInput input = inputLoader.load();
    assertEquals(EXPECTED, App.part1(input));
  }

  /**
   * Test part 2 with the example input
   *
   * @throws IOException if the input loading fails
   */
  @Disabled("TODO")
  @Test
  void part2Test() throws IOException {
    final long EXPECTED = 14;
    final TaskInputLoader inputLoader = new TaskInputLoader(EXAMPLE_FILE_NAME);
    final TaskInput input = inputLoader.load();
    assertEquals(EXPECTED, App.part2(input));
  }
}
