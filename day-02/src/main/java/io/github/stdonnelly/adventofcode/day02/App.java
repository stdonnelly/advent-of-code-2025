package io.github.stdonnelly.adventofcode.day02;

import io.github.stdonnelly.adventofcode.day02.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day02.model.IdRange;
import io.github.stdonnelly.adventofcode.day02.service.InvalidIdSpliterator;
import io.github.stdonnelly.adventofcode.day02.service.InvalidIdSpliteratorPart2;
import io.github.stdonnelly.adventofcode.day02.writer.ResultWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;
import org.yaml.snakeyaml.error.YAMLException;

/// Add together all *invalid* IDs in a given set of ranges
///
/// Definition of invalid: Exactly a sequence of digits that are repeated twice.
/// E.g. `123123` or `4545`, but not `1123123`.
///
/// Notes:
///
/// - Ranges are given as `start-end` *inclusively*. E.g. `11-22`.
/// - Leading zeros don't count. `0101` is **not** considered invalid.
/// - Assumption: There are no overlaps.
///   Checked by
// [AppTest.noOverlapsTest()][io.github.stdonnelly.adventofcode.day02.AppTest#noOverlapsTest]
public class App {
  private static final String IN_FILE_NAME = "input.txt";

  public static void main(String[] args) {
    final InputLoader inputLoader = new InputLoader(IN_FILE_NAME);

    try {
      final List<IdRange> input = inputLoader.load();

      final var part1Answer = part1(input);
      System.out.println("Part 1: " + part1Answer);

      final var part2Answer = part2(input);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  static long part1(final List<IdRange> input) {
    final List<Long> invalidIds = new ArrayList<>();

    final long invalidIdsSum =
        input.stream()
            .map(InvalidIdSpliterator::new)
            .flatMapToLong(spliterator -> StreamSupport.longStream(spliterator, false))
            .peek(invalidIds::add)
            .sum();

    // Try to write the output to a file
    try (ResultWriter resultWriter = new ResultWriter(Paths.get("output.yaml"))) {
      resultWriter.write(input, invalidIds);
    } catch (IOException | YAMLException e) {
      System.err.println("Error writing the results");
      e.printStackTrace();
    }

    return invalidIdsSum;
  }

  static long part2(final List<IdRange> input) {
    final List<Long> invalidIds = new ArrayList<>();

    final long invalidIdsSum =
        input.stream()
            .map(InvalidIdSpliteratorPart2::new)
            .flatMapToLong(spliterator -> StreamSupport.longStream(spliterator, false))
            .peek(invalidIds::add)
            .sum();

    // Try to write the output to a file
    try (ResultWriter resultWriter = new ResultWriter(Paths.get("output_part2.yaml"))) {
      resultWriter.write(input, invalidIds);
    } catch (IOException | YAMLException e) {
      System.err.println("Error writing the results");
      e.printStackTrace();
    }

    return invalidIdsSum;
  }
}
