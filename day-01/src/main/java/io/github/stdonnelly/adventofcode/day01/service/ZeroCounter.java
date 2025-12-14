package io.github.stdonnelly.adventofcode.day01.service;

import io.github.stdonnelly.adventofcode.day01.model.Dial;
import io.github.stdonnelly.adventofcode.day01.model.Direction;
import io.github.stdonnelly.adventofcode.day01.model.Instruction;
import java.util.List;

/** Counts zeros with the given {@link Dial} and {@link List} of {@link Instruction} */
public class ZeroCounter {

  /**
   * Count the number of times the dial hits 0 while executing the instructions
   *
   * @param dial The dial to work on. Will be modified.
   * @param instructions The instructions to apply to the dial
   * @return The number of times the dial ends on zero
   */
  public int countZerosDuringExecution(final Dial dial, final List<Instruction> instructions) {
    int zeroCount = 0;
    for (final Instruction instruction : instructions) {
      dial.moveDial(instruction);

      if (dial.getState() == 0) {
        zeroCount++;
      }
    }

    return zeroCount;
  }

  /**
   * Count the number of times the dial hits 0 while executing the instructions
   *
   * <p>This will count all zeros, not just when it ends on zero
   *
   * @param dial The dial to work on. Will be modified.
   * @param instructions The instructions to apply to the dial
   * @return The number of times the dial hits zero
   */
  public int countAllZerosDuringExecution(final Dial dial, final List<Instruction> instructions) {
    int zeroCount = 0;
    for (final Instruction instruction : instructions) {
      // Special case: if we start on zero, ignore the rollover because the zero was
      // already counted
      if ((dial.getState() == 0) && Direction.Left.equals(instruction.direction())) {
        zeroCount--;
      }

      dial.moveDialWithoutWrap(instruction);
      zeroCount += dial.normalize();

      // Special case: if we end on zero while moving left, there is no rollover, but
      // the zero should still be counted
      // Count if it starts on zero
      if ((dial.getState() == 0) && Direction.Left.equals(instruction.direction())) {
        zeroCount++;
      }
    }

    return zeroCount;
  }
}
