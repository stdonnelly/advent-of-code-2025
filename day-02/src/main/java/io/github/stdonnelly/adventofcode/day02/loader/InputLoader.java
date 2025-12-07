package io.github.stdonnelly.adventofcode.day02.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import io.github.stdonnelly.adventofcode.day02.model.IdRange;

/**
 * Loads the input file
 */
public class InputLoader {
    private final String inFileName;

    /**
     * Create an input file loader to load from the specified file
     * 
     * @param inFileName The file to load from
     */
    public InputLoader(final String inFileName) {
        this.inFileName = inFileName;
    }

    /**
     * Takes the input from the resource named in {@link inFileName} and parses it
     * into a list of {@link IdRange}
     * 
     * @return A List of IdRange from the file
     * @throws IOException if there is a problem loading the input.txt file, or if
     *                     there is a problem reading the file
     */
    public List<IdRange> load() throws IOException {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(inFileName);
                Scanner scanner = new Scanner(is, StandardCharsets.UTF_8)) {
            if (is == null) {
                throw new FileNotFoundException(inFileName + " not found");
            }

            scanner.useDelimiter(",");

            return scanner.tokens()
                    .map(String::trim)
                    .map(IdRange::parse)
                    .toList();
        }
    }
}
