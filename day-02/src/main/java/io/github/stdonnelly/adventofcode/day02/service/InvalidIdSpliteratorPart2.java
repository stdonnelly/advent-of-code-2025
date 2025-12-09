package io.github.stdonnelly.adventofcode.day02.service;

import java.util.stream.IntStream;

import io.github.stdonnelly.adventofcode.day02.model.IdRange;

/// {@inheritDoc} The definition of "invalid" has changed for part 2. It now
/// means any sequence of repeated n-length numbers
public class InvalidIdSpliteratorPart2 extends InvalidIdSpliterator {
    private static final int[][] FACTORS;

    static {
        // Generate factors of all the numbers less than the maximum number of digits
        FACTORS = new int[EXPONENT.length][];

        // Loop over all elements
        for (int numberToFactor = 0; numberToFactor < FACTORS.length; numberToFactor++) {
            // Copy of numberToFactor because lambdas can only capture final variables.
            final int numberToFactorCopy = numberToFactor;

            // Loop over all possible factors of the number and filter out all that are not
            // factors.
            //
            // We are dividing by 2 because numberToFactor should be excluded and the second
            // half can never be a factor.
            FACTORS[numberToFactor] = IntStream.rangeClosed(1, numberToFactorCopy / 2)
                    .filter(possibleFactor -> numberToFactorCopy % possibleFactor == 0)
                    .toArray();
        }
    }

    public InvalidIdSpliteratorPart2(IdRange range) {
        super(range);
    }

    /// {@inheritDoc} The original method, but with a new definition: It now means
    /// any sequence of repeated n-length numbers
    @Override
    boolean isInvalidId(long num) {
        // TODO
        return false;
    }

    /// Check if the number is a number (`numOfDigits` long)
    /// 
    /// @param num The number to check
    /// @param numOfDigits The number of digits to check
    /// @return `true` if the number is `numOfDigits` repeating, `false` otherwise.
    boolean isNDigitsRepeating(long num, int numOfDigits) {
        // TODO
        return false;
    }
}
