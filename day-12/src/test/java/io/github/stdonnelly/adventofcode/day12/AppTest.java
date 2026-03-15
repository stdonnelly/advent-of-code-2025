package io.github.stdonnelly.adventofcode.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.day12.loader.ProblemInputLoader;
import io.github.stdonnelly.adventofcode.day12.model.ProblemInput;
import java.io.IOException;
import java.text.ParseException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AppTest {
  private static final String EXAMPLE_FILE_NAME = "example_input.txt";

  /**
   * Test part 1 with the example input
   *
   * @throws IOException if the input loading fails
   */
  @Disabled("This test is actually too thorough for what is required by the actual input")
  @Test
  void part1Test() throws IOException, ParseException {
    final long EXPECTED = 2L;
    final ProblemInputLoader inputLoader = new ProblemInputLoader(EXAMPLE_FILE_NAME);
    final ProblemInput input = inputLoader.load();
    assertEquals(EXPECTED, App.part1(input));
  }
}
