package io.github.stdonnelly.adventofcode.day05;

import io.github.stdonnelly.adventofcode.day05.loader.TaskInputLoader;
import io.github.stdonnelly.adventofcode.day05.model.TaskInput;
import io.github.stdonnelly.adventofcode.day05.service.FreshIngredientCounter;
import java.io.IOException;

/** Day 05 solver */
public class App {
  private static final String IN_FILE_NAME = "input.txt";

  public static void main(String[] args) {
    final TaskInputLoader inputLoader = new TaskInputLoader(IN_FILE_NAME);

    try {
      final TaskInput input = inputLoader.load();

      final var part1Answer = part1(input);
      System.out.println("Part 1: " + part1Answer);

      final var part2Answer = part2(input);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  static long part1(final TaskInput input) {
    FreshIngredientCounter freshIngredientCounter =
        new FreshIngredientCounter(input.freshIngredientRanges());
    return freshIngredientCounter.freshCount(input.availableIngredients());
  }

  static long part2(final TaskInput input) {
    return -1;
  }
}
