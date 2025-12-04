package io.github.stdonnelly.adventofcode.model;

public class Dial {
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
        throw new UnsupportedOperationException("TODO");
    }
}
