package io.github.stdonnelly.adventofcode.day02.service;

import java.util.regex.Pattern;

import io.github.stdonnelly.adventofcode.day02.model.IdRange;

/// {@inheritDoc} The definition of "invalid" has changed for part 2. It now
/// means any sequence of repeated n-length numbers
public class InvalidIdSpliteratorPart2 extends InvalidIdSpliterator {
    // Regex to check if the input is a repeating series of digits.
    private static Pattern INVALID_ID_PATTERN = Pattern.compile("^(\\d+)\\1+$");

    public InvalidIdSpliteratorPart2(IdRange range) {
        super(range);
    }

    /// {@inheritDoc} The original method, but with a new definition: It now means
    /// any sequence of repeated n-length numbers
    @Override
    boolean isInvalidId(long num) {
        // Just use a regex, lol
        return INVALID_ID_PATTERN.matcher(Long.toString(num)).matches();
    }
}
