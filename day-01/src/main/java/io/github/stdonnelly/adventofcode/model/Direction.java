package io.github.stdonnelly.adventofcode.model;

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
     * Get the numeric direction
     * 
     * @return -1 for {@link Left} or 1 for {@link Right}
     */
    public int getNumericDirection() {
        return this.numericDirection;
    }
}
