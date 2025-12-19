package io.github.stdonnelly.adventofcode.common.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class InclusiveRangeTest {
  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          1-2,1,2
          123-456,123,456
          1-1,1,1
          """)
  void testParse_valid(String str, long expectedStart, long expectedEnd) {
    assertEquals(InclusiveRange.parse(str), new InclusiveRange(expectedStart, expectedEnd));
  }

  @ParameterizedTest
  @ValueSource(strings = {"12", "abcd", "123&456", "-1"})
  void testParse_invalidSchema(String str) {
    IllegalArgumentException actualException =
        assertThrows(
            IllegalArgumentException.class,
            () -> InclusiveRange.parse(str),
            () -> "It should not be possible to parse '" + str + "'");
    assertEquals("Unable to parse input '" + str + "'", actualException.getMessage());
  }

  @ParameterizedTest
  @ValueSource(strings = {"2-1", "456-123"})
  void testParse_invalidStartAfterEnd(String str) {
    IllegalArgumentException actualException =
        assertThrows(
            IllegalArgumentException.class,
            () -> InclusiveRange.parse(str),
            () -> "It should not be possible to parse '" + str + "'");
    assertEquals("start must be <= end", actualException.getMessage());
  }

  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          1-3,2-4,1-4
          1-2,2-4,1-4
          1-2,3-4,1-4
          2-4,1-3,1-4
          2-4,1-2,1-4
          3-4,1-2,1-4
          1-4,2-3,1-4
          2-3,1-4,1-4
          1-2,4-5,
          4-5,1-2,
          """)
  void testMergeWith(String rangeStr1, String rangeStr2, String expectedRangeStr) {
    InclusiveRange range1 = InclusiveRange.parse(rangeStr1);
    InclusiveRange range2 = InclusiveRange.parse(rangeStr2);
    InclusiveRange expectedRange =
        expectedRangeStr == null ? null : InclusiveRange.parse(expectedRangeStr);

    assertEquals(
        expectedRange,
        range1.mergeWith(range2),
        "Merging '" + rangeStr1 + "' with '" + rangeStr2 + "'");
  }
}
