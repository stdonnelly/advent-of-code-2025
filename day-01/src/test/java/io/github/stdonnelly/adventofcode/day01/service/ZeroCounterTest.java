package io.github.stdonnelly.adventofcode.day01.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import io.github.stdonnelly.adventofcode.day01.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day01.model.Dial;
import io.github.stdonnelly.adventofcode.day01.model.Instruction;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZeroCounterTest {
    private static final String EXAMPLE_FILE_NAME = "example_input.txt";
    private static final String CSV_FILE_NAME = "/part_2_testcases.csv";
    private final ZeroCounter zeroCounter = new ZeroCounter();

    /**
     * Test part 1 with the example input
     * 
     * @throws IOException
     *             if the input loading fails
     */
    @Test
    void countZerosDuringExecutionTest() throws IOException {
        final int EXPECTED_COUNT = 3;

        final InputLoader inputLoader = new InputLoader(EXAMPLE_FILE_NAME);
        final List<Instruction> instructions = inputLoader.load();

        assertEquals(EXPECTED_COUNT, zeroCounter.countZerosDuringExecution(new Dial(), instructions));
    }

    /**
     * Test part 2 with the example input
     * 
     * @throws IOException
     *             if the input loading fails
     */
    @Test
    void countAllZerosDuringExecutionTest() throws IOException {
        final int EXPECTED_COUNT = 6;

        final InputLoader inputLoader = new InputLoader(EXAMPLE_FILE_NAME);
        final List<Instruction> instructions = inputLoader.load();

        assertEquals(EXPECTED_COUNT, zeroCounter.countAllZerosDuringExecution(new Dial(), instructions));
    }

    /**
     * Test part 2 with the specific input
     * 
     * @throws IOException
     *             if the input loading fails
     */
    @ParameterizedTest
    @CsvFileSource(resources = CSV_FILE_NAME, useHeadersInDisplayName = true)
    void countAllZerosDuringExecutionParameterizedTest(final String description, final String instructionsStr,
            final int expected) {

        List<Instruction> instructions;

        if (instructionsStr != null && !instructionsStr.isBlank()) {
            final String[] instructionsArray = instructionsStr.split(":");
            instructions = Arrays.stream(instructionsArray)
                    .map(Instruction::parse)
                    .toList();
        } else {
            instructions = Collections.emptyList();
        }

        assertEquals(expected, zeroCounter.countAllZerosDuringExecution(new Dial(), instructions), description);
    }
}