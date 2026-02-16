package io.github.stdonnelly.adventofcode.day12.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RegionTest {
  @ParameterizedTest
  @MethodSource("regionProvider")
  void testParse(String parseInput, Region expected) throws ParseException {
    assertEquals(expected, Region.parse(parseInput));
  }

  static Stream<Arguments> regionProvider() {
    return Stream.of(
        arguments("4x4: 0 0 0 0 2 0", new Region(4, 4, List.of(0, 0, 0, 0, 2, 0))),
        arguments("12x5: 1 0 1 0 2 2", new Region(12, 5, List.of(1, 0, 1, 0, 2, 2))),
        arguments("12x5: 1 0 1 0 3 2", new Region(12, 5, List.of(1, 0, 1, 0, 3, 2))));
  }
}
