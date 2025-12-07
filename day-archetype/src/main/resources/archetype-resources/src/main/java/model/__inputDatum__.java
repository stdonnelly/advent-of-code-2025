package ${package}.model;

public record ${inputDatum}() {
    /**
     * Parse an input object
     * @param input The input string to parse
     * 
     * @return The input after parsing
     * @throws IllegalArgumentException If the input is not parsable
     */
    public static ${inputDatum} parse(String input) throws IllegalArgumentException {
        throw new java.lang.UnsupportedOperationException("TODO: write input parser");
    }
}
