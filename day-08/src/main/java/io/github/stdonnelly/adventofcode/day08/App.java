package io.github.stdonnelly.adventofcode.day08;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day08.loader.Point3dLoader;
import io.github.stdonnelly.adventofcode.day08.model.Point3d;
import io.github.stdonnelly.adventofcode.day08.service.CircuitFactory;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/** Day 08 solver */
public class App {
  private static final String IN_FILE_NAME = "input.txt";
  private static final int CONNECTION_COUNT = 1000;

  public static void main(String[] args) {
    final InputLoader<Point3d> inputLoader = new Point3dLoader(IN_FILE_NAME);

    try {
      final List<Point3d> input = inputLoader.load();

      final var part1Answer = part1(input, CONNECTION_COUNT);
      System.out.println("Part 1: " + part1Answer);

      final var part2Answer = part2(input);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  static long part1(final List<Point3d> input, final int connectionCount) {
    CircuitFactory circuitFactory = new CircuitFactory(input);
    Set<Set<Point3d>> circuits = circuitFactory.getCircuits(connectionCount);

    return circuits.stream()
        .map(Set::size)
        .sorted(Comparator.reverseOrder())
        .limit(3)
        .mapToLong(Long::valueOf)
        .reduce(1L, (a, b) -> a * b);
  }

  static long part2(final List<Point3d> input) {
    return -1;
  }
}
