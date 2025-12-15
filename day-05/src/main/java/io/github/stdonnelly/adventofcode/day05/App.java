package io.github.stdonnelly.adventofcode.day05;

import io.github.stdonnelly.adventofcode.day05.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day05.model.FreshRange;
import java.io.IOException;
import java.util.List;

/** Day 05 solver */
public class App {
  private static final String IN_FILE_NAME = "input.txt";

  public static void main(String[] args) {
    final InputLoader inputLoader = new InputLoader(IN_FILE_NAME);

    try {
      final List<FreshRange> input = inputLoader.load();

      final var part1Answer = part1(input);
      System.out.println("Part 1: " + part1Answer);

      final var part2Answer = part2(input);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  static int part1(final List<FreshRange> input) {
    return -1;
  }

  static int part2(final List<FreshRange> input) {
    return -1;
  }
}
