package io.github.stdonnelly.adventofcode.day07;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day07.loader.ManifoldRowLoader;
import io.github.stdonnelly.adventofcode.day07.model.ManifoldRow;
import io.github.stdonnelly.adventofcode.day07.model.ManifoldSquare;
import io.github.stdonnelly.adventofcode.day07.service.BeamSplitCounter;
import io.github.stdonnelly.adventofcode.day07.writer.ManifoldRowWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/** Day 07 solver */
public class App {
  private static final String IN_FILE_NAME = "input.txt";

  public static void main(String[] args) {
    final InputLoader<ManifoldRow> inputLoader = new ManifoldRowLoader(IN_FILE_NAME);

    try {
      final List<ManifoldRow> input = inputLoader.load();

      final var part1Answer = part1(input);
      System.out.println("Part 1: " + part1Answer);

      final var part2Answer = part2(input);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  static int part1(final List<ManifoldRow> input) {
    // Cleanup and clone the input
    final List<ManifoldRow> inputCopy =
        input.stream()
            // Remove unnecessary rows
            .filter(Predicate.not(ManifoldRow::isBlank))
            // Create a mutable copy of everything
            .map(ManifoldRow::new)
            .toList();

    // Count the beam splits
    final BeamSplitCounter beamSplitCounter = new BeamSplitCounter();
    final int splits = beamSplitCounter.countSplits(inputCopy);
    try {
      final ManifoldRowWriter manifoldRowWriter = new ManifoldRowWriter(Paths.get("output.txt"));
      manifoldRowWriter.write(inputCopy);
    } catch (IOException e) {
      System.err.println("Error writing the output: " + e);
    }
    return splits;
  }

  static long part2(final List<ManifoldRow> input) {
    // Cleanup and clone the input
    final List<ManifoldRow> inputCopy =
        input.stream()
            // Remove unnecessary rows
            .filter(Predicate.not(ManifoldRow::isBlank))
            // Create a mutable copy of everything
            .map(ManifoldRow::new)
            .toList();

    // Count the beam splits
    final BeamSplitCounter beamSplitCounter = new BeamSplitCounter();
    beamSplitCounter.countSplits(inputCopy);

    final long totalBeamCount =
        inputCopy.getLast().manifoldSquareList().stream()
            .mapToLong(ManifoldSquare::getBeamCount)
            .sum();
    try {
      final ManifoldRowWriter manifoldRowWriter = new ManifoldRowWriter(Paths.get("output.txt"));
      manifoldRowWriter.write(inputCopy);
    } catch (IOException e) {
      System.err.println("Error writing the output: " + e);
    }

    return totalBeamCount;
  }
}
