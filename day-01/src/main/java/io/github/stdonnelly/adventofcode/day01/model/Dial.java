package io.github.stdonnelly.adventofcode.day01.model;

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

  /** Start the dial at 50 */
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
    this.moveDialWithoutWrap(instruction);
    this.normalize();
  }

  /**
   * Move the dial according to the instruction, but do not apply the modulo
   *
   * @param instruction How to move the dial
   */
  public void moveDialWithoutWrap(Instruction instruction) {
    this.state = instruction.applyAsInt(state);
  }

  /**
   * Normalize the dial by applying modulo 100
   *
   * @return The number of times zero was encountered (not including state before normalization)
   */
  public int normalize() {
    final int quotient = Math.floorDiv(this.state, BASE);
    this.state -= quotient * BASE;

    return Math.abs(quotient);
  }
}
