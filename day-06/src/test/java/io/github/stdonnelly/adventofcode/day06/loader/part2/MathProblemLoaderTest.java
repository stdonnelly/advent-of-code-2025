package io.github.stdonnelly.adventofcode.day06.loader.part2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.day06.model.Operation;
import io.github.stdonnelly.adventofcode.day06.model.part2.MathProblem;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class MathProblemLoaderTest {
  private static final MathProblemLoader MATH_PROBLEM_LOADER =
      new MathProblemLoader("example_input.txt");

  @Test
  void testGetColumnLengths_example1() {
    final String operandRow = "*   +   *   +  ";
    assertEquals(List.of(3, 3, 3, 3), MATH_PROBLEM_LOADER.getColumnLengths(operandRow));
  }

  @Test
  void testGetColumnLengths_example2() {
    final String operandRow = "*  +    * +  ";
    assertEquals(List.of(2, 4, 1, 3), MATH_PROBLEM_LOADER.getColumnLengths(operandRow));
  }

  @Test
  void testLoad() throws IOException {
    List<MathProblem> mathProblemList = MATH_PROBLEM_LOADER.load();
    List<MathProblem> expected =
        List.of(
            new MathProblem(List.of(4L, 431L, 623L), Operation.ADD),
            new MathProblem(List.of(175L, 581L, 32L), Operation.MUL),
            new MathProblem(List.of(8L, 248L, 369L), Operation.ADD),
            new MathProblem(List.of(356L, 24L, 1L), Operation.MUL));

    assertEquals(expected, mathProblemList);
  }
}
