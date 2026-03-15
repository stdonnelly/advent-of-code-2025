package io.github.stdonnelly.adventofcode.day12.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.text.ParseException;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PresentShapeTest {
  @ParameterizedTest
  @MethodSource("presentShapeProvider")
  void testParse(String parseInput, PresentShape expected) throws ParseException {
    assertEquals(expected, PresentShape.parse(parseInput));
  }

  static Stream<Arguments> presentShapeProvider() {
    return Stream.of(
        arguments(
            """
            ###
            ##.
            ##.
            """,
            new PresentShape(
                new boolean[][] {
                  new boolean[] {true, true, true},
                  new boolean[] {true, true, false},
                  new boolean[] {true, true, false}
                })),
        arguments(
            """
            ###
            ##.
            .##
            """,
            new PresentShape(
                new boolean[][] {
                  new boolean[] {true, true, true},
                  new boolean[] {true, true, false},
                  new boolean[] {false, true, true}
                })),
        arguments(
            """
            .##
            ###
            ##.
            """,
            new PresentShape(
                new boolean[][] {
                  new boolean[] {false, true, true},
                  new boolean[] {true, true, true},
                  new boolean[] {true, true, false}
                })),
        arguments(
            """
            ##.
            ###
            ##.
            """,
            new PresentShape(
                new boolean[][] {
                  new boolean[] {true, true, false},
                  new boolean[] {true, true, true},
                  new boolean[] {true, true, false}
                })),
        arguments(
            """
            ###
            #..
            ###
            """,
            new PresentShape(
                new boolean[][] {
                  new boolean[] {true, true, true},
                  new boolean[] {true, false, false},
                  new boolean[] {true, true, true}
                })),
        arguments(
            """
            ###
            .#.
            ###
            """,
            new PresentShape(
                new boolean[][] {
                  new boolean[] {true, true, true},
                  new boolean[] {false, true, false},
                  new boolean[] {true, true, true}
                })));
  }
}
