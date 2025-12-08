package io.github.stdonnelly.adventofcode.day02.service;

import java.util.Comparator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators.AbstractLongSpliterator;
import java.util.function.LongConsumer;

import io.github.stdonnelly.adventofcode.day02.model.IdRange;

/// Iterator over invalid IDs in a given [IdRange]
/// 
/// "Invalid" is defined in [Advent of code 2025
/// Day 2](https://adventofcode.com/2025/day/2)
public class InvalidIdSpliterator extends AbstractLongSpliterator {
    // The number to be returned by next()
    private long next;
    // The last number (inclusive)
    private long end;

    /// Construct a new instance
    /// 
    /// @param range the [IdRange] to iterate over
    public InvalidIdSpliterator(IdRange range) {
        // Set the next to before the start.
        // That way, if start is invalid
        next = range.start() - 1;
        end = range.end();

        super(Long.MAX_VALUE, Spliterator.ORDERED
                | Spliterator.DISTINCT
                | Spliterator.SORTED
                | Spliterator.NONNULL
                | Spliterator.IMMUTABLE);
    }

    @Override
    public boolean tryAdvance(LongConsumer consumer) {
        Objects.requireNonNull(consumer);

        // Naive implementation: just go through every number and check if it is invalid

        // Always false if this will put `next` outside the range
        while (next < end) {
            // Start by incrementing and continue until we get to an id that is an invalid
            // id.
            next++;

            // Success if this is considered and invalid ID.
            if (isInvalidId(next)) {
                consumer.accept(next);
                return true;
            }
        }

        // If all numbers are exhausted, return failure
        return false;
    }

    @Override
    public Comparator<Long> getComparator() {
        return Long::compare;
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