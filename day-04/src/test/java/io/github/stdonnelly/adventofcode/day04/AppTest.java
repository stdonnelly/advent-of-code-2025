package io.github.stdonnelly.adventofcode.day04;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import io.github.stdonnelly.adventofcode.day04.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day04.model.FloorMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private static final String EXAMPLE_FILE_NAME = "example_input.txt";

    /**
     * Test part 1 with the example input
     * 
     * @throws IOException
     *             if the input loading fails
     */
    @Test
    void part1Test() throws IOException {
        final int EXPECTED = 13;
        final InputLoader inputLoader = new InputLoader(EXAMPLE_FILE_NAME);
        final FloorMap input = inputLoader.load();
        assertEquals(EXPECTED, App.part1(input));
    }

    /**
     * Test part 2 with the example input
     * 
     * @throws IOException
     *             if the input loading fails
     */
    @Test
    void part2Test() throws IOException {
        final int EXPECTED = -1;
        final InputLoader inputLoader = new InputLoader(EXAMPLE_FILE_NAME);
        final FloorMap input = inputLoader.load();
        assertEquals(EXPECTED, App.part2(input));
    }
}
