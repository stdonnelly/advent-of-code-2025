package io.github.stdonnelly.adventofcode.day12;

import io.github.stdonnelly.adventofcode.day12.loader.ProblemInputLoader;
import io.github.stdonnelly.adventofcode.day12.model.ProblemInput;
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

      final var part2Answer = part2(input);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException | ParseException e) {
      System.err.println(e);
    }
  }

  static long part1(final ProblemInput input) {
    return -1;
  }

  static long part2(final ProblemInput input) {
    return -1;
  }
}
