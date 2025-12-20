package io.github.stdonnelly.adventofcode.day06;

import io.github.stdonnelly.adventofcode.day06.loader.LongTableLoader;
import io.github.stdonnelly.adventofcode.day06.model.LongTable;
import java.io.IOException;

/** Day 06 solver */
public class App {
  private static final String IN_FILE_NAME = "input.txt";

  public static void main(String[] args) {
    final LongTableLoader inputLoader = new LongTableLoader(IN_FILE_NAME);

    try {
      final LongTable input = inputLoader.loadAsLongTable();

      final var part1Answer = part1(input);
      System.out.println("Part 1: " + part1Answer);

      final var part2Answer = part2(input);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  static int part1(final LongTable input) {
    return -1;
  }

  static int part2(final LongTable input) {
    return -1;
  }
}
