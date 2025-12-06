package io.github.stdonnelly.adventofcode.model;

public class Dial {
    // The number to apply a modulo against
    private static final int BASE = 100;
    // The number the dial is pointing at
    private int state;

    /**
     * Start the dial at a specific state
     * 
     * @param state The number the dial should point at
     */
    public Dial(int state) {
        this.state = state;
    }

    /**
     * Start the dial at 50
     */
    public Dial() {
        this(50);
    }

    /**
     * Get the number the dial is currently pointing to
     * 
     * @return The number the dial is currently pointing to
     */
    public int getState() {
        return state;
    }

    /**
     * Move the dial according to the instruction
     * 
     * @param instruction How to move the dial
     */
    public void moveDial(Instruction instruction) {
        // The Instruction will not return the dial as a normalized (between 0 and 99) state
        final int nonNormalizedState = instruction.applyAsInt(state);
        state = Math.floorMod(nonNormalizedState, BASE);
    }
}
