package io.github.stdonnelly.adventofcode.common.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.common.model.InclusiveRange;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class InclusiveRangeMergingGathererTest {
  @Test
  void testGatherOnStream() {
    Stream<InclusiveRange> input =
        Stream.of("1-2", "2-3", "4-8", "10-14", "11-12", "16-17", "2-5", "18-18")
            .map(InclusiveRange::parse);
    List<InclusiveRange> expectedOutput =
        Stream.of("1-8", "10-14", "16-18").map(InclusiveRange::parse).toList();

    List<InclusiveRange> actualOutput =
        input.sorted().gather(new InclusiveRangeMergingGatherer()).toList();

    assertEquals(expectedOutput, actualOutput);
  }
}
