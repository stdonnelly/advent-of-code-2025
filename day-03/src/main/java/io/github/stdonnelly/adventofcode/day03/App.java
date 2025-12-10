package io.github.stdonnelly.adventofcode.day03;

import java.io.IOException;
import java.util.List;

import io.github.stdonnelly.adventofcode.day03.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day03.model.BatteryBank;

/**
 * Day 03 solver
 *
 */
public class App
{
    private static final String IN_FILE_NAME = "input.txt";

    public static void main(String[] args)
    {
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

    static int part1(final List<BatteryBank> input) {
        return -1;
    }

    static int part2(final List<BatteryBank> input) {
        return -1;
    }
}
