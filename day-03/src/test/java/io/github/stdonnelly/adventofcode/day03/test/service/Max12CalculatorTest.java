package io.github.stdonnelly.adventofcode.day03.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import io.github.stdonnelly.adventofcode.day03.model.BatteryBank;
import io.github.stdonnelly.adventofcode.day03.service.Max12Calculator;

class Max12CalculatorTest {
    @ParameterizedTest
    @CsvSource(textBlock = """
            987654321111111,987654321111
            811111111111119,811111111119
            234234234234278,434234234278
            818181911112111,888911112111
            """)
    void testMaxPairCalculation_part1(final String batteryBankStr, final long expected) {
        final Max12Calculator maxPairCalculator = new Max12Calculator();
        final BatteryBank batteryBank = BatteryBank.parse(batteryBankStr);

        assertEquals(expected, maxPairCalculator.applyAsLong(batteryBank), () -> batteryBank.toString());
    }
}
