package io.github.stdonnelly.adventofcode.day06.loader;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day06.model.LongTable;
import io.github.stdonnelly.adventofcode.day06.model.Operation;
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

  private List<Operation> operationList;

  public LongTableLoader(String inFileName) {
    super(inFileName);
  }

  /// Like [load()][#load], but formats the output as a [LongTable]
  public LongTable loadAsLongTable() throws IOException {
    return new LongTable(load().toArray(long[][]::new));
  }

  /// Get the list of operations from the most recent [load()][#load()]
  ///
  /// This is probably not the best way to do this, but only being allowed to return 1 value and
  /// the way that I designed the [InputLoader] make it difficult to do anything else.
  ///
  /// @return The [List] of operations that was parsed during the most recent run of
  /// [loadAsLongTable()][#loadAsLongTable()] or [load()][#load()]
  /// @throws IllegalStateException If a load operation was not called before this, or if the most
  /// load operation did not yield an operation list
  public List<Operation> getOperationList() {
    if (operationList == null) {
      throw new IllegalStateException("load() was not called or did not yield an operation list");
    }
    return operationList;
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
          operationList = parseOperationRow(line);
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

  public List<Operation> parseOperationRow(String input) {
    return Arrays.stream(input.split(" "))
        .filter(Predicate.not(String::isBlank))
        .map(Operation::parse)
        .toList();
  }
}
