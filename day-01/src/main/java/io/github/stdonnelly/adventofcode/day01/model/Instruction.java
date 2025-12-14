package io.github.stdonnelly.adventofcode.day01.model;

import java.util.function.IntUnaryOperator;

/**
 * Represents an instruction for the safe
 *
 * @param direction Left (less) or Right (more)
 * @param distance How far to go in the given direction
 */
public record Instruction(Direction direction, int distance) implements IntUnaryOperator {
  /**
   * Parse an input object as
   *
   * @param input
   * @return
   * @throws IllegalArgumentException
   */
  public static Instruction parse(String input) throws IllegalArgumentException {
    if (input.length() < 2) {
      throw new IllegalArgumentException(
          "Instruction too short, at least 2 characters are expected");
    }

    final Direction direction = Direction.parse(input.charAt(0));
    final int distance = Integer.parseInt(input.substring(1));
    return new Instruction(direction, distance);
  }

  /**
   * Moves the dial according to this instruction.
   *
   * <p>This function does not guarantee any maximum or minimum position. The caller will need to
   * perform modulo.
   *
   * @param dial The current state of the dial
   * @return The state of the dial after changing
   */
  @Override
  public int applyAsInt(int dial) {
    final int signedDistance = distance * direction.getNumericDirection();
    return dial + signedDistance;
  }
}
