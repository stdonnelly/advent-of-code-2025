package io.github.stdonnelly.adventofcode.day04.service;

import io.github.stdonnelly.adventofcode.day04.model.FloorMap;
import io.github.stdonnelly.adventofcode.day04.model.FloorSquare;

import static io.github.stdonnelly.adventofcode.day04.model.FloorSquare.PAPER;

/// Service class that counts the number of paper squares on the floor map can be moved
public class FloorService {
    public int countMoveablePaper(final FloorMap floorMap) {
        final FloorSquare[][] squares = floorMap.squares();

        int movablePaperCount = 0;

        for (int row = 0; row < squares.length; row++) {
            for (int column = 0; column < squares[row].length; column++) {
                if (PAPER.equals(squares[row][column]) && floorMap.countNeighbors(row, column) < 4) {
                    movablePaperCount++;
                }
            }
        }

        return movablePaperCount;
    }
}
