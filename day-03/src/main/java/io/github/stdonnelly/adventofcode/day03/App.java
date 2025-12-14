package io.github.stdonnelly.adventofcode.day03;

import io.github.stdonnelly.adventofcode.day03.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day03.model.BatteryBank;
import io.github.stdonnelly.adventofcode.day03.service.Max12Calculator;
import io.github.stdonnelly.adventofcode.day03.service.MaxPairCalculator;
import java.io.IOException;
import java.util.List;

/** Day 03 solver */
public class App {
  private static final String IN_FILE_NAME = "input.txt";

  public static void main(String[] args) {
    final InputLoader inputLoader = new InputLoader(IN_FILE_NAME);

    try {
      final List<BatteryBank> input = inputLoader.load();

      final var part1Answer = part1(input);
      System.out.println("Part 1: " + part1Answer);

      final var part2Answer = part2(input);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  public static int part1(final List<BatteryBank> input) {
    MaxPairCalculator maxPairCalculator = new MaxPairCalculator();

    return input.stream().mapToInt(maxPairCalculator).sum();
  }

  public static long part2(final List<BatteryBank> input) {
    Max12Calculator max12Calculator = new Max12Calculator();

    return input.stream().mapToLong(max12Calculator).sum();
  }
}
