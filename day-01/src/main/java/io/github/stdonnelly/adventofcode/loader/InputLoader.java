package io.github.stdonnelly.adventofcode.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import io.github.stdonnelly.adventofcode.model.Instruction;

/**
 * Loads the input file
 */
public class InputLoader {
    final String IN_FILE_NAME = "input.txt";

    /**
     * Takes the input from the resource named in {@link IN_FILE_NAME} and parses it
     * into a list of instructions
     * 
     * @return A List of Instructions from the file
     * @throws IOException if there is a problem loading the input.txt file, or if there is a problem reading the file
     */
    public List<Instruction> load() throws IOException {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(IN_FILE_NAME);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(isr)) {
            if (is == null) {
                throw new FileNotFoundException("input.txt not found");
            }

            return reader.lines()
                .map(Instruction::parse)
                .collect(Collectors.toList());
        }
    }
}
