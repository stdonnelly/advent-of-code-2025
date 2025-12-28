package io.github.stdonnelly.adventofcode.day06.model.part2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.day06.model.Operation;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MathProblemTest {
  @Test
  void testParse_example1() {
    final List<String> rows = List.of("64 ", "23 ", "314", "+  ");
    final List<Long> expectedOperandList = List.of(4L, 431L, 623L);

    final MathProblem mathProblem = MathProblem.parse(rows);

    assertEquals(expectedOperandList, mathProblem.operandList());
    assertEquals(Operation.ADD, mathProblem.operation());
  }

  @Test
  void testParse_example2() {
    final List<String> rows = List.of(" 51", "387", "215", "*  ");
    final List<Long> expectedOperandList = List.of(175L, 581L, 32L);

    final MathProblem mathProblem = MathProblem.parse(rows);

    assertEquals(expectedOperandList, mathProblem.operandList());
    assertEquals(Operation.MUL, mathProblem.operation());
  }

  @Test
  void testParse_example3() {
    final List<String> rows = List.of("328", "64 ", "98 ", "+  ");
    final List<Long> expectedOperandList = List.of(8L, 248L, 369L);

    final MathProblem mathProblem = MathProblem.parse(rows);

    assertEquals(expectedOperandList, mathProblem.operandList());
    assertEquals(Operation.ADD, mathProblem.operation());
  }

  @Test
  void testParse_example4() {
    final List<String> rows = List.of("123", " 45", "  6", "*  ");
    final List<Long> expectedOperandList = List.of(356L, 24L, 1L);

    final MathProblem mathProblem = MathProblem.parse(rows);

    assertEquals(expectedOperandList, mathProblem.operandList());
    assertEquals(Operation.MUL, mathProblem.operation());
  }

  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          4,431,623,+,1058
          175,581,32,*,3253600
          8,248,369,+,625
          356,24,1,*,8544
          """)
  void testCompute(
      long operand1, long operand2, long operand3, String operationString, long answer) {
    final MathProblem mathProblem =
        new MathProblem(List.of(operand1, operand2, operand3), Operation.parse(operationString));

    assertEquals(answer, mathProblem.compute());
  }
}
