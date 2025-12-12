package io.github.stdonnelly.adventofcode.day02.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;

import io.github.stdonnelly.adventofcode.day02.model.IdRange;

/// Writes the results to a YAML file
public class ResultWriter implements AutoCloseable {
    private final BufferedWriter writer;
    private final Yaml yaml;

    /// Constructs and instance of this
    /// 
    /// @param outPath The path of the file to write. Will be truncated if it exists.
    /// @throws IOException If there is an error creating a buffered writer for
    ///                     the path.
    public ResultWriter(Path outPath) throws IOException {
        writer = Files.newBufferedWriter(outPath);

        // YAML output configuration
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
        yaml = new Yaml(dumperOptions);

        // Write the comment to link this to the schema file
        writer.write(
                "# yaml-language-server: $schema=https://raw.githubusercontent.com/stdonnelly/advent-of-code-2025/refs/heads/day-02/day-02/src/main/resources/output.schema.json\n\n");
    }

    /// Write the range and invalid Ids to the file
    /// 
    /// @param ranges     The ranges to output
    /// @param invalidIds The List of invalid IDs to output
    public void write(List<IdRange> ranges, List<Long> invalidIds) {
        // LinkedHashMap to preserve order
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("ranges", ranges.stream().map(IdRange::toString).toList());
        map.put("invalid_ids", invalidIds);

        // Dump as a singleton list
        yaml.dump(map, writer);
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
