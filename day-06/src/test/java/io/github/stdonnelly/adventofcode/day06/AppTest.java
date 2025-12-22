package io.github.stdonnelly.adventofcode.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.day06.loader.LongTableLoader;
import io.github.stdonnelly.adventofcode.day06.model.LongTable;
import io.github.stdonnelly.adventofcode.day06.model.Operation;
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
    final long EXPECTED = 4277556L;
    final LongTableLoader inputLoader = new LongTableLoader(EXAMPLE_FILE_NAME);
    final LongTable input = inputLoader.loadAsLongTable();
    final List<Operation> operationList = inputLoader.getOperationList();
    assertEquals(EXPECTED, App.part1(input, operationList));
  }

  /**
   * Test part 2 with the example input
   *
   * @throws IOException if the input loading fails
   */
  @Test
  void part2Test() throws IOException {
    final long EXPECTED = -1;
    final LongTableLoader inputLoader = new LongTableLoader(EXAMPLE_FILE_NAME);
    final LongTable input = inputLoader.loadAsLongTable();
    final List<Operation> operationList = inputLoader.getOperationList();
    assertEquals(EXPECTED, App.part2(input, operationList));
  }
}
