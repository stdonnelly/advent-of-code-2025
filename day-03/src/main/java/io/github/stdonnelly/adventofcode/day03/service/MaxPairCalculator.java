package io.github.stdonnelly.adventofcode.day03.service;

import java.util.function.ToIntFunction;

import io.github.stdonnelly.adventofcode.day03.model.BatteryBank;

/// Calculates the maximum "joltage" from a pair of batteries in the
/// [BatteryBank]
///
/// The joltage is the first value concatenated with the second value in a pair
/// 
/// Initial thought: The maximum digit (not including the last one) should be in
/// the tens place. The next largest digit after the first instance of the
/// largest digit should be next.
public class MaxPairCalculator implements ToIntFunction<BatteryBank> {

    @Override
    public int applyAsInt(BatteryBank value) {
        int maxDigit1 = 0;
        int maxDigit2 = 0;

        // Use the length minus one because we cannot use the last battery as the first
        // digit.
        final int lengthMinusOne = value.batteries().length - 1;
        for (int i = 0; i < lengthMinusOne; i++) {
            final int battery = value.batteries()[i];

            if (battery > maxDigit1) {
                // If this is the largest so far, clear everything because this is the first
                // digit.
                maxDigit1 = battery;
                maxDigit2 = 0;
            } else if (battery > maxDigit2) {
                // If this the second-best, it can be the second digit.
                maxDigit2 = battery;
            }
        }

        // Finally, because we excluded the last digit, check it
        if (value.batteries()[lengthMinusOne] > maxDigit2) {
            maxDigit2 = value.batteries()[lengthMinusOne];
        }

        return (maxDigit1 * 10) + maxDigit2;
    }
}
