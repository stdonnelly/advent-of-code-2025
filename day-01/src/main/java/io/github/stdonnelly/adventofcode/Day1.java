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
        final InputLoader inputLoader = new InputLoader();

        try {
            final List<Instruction> instructions = inputLoader.load();
            // System.out.println("Instructions");
            // System.out.println(instructions);

            final int zeroCount = countZerosDuringExecution(new Dial(), instructions);
            System.out.println("Part 1: Count of zeros: " + zeroCount);

            final int allZerosCount = countAllZerosDuringExecution(new Dial(), instructions);
            System.out.println("Part 2: Count of zeros: " + allZerosCount);
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    /**
     * Count the number of times the dial hits 0 while executing the instructions
     * 
     * @param dial The dial to work on. Will be modified.
     * @param instructions The instructions to apply to the dial
     * @return The number of times the dial ends on zero
     */
    private static int countZerosDuringExecution(final Dial dial, final List<Instruction> instructions) {
        int zeroCount = 0;
        for (final Instruction instruction : instructions) {
            dial.moveDial(instruction);

            // System.out.println("Instruction: " + instruction + ", + state: " + dial.getState());

            if (dial.getState() == 0) {
                zeroCount++;
            }
        }

        return zeroCount;
    };

    /**
     * Count the number of times the dial hits 0 while executing the instructions
     * 
     * This will count all zeros, not just when it ends on zero
     * 
     * @param dial The dial to work on. Will be modified.
     * @param instructions The instructions to apply to the dial
     * @return The number of times the dial hits zero
     */
    private static int countAllZerosDuringExecution(final Dial dial, final List<Instruction> instructions) {
        throw new UnsupportedOperationException("TODO");
    }
}
