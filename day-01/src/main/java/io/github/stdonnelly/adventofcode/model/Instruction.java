package io.github.stdonnelly.adventofcode.model;

/**
 * Represents an instruction for the safe
 * 
 * @param direction Left (less) or Right (more)
 * @param distance How far to go in the given direction
 */
public record Instruction(Direction direction, int distance) {
    public static Instruction parse(String input) throws IllegalArgumentException {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Moves the dial according to this instruction
     * 
     * @param dial The current state of the dial
     * @return The state of the dial after changing
     */
    int applyToDial(int dial) {
        throw new UnsupportedOperationException("TODO");
    }
}
