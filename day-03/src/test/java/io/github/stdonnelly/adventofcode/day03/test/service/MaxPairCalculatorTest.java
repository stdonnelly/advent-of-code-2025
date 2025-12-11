package io.github.stdonnelly.adventofcode.day03.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import io.github.stdonnelly.adventofcode.day03.model.BatteryBank;
import io.github.stdonnelly.adventofcode.day03.service.MaxPairCalculator;

class MaxPairCalculatorTest {
    @ParameterizedTest
    @CsvSource(textBlock = """
            987654321111111,98
            811111111111119,89
            234234234234278,78
            818181911112111,92
            """)
    void testMaxPairCalculation_part1(final String batteryBankStr, final int expected) {
        final MaxPairCalculator maxPairCalculator = new MaxPairCalculator();
        final BatteryBank batteryBank = BatteryBank.parse(batteryBankStr);

        assertEquals(expected, maxPairCalculator.applyAsInt(batteryBank), () -> batteryBank.toString());
    }
}
