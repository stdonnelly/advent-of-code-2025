package io.github.stdonnelly.adventofcode.day02.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An inclusive range of IDs from the question
 * 
 * @param start The start of the range (inclusive)
 * @param end   The end of the range (inclusive)
 */
public record IdRange(long start, long end) {
    // A regex to match the range
    private static final Pattern PARSER_PATTERN = Pattern.compile("^(\\d+)-(\\d+)$");

    /**
     * Parse an input object
     * 
     * @param input The input string to parse
     * 
     * @return The input after parsing
     * @throws IllegalArgumentException If the input is not parsable
     */
    public static IdRange parse(String input) throws IllegalArgumentException {
        final Matcher matcher = PARSER_PATTERN.matcher(input);

        // Return an exception if the parsing fails.
        // This should cover any parsing error.
        // The rest of the statements in this method can throw runtime errors,
        // but should only do so if the regex was made incorrectly.
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unable to parse input '" + input + "'");
        }

        final long start = Long.parseLong(matcher.group(1));
        final long end = Long.parseLong(matcher.group(2));

        return new IdRange(start, end);
    }

    /**
     * Check if the number is included in this range
     * 
     * @param other The number to check
     * @return `true` if the number is in this range (inclusive). `false` otherwise.
     */
    public boolean contains(long other) {
        return this.start <= other && other <= this.end;
    }
}
