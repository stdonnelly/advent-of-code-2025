package io.github.stdonnelly.adventofcode.day03.service;

import io.github.stdonnelly.adventofcode.day03.model.BatteryBank;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToLongFunction;

/// Calculates the maximum "joltage" from 12 batteries in the [BatteryBank]
///
/// This is probably easier to solve in a different way
public class Max12Calculator implements ToLongFunction<BatteryBank> {

  /// Find the joltage from the best list of 12 batteries in the bank
  @Override
  public long applyAsLong(BatteryBank value) {
    List<Integer> batteries = Arrays.stream(value.batteries()).boxed().toList();

    return findMaxNDigits(12, batteries);
  }

  /// Recursive function to find the maximum value for n of the batteries left
  ///
  /// @param numberToPick       The number of remaining batteries to pick
  /// @param remainingBatteries The list of batteries that can be picked from. This
  ///                           is a list instead of an array to be able to use
  ///                           [subList][List#subList(int, int)]
  private long findMaxNDigits(final int numberToPick, final List<Integer> remainingBatteries) {
    // Base case: No numbers to pick
    if (numberToPick <= 0) {
      return 0L;
    }

    // Recursive case
    final int otherNumbers = numberToPick - 1;

    // Start by finding the maximum digit we can use and the first index of that
    // digit
    int maxDigit = 0;
    int maxDigitIndex = -1;

    // Calculate this because we cannot pick a first digit if there aren't enough
    // digits left
    final int end = remainingBatteries.size() - otherNumbers;
    for (int i = 0; i < end; i++) {
      final int battery = remainingBatteries.get(i);

      // If this is the max, use this
      if (battery > maxDigit) {
        maxDigit = battery;
        maxDigitIndex = i;
      }
    }

    // Find the best remaining digits and return
    return (maxDigit * Math.powExact(10L, otherNumbers))
        + findMaxNDigits(
            otherNumbers, remainingBatteries.subList(maxDigitIndex + 1, remainingBatteries.size()));
  }
}
