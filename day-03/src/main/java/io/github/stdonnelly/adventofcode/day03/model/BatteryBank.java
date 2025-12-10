package io.github.stdonnelly.adventofcode.day03.model;

/// An ordered bank of batteries
/// 
/// @param batteries The actual array of batteries.
public record BatteryBank(int[] batteries) {
    /**
     * Parse an input object
     * 
     * @param input The input string to parse
     * 
     * @return The input after parsing
     * @throws IllegalArgumentException If the input is not parsable
     */
    public static BatteryBank parse(String input) throws IllegalArgumentException {
        if (input == null || "".equals(input)) {
            return new BatteryBank(new int[0]);
        }

        int[] batteries = input.chars()
            .map(c -> {
                if (c < '0' || c > '9') {
                    throw new IllegalArgumentException("Unable to parse as digit '" + (char)c + "'");
                } else {
                    return c - '0';
                }
            })
            .toArray();
        
            return new BatteryBank(batteries);
    }
}
