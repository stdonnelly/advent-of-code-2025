package io.github.stdonnelly.adventofcode.service;

import java.util.List;

import io.github.stdonnelly.adventofcode.model.Dial;
import io.github.stdonnelly.adventofcode.model.Instruction;

/**
 * Counts zeros with the given {@link Dial} and {@link List} of
 * {@link Instruction}
 */
public class ZeroCounter {

    /**
     * Count the number of times the dial hits 0 while executing the instructions
     * 
     * @param dial         The dial to work on. Will be modified.
     * @param instructions The instructions to apply to the dial
     * @return The number of times the dial ends on zero
     */
    public int countZerosDuringExecution(final Dial dial, final List<Instruction> instructions) {
        int zeroCount = 0;
        for (final Instruction instruction : instructions) {
            dial.moveDial(instruction);

            // System.out.println("Instruction: " + instruction + ", + state: " +
            // dial.getState());

            if (dial.getState() == 0) {
                zeroCount++;
            }
        }

        return zeroCount;
    }

    /**
     * Count the number of times the dial hits 0 while executing the instructions
     * 
     * This will count all zeros, not just when it ends on zero
     * 
     * @param dial         The dial to work on. Will be modified.
     * @param instructions The instructions to apply to the dial
     * @return The number of times the dial hits zero
     */
    public int countAllZerosDuringExecution(final Dial dial, final List<Instruction> instructions) {
        throw new UnsupportedOperationException("TODO");
    }

}
