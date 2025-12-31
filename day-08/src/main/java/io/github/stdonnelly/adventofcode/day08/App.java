package io.github.stdonnelly.adventofcode.day08;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day08.loader.Point3dLoader;
import io.github.stdonnelly.adventofcode.day08.model.Point3d;
import java.io.IOException;
import java.util.List;

/** Day 08 solver */
public class App {
  private static final String IN_FILE_NAME = "input.txt";

  public static void main(String[] args) {
    final InputLoader<Point3d> inputLoader = new Point3dLoader(IN_FILE_NAME);

    try {
      final List<Point3d> input = inputLoader.load();

      final var part1Answer = part1(input);
      System.out.println("Part 1: " + part1Answer);

      final var part2Answer = part2(input);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  static long part1(final List<Point3d> input) {
    return -1;
  }

  static long part2(final List<Point3d> input) {
    return -1;
  }
}
