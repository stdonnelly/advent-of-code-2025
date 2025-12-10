package io.github.stdonnelly.adventofcode.day01.model;

/**
 * Represents a direction (left or right)
 */
public enum Direction {
    /**
     * Left - decrease the number
     */
    Left(-1),

    /**
     * Right - increase the number
     */
    Right(1);

    private final int numericDirection;

    private Direction(int numericDirection) {
        this.numericDirection = numericDirection;
    }

    /**
     * Parse the input letter, L or R, as a direction
     * 
     * @param directionIndicator 'L' or 'R'
     * @return The indicated direction. Left for 'L' or Right for 'R'
     * @throws IllegalArgumentException If the input is not 'L' or 'R'
     */
    public static Direction parse(char directionIndicator) throws IllegalArgumentException {
        return switch (directionIndicator) {
            case 'L' -> Left;
            case 'R' -> Right;
            default -> throw new IllegalArgumentException("Unrecognized direction: '" + directionIndicator + "'");
        };
    }

    /**
     * Get the numeric direction
     * 
     * @return -1 for {@link Left} or 1 for {@link Right}
     */
    public int getNumericDirection() {
        return this.numericDirection;
    }
}
