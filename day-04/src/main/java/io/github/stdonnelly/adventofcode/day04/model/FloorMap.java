package io.github.stdonnelly.adventofcode.day04.model;

import java.util.stream.Stream;

public record FloorMap() {
    /**
     * Parse an input object
     * 
     * @param input
     *            The input strings to parse.
     *            This takes a {@link Stream} because this parses the entire file,
     *            rather than just a line.
     * 
     * @return The input after parsing
     * @throws IllegalArgumentException
     *             If the input is not parsable
     */
    public static FloorMap parse(Stream<String> input) throws IllegalArgumentException {
        throw new java.lang.UnsupportedOperationException("TODO: write input parser");
    }
}
