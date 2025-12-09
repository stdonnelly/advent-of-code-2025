package io.github.stdonnelly.adventofcode.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.github.stdonnelly.adventofcode.day02.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day02.model.IdRange;

class AppTest {
    private static final String EXAMPLE_FILE_NAME = "example_input.txt";

    /**
     * Test part 1 with the example input
     * 
     * @throws IOException if the input loading fails
     */
    @Test
    void part1Test() throws IOException {
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
    void part2Test() throws IOException {
        final long EXPECTED = 4174379265L;
        final InputLoader inputLoader = new InputLoader(EXAMPLE_FILE_NAME);
        final List<IdRange> input = inputLoader.load();
        assertEquals(EXPECTED, App.part2(input));
    }

    /**
     * Check if the example input or the real input have overlaps
     * 
     * This is to check an assumption before writing the actual code.
     * 
     * @param inputFileName The file to check. This is parameterized because I want
     *                      to check both the example and the real input.
     * @throws IOException if the input loading fails
     */
    @ParameterizedTest
    @ValueSource(strings = { EXAMPLE_FILE_NAME, "input.txt" })
    void noOverlapsTest(String inputFileName) throws IOException {
        final InputLoader inputLoader = new InputLoader(inputFileName);
        final List<IdRange> input = inputLoader.load();

        // Loop over all input elements to check if start or end are in another range
        // We're using a traditional for loop because I want to exclude this when
        // checking (a range always overlaps with itself).
        final int inputSize = input.size();
        for (int i = 0; i < inputSize; i++) {
            final IdRange idRange = input.get(i);

            // Yay, O(n^2)
            for (int j = 0; j < inputSize; j++) {
                // Skip the IdRange at I to prevent false positives
                if (i == j) {
                    continue;
                }

                final IdRange idRange2 = input.get(j);

                assertFalse(idRange2.contains(idRange.start()), () -> idRange + " overlaps with " + idRange2);
                assertFalse(idRange2.contains(idRange.end()), () -> idRange + " overlaps with " + idRange2);
            }
        }
    }
}
