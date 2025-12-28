package io.github.stdonnelly.adventofcode.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day06.loader.LongTableLoader;
import io.github.stdonnelly.adventofcode.day06.loader.part2.MathProblemLoader;
import io.github.stdonnelly.adventofcode.day06.model.LongTable;
import io.github.stdonnelly.adventofcode.day06.model.Operation;
import io.github.stdonnelly.adventofcode.day06.model.part2.MathProblem;
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
    final long EXPECTED = 3263827L;
    final InputLoader<MathProblem> inputLoader = new MathProblemLoader(EXAMPLE_FILE_NAME);
    final List<MathProblem> mathProblemList = inputLoader.load();
    assertEquals(EXPECTED, App.part2(mathProblemList));
  }
}
