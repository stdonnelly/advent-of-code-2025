package io.github.stdonnelly.adventofcode.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.stdonnelly.adventofcode.day02.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day02.model.IdRange;

class AppTest
{
    private static final String EXAMPLE_FILE_NAME = "example_input.txt";

    /**
     * Test part 1 with the example input
     * 
     * @throws IOException if the input loading fails
     */
    @Test
    void part1Test() throws IOException
    {
        final long EXPECTED = 1227775554;
        final InputLoader inputLoader = new InputLoader(EXAMPLE_FILE_NAME);
        final List<IdRange> input = inputLoader.load();
        assertEquals(EXPECTED, App.part1(input));
    }

    /**
     * Test part 2 with the example input
     * 
     * @throws IOException if the input loading fails
     */
    @Test
    void part2Test() throws IOException
    {
        final long EXPECTED = -1;
        final InputLoader inputLoader = new InputLoader(EXAMPLE_FILE_NAME);
        final List<IdRange> input = inputLoader.load();
        assertEquals(EXPECTED, App.part2(input));
    }
}
