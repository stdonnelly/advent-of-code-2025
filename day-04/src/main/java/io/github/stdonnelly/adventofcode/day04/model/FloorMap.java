package io.github.stdonnelly.adventofcode.day04.model;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.stdonnelly.adventofcode.day04.model.FloorSquare.PAPER;

/// The entire map given in the task
///
/// @param squares The map as a 2D array of [FloorSquare]
public record FloorMap(FloorSquare[][] squares) {
    /**
     * Parse an input object
     * 
     * @param input
     *            The input strings to parse.
     *            This takes a {@link Stream} because this parses the entire
     *            file,
     *            rather than just a line.
     * 
     * @return The input after parsing
     * @throws IllegalArgumentException
     *             If the input is not parsable
     */
    public static FloorMap parse(Stream<String> input) throws IllegalArgumentException {
        FloorSquare[][] squares = input.map(FloorMap::parseRow).toArray(FloorSquare[][]::new);
        return new FloorMap(squares);
    }

    /// Count the neighbors of a given cell in the floor square
    ///
    /// A neighbor is directly adjacent or directly diagonally adjacent.
    ///
    /// @param row The row index (0-indexed)
    /// @param column The column index (0-indexed)
    public int countNeighbors(final int row, final int column) {
        final int rowCount = squares.length;
        final int columnCount = squares[0].length;

        int neighborCount = 0;

        // Check the row above if this is not the top row
        if (row > 0) {
            if (column > 0 && PAPER.equals(squares[row - 1][column - 1])) {
                neighborCount++;
            }
            if (PAPER.equals(squares[row - 1][column])) {
                neighborCount++;
            }
            if (column < columnCount - 1 && PAPER.equals(squares[row - 1][column + 1])) {
                neighborCount++;
            }
        }

        // Check this row
        if (column > 0 && PAPER.equals(squares[row][column - 1])) {
            neighborCount++;
        }
        if (column < columnCount - 1 && PAPER.equals(squares[row][column + 1])) {
            neighborCount++;
        }

        // Check the row below
        if (row < rowCount - 1) {
            if (column > 0 && PAPER.equals(squares[row + 1][column - 1])) {
                neighborCount++;
            }
            if (PAPER.equals(squares[row + 1][column])) {
                neighborCount++;
            }
            if (column < columnCount - 1 && PAPER.equals(squares[row + 1][column + 1])) {
                neighborCount++;
            }
        }

        return neighborCount;
    }

    private static FloorSquare[] parseRow(String input) throws IllegalArgumentException {
        return input.chars().mapToObj(mapChar -> FloorSquare.parse((char) mapChar)).toArray(FloorSquare[]::new);
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof FloorMap(FloorSquare[][] otherSquares)) {
            return Arrays.deepEquals(this.squares(), otherSquares);
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        return Arrays.deepHashCode(this.squares());
    }

    @Override
    public final String toString() {
        return Arrays.stream(this.squares())
                // Iterate over squares
                .map((FloorSquare[] line) -> Arrays.stream(line)
                        // For each square, iterate over characters
                        .map((FloorSquare square) -> Character.toString(square.getMapChar()))
                        .collect(Collectors.joining()))
                // Join lines with a newline
                .collect(Collectors.joining("\n"));
    }
}
