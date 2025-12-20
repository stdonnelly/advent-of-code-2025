package io.github.stdonnelly.adventofcode.day06.loader;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day06.model.LongTable;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class LongTableLoader extends InputLoader<long[]> {

  private static final Pattern STARTS_WITH_NUMBER = Pattern.compile("^\\s*\\d");

  public LongTableLoader(String inFileName) {
    super(inFileName);
  }

  /// Like [load()][#load], but formats the output as a [LongTable]
  public LongTable loadAsLongTable() throws IOException {
    return new LongTable(load().toArray(long[][]::new));
  }

  @Override
  public List<long[]> load() throws IOException {
    try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(inFileName);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr)) {
      if (is == null) {
        throw new FileNotFoundException(inFileName + " not found");
      }

      // Parse the file
      List<long[]> rowList = new ArrayList<>();
      for (String line = reader.readLine(); line != null; line = reader.readLine()) {
        // If this is the line with symbols, do not try to match
        if (!STARTS_WITH_NUMBER.matcher(line).find()) {
          break;
        }

        rowList.add(parseOne(line));
      }

      return rowList;
    }
  }

  @Override
  public long[] parseOne(String input) {
    return Arrays.stream(input.split(" "))
        .filter(Predicate.not(String::isBlank))
        .mapToLong(Long::parseLong)
        .toArray();
  }
}
