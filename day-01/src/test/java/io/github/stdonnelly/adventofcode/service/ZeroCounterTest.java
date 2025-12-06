package io.github.stdonnelly.adventofcode.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import io.github.stdonnelly.adventofcode.loader.InputLoader;
import io.github.stdonnelly.adventofcode.model.Dial;
import io.github.stdonnelly.adventofcode.model.Instruction;

public class ZeroCounterTest {
    private final ZeroCounter zeroCounter = new ZeroCounter();

    /**
     * Test part 1 with the example input
     * 
     * @throws IOException if the input loading fails
     */
    @Test
    void countZerosDuringExecutionTest() throws IOException {
        final int EXPECTED_COUNT = 3;

        final InputLoader inputLoader = new InputLoader("example_input.txt");
        final List<Instruction> instructions = inputLoader.load();

        assertEquals(EXPECTED_COUNT, zeroCounter.countZerosDuringExecution(new Dial(), instructions));
    }

    /**
     * Test part 2 with the example input
     * 
     * @throws IOException if the input loading fails
     */
    @Test
    void countAllZerosDuringExecutionTest() throws IOException {
        fail("foo");
        final int EXPECTED_COUNT = 6;

        final InputLoader inputLoader = new InputLoader("example_input.txt");
        final List<Instruction> instructions = inputLoader.load();

        assertEquals(EXPECTED_COUNT, zeroCounter.countAllZerosDuringExecution(new Dial(), instructions));
    }

    /**
     * Test part 2 with the specific input
     * 
     * @throws IOException if the input loading fails
     */
    @ParameterizedTest
    @MethodSource("instructionProvider")
    void countAllZerosDuringExecutionParameterizedTest(final List<Instruction> instructions, final int expected) {
        assertEquals(expected, zeroCounter.countZerosDuringExecution(new Dial(), instructions));
    }

    static Stream<Arguments> instructionProvider() {
        return Stream.of(
                arguments(Collections.emptyList(), 0));
    }
}