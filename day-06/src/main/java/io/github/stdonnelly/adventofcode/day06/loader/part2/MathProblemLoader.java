package io.github.stdonnelly.adventofcode.day06.loader.part2;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day06.model.part2.MathProblem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/// Loads a [MathProblem] list
public class MathProblemLoader extends InputLoader<MathProblem> {
  public MathProblemLoader(final String inFileName) {
    super(inFileName);
  }

  @Override
  public List<MathProblem> load() throws IOException {
    try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(inFileName);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr)) {
      if (is == null) {
        throw new FileNotFoundException(inFileName + " not found");
      }

      return loadInner(reader.readAllLines());
    }
  }

  @Override
  public MathProblem parseOne(String input) {
    // Unsupported because this doesn't work the same way.
    throw new UnsupportedOperationException();
  }

  /// Load from the list of lines, which contain the entire file.
  List<MathProblem> loadInner(final List<String> lines) {
    // Start by finding the number of rows, and the length of each section
    final int lineCount = lines.size();
    final List<Integer> columnLengthList = getColumnLengths(lines.getLast());

    // Value to return
    final List<MathProblem> mathProblemList = new ArrayList<>();
    // Variable to hold the start of each section. This exists because we have lengths, but not
    // offsets.
    int startIndex = 0;

    // Loop over each section and parse using the MathProblem#parse(String) static method
    // This is being parsed left-to-right for simplicity, but will be reversed before this method
    // returns.
    for (int columnLength : columnLengthList) {
      // Get the section
      String[] rows = new String[lineCount];
      for (int row = 0; row < lineCount; row++) {
        rows[row] = lines.get(row).substring(startIndex, startIndex + columnLength);
      }

      // Convert to a List<String>
      mathProblemList.add(MathProblem.parse(List.of(rows)));

      // Finally, add to the offset this column length, plus 1 for the space character
      startIndex += columnLength + 1;
    }

    return mathProblemList.reversed();
  }

  /// Find the lengths of each column based on the last row, which contains the operators
  List<Integer> getColumnLengths(final String operatorRow) {
    final char[] operatorRowChars = operatorRow.toCharArray();
    // Input validation
    final char firstChar = operatorRowChars[0];
    if (firstChar != '+' && firstChar != '*') {
      throw new IllegalArgumentException("The operator row must start with an operator");
    }

    // Check the length of each section between the operators
    final List<Integer> lengthList = new ArrayList<>();
    // Keep track of the length of the column we are currently working on
    int thisLength = 0;
    // The first character is skipped to prevent adding a zero-length column
    for (int i = 1; i < operatorRowChars.length; i++) {
      final char thisChar = operatorRowChars[i];
      if (thisChar == ' ') {
        thisLength++;
      } else if (thisChar == '+' || thisChar == '*') {
        // If this is another operator, use thisLength for the last column length
        lengthList.add(thisLength);
        thisLength = 0;
      } else {
        throw new IllegalArgumentException(
            "Only spaces and operators are expected in the operator row");
      }
    }

    // Finally, add the last length to the list
    // One is added to account for the fact that there is no trailing space as a delimiter
    lengthList.add(thisLength + 1);

    return lengthList;
  }
}
