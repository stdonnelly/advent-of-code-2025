package io.github.stdonnelly.adventofcode;

import java.io.IOException;
import java.util.List;

import io.github.stdonnelly.adventofcode.loader.InputLoader;
import io.github.stdonnelly.adventofcode.model.Dial;
import io.github.stdonnelly.adventofcode.model.Instruction;
import io.github.stdonnelly.adventofcode.service.ZeroCounter;

/**
 * First day of Advent of Code 2025
 */
public class Day1 {
    public static void main(String[] args) {
        final InputLoader inputLoader = new InputLoader("input.txt");
        final ZeroCounter zeroCounter = new ZeroCounter();

        try {
            final List<Instruction> instructions = inputLoader.load();
            // System.out.println("Instructions");
            // System.out.println(instructions);

            final int zeroCount = zeroCounter.countZerosDuringExecution(new Dial(), instructions);
            System.out.println("Part 1: Count of zeros: " + zeroCount);

            final int allZerosCount = zeroCounter.countAllZerosDuringExecution(new Dial(), instructions);
            System.out.println("Part 2: Count of zeros: " + allZerosCount);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
