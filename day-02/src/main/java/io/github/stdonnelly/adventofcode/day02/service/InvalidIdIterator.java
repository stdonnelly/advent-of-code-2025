package io.github.stdonnelly.adventofcode.day02.service;

import java.util.Iterator;
import java.util.NoSuchElementException;

import io.github.stdonnelly.adventofcode.day02.model.IdRange;

/// Iterator over invalid IDs in a given [IdRange]
/// 
/// "Invalid" is defined in [Advent of code 2025:
/// Day 2](https://adventofcode.com/2025/day/2)
public class InvalidIdIterator implements Iterator<Long> {
    // The number to be returned by next()
    private long next;
    // The last number (inclusive)
    private long end;
    // A flag to determine if this state is valid
    private boolean invalidState;

    /// Construct a new instance
    /// 
    /// @param range the [IdRange] to iterate over
    public InvalidIdIterator(IdRange range) {
        // Set the next to before the start.
        // That way, if start is invalid
        next = range.start() - 1;
        end = range.end();

        invalidState = true;
    }

    @Override
    public boolean hasNext() {
        // Naive implementation: just go through every number and check if it is invalid

        // Always false if this is outside the range
        if (next >= end) {
            return false;
        }

        // Start by incrementing and continue until we get to an id that is an invalid
        // id.
        next++;
        while (!isInvalidId(next)) {
            next++;
            if (next > end) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Long next() {
        // Check if the state is valid
        if (invalidState) {
            throw new NoSuchElementException();
        }

        return next;
    }

    /// Returns true if the number is an "invalid" ID
    /// 
    /// @param num The number to check
    boolean isInvalidId(long num) {
        final int length = floorLog(num);

        // A number with an odd number of digits can never be considered invalid
        if (length % 2 == 1) {
            return false;
        }

        // Not sure what to call this. It is 10^(length/2)
        final long halfExponent = Math.powExact(10L, length / 2);

        // Return true if the first half is the same as the last half
        return num / halfExponent == num % halfExponent;
    }

    /// Returns the floor of log_10(num)
    /// 
    /// @param num The number
    int floorLog(long num) {
        // Validation
        if (num < 0) {
            throw new IllegalArgumentException("floorLog(num): num must be non-negative");
        }

        // Count the number of times we can divide by 10
        int log = 0;
        for (; num != 0; num /= 10) {
            log++;
        }

        return log;
    }
}