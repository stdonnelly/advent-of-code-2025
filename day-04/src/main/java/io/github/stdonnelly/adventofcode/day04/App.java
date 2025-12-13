package io.github.stdonnelly.adventofcode.day04;

import java.io.IOException;

import io.github.stdonnelly.adventofcode.day04.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day04.model.FloorMap;
import io.github.stdonnelly.adventofcode.day04.service.FloorService;
import io.github.stdonnelly.adventofcode.day04.service.FloorServicePart2;

/**
 * Day 04 solver
 *
 */
public class App {
    private static final String IN_FILE_NAME = "input.txt";

    public static void main(String[] args) {
        final InputLoader inputLoader = new InputLoader(IN_FILE_NAME);

        try {
            final FloorMap input = inputLoader.load();

            final var part1Answer = part1(input);
            System.out.println("Part 1: " + part1Answer);

            final var part2Answer = part2(input);
            System.out.println("Part 2: " + part2Answer);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    static int part1(final FloorMap input) {
        final FloorService floorService = new FloorService();

        return floorService.countMoveablePaper(input);
    }

    static int part2(final FloorMap input) {
        final FloorServicePart2 floorService = new FloorServicePart2();

        return floorService.countMoveablePaper(input);
    }
}
