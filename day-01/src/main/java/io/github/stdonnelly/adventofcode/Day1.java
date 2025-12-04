package io.github.stdonnelly.adventofcode;

import java.io.IOException;
import java.util.List;

import io.github.stdonnelly.adventofcode.loader.InputLoader;
import io.github.stdonnelly.adventofcode.model.Dial;
import io.github.stdonnelly.adventofcode.model.Instruction;

/**
 * First day of Advent of Code 2025
 */
public class Day1 {
    public static void main(String[] args) {
        final Dial dial = new Dial();
        final InputLoader inputLoader = new InputLoader();

        try {
            final List<Instruction> instructions = inputLoader.load();
            System.out.println("Instructions");
            System.out.println(instructions);

            final int zeroCount = countZerosDuringExecution(dial, instructions);

            System.out.println("Count of zeros: " + zeroCount);
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    private static int countZerosDuringExecution(final Dial dial, final List<Instruction> instructions) {
        throw new UnsupportedOperationException("TODO");
    };
}
