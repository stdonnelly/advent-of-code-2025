package io.github.stdonnelly.adventofcode.day04.service;

import io.github.stdonnelly.adventofcode.day04.model.FloorMap;
import io.github.stdonnelly.adventofcode.day04.model.FloorSquare;

import static io.github.stdonnelly.adventofcode.day04.model.FloorSquare.PAPER;
import static io.github.stdonnelly.adventofcode.day04.model.FloorSquare.REMOVED;

/// Service class that counts the number of paper squares on the floor map can be moved
/// 
/// This one also removes and counts again
public class FloorServicePart2 extends FloorService {
    // This method does intentionally jump back in the loop.
    // I know this isn't good practice, but this is more efficient than calling this
    // method possibly hundreds of times.
    @SuppressWarnings("java:S127")
    @Override
    public int countMoveablePaper(final FloorMap floorMap) {
        final FloorSquare[][] squares = floorMap.squares();

        int movablePaperCount = 0;

        for (int row = 0; row < squares.length; row++) {
            for (int column = 0; column < squares[row].length; column++) {
                if (PAPER.equals(squares[row][column]) && floorMap.countNeighbors(row, column) < 4) {
                    movablePaperCount++;

                    // Remove this one
                    squares[row][column] = REMOVED;

                    // Move back to the left and up, if possible, that one might be able to be
                    // removed now.
                    if (column > 0) {
                        // subtract 2 because the loop will add another one.
                        column -= 2;
                    }
                    if (row > 0) {
                        row--;
                    }
                }
            }
        }

        return movablePaperCount;
    }
}
