package io.github.stdonnelly.adventofcode;

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

        final List<Instruction> instructions = inputLoader.load();
        final int zeroCount = countZerosDuringExecution(dial, instructions);

        System.out.println("Count of zeros: " + zeroCount);
    }

    private static int countZerosDuringExecution(final Dial dial, final List<Instruction> instructions){
        throw new UnsupportedOperationException("TODO");
    };
}
