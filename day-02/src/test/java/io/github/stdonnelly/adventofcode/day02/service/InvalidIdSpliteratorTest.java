package io.github.stdonnelly.adventofcode.day02.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import io.github.stdonnelly.adventofcode.common.model.InclusiveRange;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class InvalidIdSpliteratorTest {
  // An iterator for the tests where range doesn't matter
  private final InvalidIdSpliterator invalidIdSpliterator120 =
      new InvalidIdSpliterator(new InclusiveRange(1, 20));

  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          11-22,11:22
          95-115,99
          998-1012,1010
          1188511880-1188511890,1188511885
          222220-222224,222222
          1698522-1698528,''
          446443-446449,446446
          38593856-38593862,38593859
          2121212118-2121212124,''
          """)
  void invalidIdSpliterationTest(String rangeStr, String invalidIdsStr) {
    final InvalidIdSpliterator invalidIdSpliterator =
        new InvalidIdSpliterator(InclusiveRange.parse(rangeStr.trim()));

    List<Long> expectedInvalidIds;
    if (invalidIdsStr == null || invalidIdsStr.isBlank()) {
      expectedInvalidIds = List.of();
    } else {
      expectedInvalidIds = Arrays.stream(invalidIdsStr.split(":")).map(Long::parseLong).toList();
    }

    for (long expectedInvalidId : expectedInvalidIds) {
      // On the outside, assert that the spliterator returns true
      assertTrue(
          invalidIdSpliterator.tryAdvance(
              // As the consumer, assert that the value returned is the expected value.
              (long l) ->
                  assertEquals(l, expectedInvalidId, (() -> "Expected ID " + expectedInvalidId))));
    }

    assertFalse(
        invalidIdSpliterator.tryAdvance(
            (long l) -> fail("There shouldn't be any more invalid IDs")));
  }

  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          0,0
          1,1
          9,1
          10,2
          99,2
          100,3
          """)
  void floorLogTest(final long number, final int expected) {
    assertEquals(
        expected,
        invalidIdSpliterator120.floorLog(number),
        () -> String.format("floorLog(%d)", number));
  }

  @ParameterizedTest
  @ValueSource(
      longs = {
        11L, 22L, 1010L, 222222L, 38593859L,
      })
  void isInvalidIdTest_true(long num) {
    assertTrue(
        invalidIdSpliterator120.isInvalidId(num), () -> num + " should be considered invalid");
  }

  @ParameterizedTest
  @ValueSource(longs = {1L, 10L, 21L, 222L, 1001L, 223222L, 1234L, 111L})
  void isInvalidIdTest_false(long num) {
    assertFalse(
        invalidIdSpliterator120.isInvalidId(num), () -> num + " should not be considered invalid");
  }
}
