package io.github.stdonnelly.adventofcode.day10;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day10.loader.MachineDescriptionLoader;
import io.github.stdonnelly.adventofcode.day10.model.MachineDescription;
import io.github.stdonnelly.adventofcode.day10.service.MinimumButtonCounter;
import java.io.IOException;
import java.util.List;

/** Day 10 solver */
public class App {
  private static final String IN_FILE_NAME = "input.txt";

  public static void main(String[] args) {
    final InputLoader<MachineDescription> inputLoader = new MachineDescriptionLoader(IN_FILE_NAME);

    try {
      final List<MachineDescription> input = inputLoader.load();

      final var part1Answer = part1(input);
      System.out.println("Part 1: " + part1Answer);

      final var part2Answer = part2(input);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  static long part1(final List<MachineDescription> input) {
    MinimumButtonCounter minimumButtonCounter = new MinimumButtonCounter();
    return input.stream().mapToLong(minimumButtonCounter::getMinimumButtonPresses).sum();
  }

  static long part2(final List<MachineDescription> input) {
    return -1;
  }
}
