package ${package};

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ${package}.App;

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
        final int EXPECTED = -1;
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
        final int EXPECTED = -1;
        assertEquals(EXPECTED, App.part2());
    }
}
