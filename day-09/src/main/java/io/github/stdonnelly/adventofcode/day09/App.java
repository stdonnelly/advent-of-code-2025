package io.github.stdonnelly.adventofcode.day09;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day09.loader.Point2dLoader;
import io.github.stdonnelly.adventofcode.day09.model.Point2d;
import java.io.IOException;
import java.util.List;

/** Day 09 solver */
public class App {
  private static final String IN_FILE_NAME = "input.txt";

  public static void main(String[] args) {
    final InputLoader<Point2d> inputLoader = new Point2dLoader(IN_FILE_NAME);

    try {
      final List<Point2d> input = inputLoader.load();

      final var part1Answer = part1(input);
      System.out.println("Part 1: " + part1Answer);

      final var part2Answer = part2(input);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  static long part1(final List<Point2d> input) {
    return -1;
  }

  static long part2(final List<Point2d> input) {
    return -1;
  }
}
