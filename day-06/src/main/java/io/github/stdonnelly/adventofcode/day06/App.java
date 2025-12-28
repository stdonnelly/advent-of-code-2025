package io.github.stdonnelly.adventofcode.day06;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day06.loader.LongTableLoader;
import io.github.stdonnelly.adventofcode.day06.loader.part2.MathProblemLoader;
import io.github.stdonnelly.adventofcode.day06.model.LongTable;
import io.github.stdonnelly.adventofcode.day06.model.Operation;
import io.github.stdonnelly.adventofcode.day06.model.part2.MathProblem;
import io.github.stdonnelly.adventofcode.service.MathService;
import java.io.IOException;
import java.util.List;

/** Day 06 solver */
public class App {
  private static final String IN_FILE_NAME = "input.txt";

  public static void main(String[] args) {
    final LongTableLoader inputLoader = new LongTableLoader(IN_FILE_NAME);
    final InputLoader<MathProblem> mathProblemLoader = new MathProblemLoader(IN_FILE_NAME);

    try {
      final LongTable input = inputLoader.loadAsLongTable();
      final List<Operation> operationList = inputLoader.getOperationList();

      final var part1Answer = part1(input, operationList);
      System.out.println("Part 1: " + part1Answer);

      final List<MathProblem> mathProblemList = mathProblemLoader.load();

      final var part2Answer = part2(mathProblemList);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  static long part1(final LongTable input, List<Operation> operationList) {
    final MathService mathService = new MathService();
    return mathService.performAllOperationsAndSum(operationList, input.pivot());
  }

  static long part2(final List<MathProblem> mathProblemList) {
    return mathProblemList.stream().mapToLong(MathProblem::compute).sum();
  }
}
