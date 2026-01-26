package io.github.stdonnelly.adventofcode.day10.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.day10.error.NonIntegerDivisionException;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MatrixTest {
  @ParameterizedTest
  @MethodSource("matrixWithRREFProvider")
  void testReducedRowEchelonForm(final Matrix matrix, final Matrix expectedRREF)
      throws NonIntegerDivisionException {
    assertEquals(expectedRREF, matrix.getReducedForEchelonForm());
  }

  static Stream<Arguments> matrixWithRREFProvider() {
    return Stream.of(
        Arguments.of(
            new Matrix(
                new Matrix.Row(0, 0, 0, 0, 1, 1, 3),
                new Matrix.Row(0, 1, 0, 0, 0, 1, 5),
                new Matrix.Row(0, 0, 1, 1, 1, 0, 4),
                new Matrix.Row(1, 1, 0, 1, 0, 0, 7)),
            new Matrix(
                new Matrix.Row(1, 0, 0, 1, 0, -1, 2),
                new Matrix.Row(0, 1, 0, 0, 0, 1, 5),
                new Matrix.Row(0, 0, 1, 1, 0, -1, 1),
                new Matrix.Row(0, 0, 0, 0, 1, 1, 3))),
        Arguments.of(
            new Matrix(
                new Matrix.Row(1, 0, 1, 1, 0, 7),
                new Matrix.Row(0, 0, 0, 1, 1, 5),
                new Matrix.Row(1, 1, 0, 1, 1, 12),
                new Matrix.Row(1, 1, 0, 0, 1, 7),
                new Matrix.Row(1, 0, 1, 0, 1, 2)),
            new Matrix(
                new Matrix.Row(1, 0, 1, 0, 0, 2),
                new Matrix.Row(0, 1, -1, 0, 0, 5),
                new Matrix.Row(0, 0, 0, 1, 0, 5),
                new Matrix.Row(0, 0, 0, 0, 1, 0),
                new Matrix.Row(0, 0, 0, 0, 0, 0))),
        Arguments.of(
            new Matrix(
                new Matrix.Row(1, 1, 1, 0, 10),
                new Matrix.Row(1, 0, 1, 1, 11),
                new Matrix.Row(1, 0, 1, 1, 11),
                new Matrix.Row(1, 1, 0, 0, 5),
                new Matrix.Row(1, 1, 1, 0, 10),
                new Matrix.Row(0, 0, 1, 0, 5)),
            new Matrix(
                new Matrix.Row(1, 0, 0, 1, 6),
                new Matrix.Row(0, 1, 0, -1, -1),
                new Matrix.Row(0, 0, 1, 0, 5),
                new Matrix.Row(0, 0, 0, 0, 0),
                new Matrix.Row(0, 0, 0, 0, 0),
                new Matrix.Row(0, 0, 0, 0, 0))));
  }
}
