package io.github.stdonnelly.adventofcode.model;

/**
 * Represents an instruction for the safe
 * 
 * @param direction Left (less) or Right (more)
 * @param distance How far to go in the given direction
 */
public record Instruction(Direction direction, int distance) {
    /**
     * Parse an input object as 
     * @param input
     * @return
     * @throws IllegalArgumentException
     */
    public static Instruction parse(String input) throws IllegalArgumentException {
        if (input.length() < 2) {
            throw new IllegalArgumentException("Instruction too short, at least 2 characters are expected");
        }

        final Direction direction = Direction.parse(input.charAt(0));
        final int distance = Integer.parseInt(input.substring(1));
        return new Instruction(direction, distance);
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
