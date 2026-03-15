package io.github.stdonnelly.adventofcode.day12;

import io.github.stdonnelly.adventofcode.day12.loader.ProblemInputLoader;
import io.github.stdonnelly.adventofcode.day12.model.ProblemInput;
import io.github.stdonnelly.adventofcode.day12.service.PresentSizeFilterOptimistic;
import io.github.stdonnelly.adventofcode.day12.service.PresentSizeFilterPessimistic;
import java.io.IOException;
import java.text.ParseException;

/** Day 12 solver */
public class App {
  private static final String IN_FILE_NAME = "input.txt";

  public static void main(String[] args) {
    final ProblemInputLoader inputLoader = new ProblemInputLoader(IN_FILE_NAME);

    try {
      final ProblemInput input = inputLoader.load();

      final var part1Answer = part1(input);
      System.out.println("Part 1: " + part1Answer);
    } catch (IOException | ParseException e) {
      System.err.println(e);
    }
  }

  static long part1(final ProblemInput input) {
    // There is definitely a more thorough way to do this, but, for the puzzle input, the optimistic
    // and pessimistic solutions yield the same result. I was planning on narrowing it down using
    // these filters and writing code for the remaining regions, but there are none.
    final PresentSizeFilterOptimistic optimisticFilter =
        new PresentSizeFilterOptimistic(input.presentShapes());
    final PresentSizeFilterPessimistic pessimisticFilter = new PresentSizeFilterPessimistic();
    final long optimisticCount = input.regions().stream().filter(optimisticFilter).count();
    final long pessimisticCount = input.regions().stream().filter(pessimisticFilter).count();

    if (optimisticCount == pessimisticCount) {
      return optimisticCount;
    }

    throw new ArithmeticException(
        "Unable to narrow down the possibilities beyond the range ["
            + optimisticCount
            + ","
            + pessimisticCount
            + "]");
  }

  // Like with previous Advents of Code, part 2 of the last day is simply to complete everything
  // else.
}
