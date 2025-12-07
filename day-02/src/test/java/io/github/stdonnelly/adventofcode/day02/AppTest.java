package io.github.stdonnelly.adventofcode.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.github.stdonnelly.adventofcode.day02.App;

class AppTest
{
    private static final String EXAMPLE_FILE_NAME = "example_input.txt";

    /**
     * Test part 1 with the example input
     * 
     * @throws IOException if the input loading fails
     */
    @Test
    public void part1Test()
    {
        final long EXPECTED = 1227775554;
        assertEquals(EXPECTED, App.part1());
    }

    /**
     * Test part 2 with the example input
     * 
     * @throws IOException if the input loading fails
     */
    @Test
    public void part2Test()
    {
        final long EXPECTED = -1;
        assertEquals(EXPECTED, App.part2());
    }
}
