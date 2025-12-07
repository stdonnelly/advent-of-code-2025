package io.github.stdonnelly.adventofcode.day02.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import io.github.stdonnelly.adventofcode.day02.model.IdRange;

class InvalidIdIteratorTest {
    InvalidIdIterator invalidIdIterator = new InvalidIdIterator(new IdRange(1, 20));

    @ParameterizedTest
    @CsvSource(textBlock = """
            0,0
            1,1
            9,1
            10,2
            99,2
            100,3
            """)
    void floorLogTest(final long number, final int expected) {
        assertEquals(expected, invalidIdIterator.floorLog(number), () -> String.format("floorLog(%d)", number));
    }

    @ParameterizedTest
    @ValueSource(longs = {
            11L,
            22L,
            1010L,
            222222L,
            38593859L,
    })
    void isInvalidIdTest_true(long num) {
        assertTrue(invalidIdIterator.isInvalidId(num), () -> num + " should be considered invalid");
    }

    @ParameterizedTest
    @ValueSource(longs = {
            1L,
            10L,
            21L,
            222L,
            1001L,
            223222L,
            1234L,
            111L
    })
    void isInvalidIdTest_false(long num) {
        assertFalse(invalidIdIterator.isInvalidId(num), () -> num + " should not be considered invalid");
    }
}
