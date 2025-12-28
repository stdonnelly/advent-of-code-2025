package io.github.stdonnelly.adventofcode.day07.service;

import static io.github.stdonnelly.adventofcode.day07.model.ManifoldSquare.BEAM;
import static io.github.stdonnelly.adventofcode.day07.model.ManifoldSquare.START;

import io.github.stdonnelly.adventofcode.day07.model.ManifoldRow;
import java.util.List;

public class BeamSplitCounter {
  /// Counts the number of beam splits in the given manifold map
  ///
  /// Note: This operation will modify the input list.
  public int countSplits(List<ManifoldRow> manifoldRowList) {
    int splitCount = 0;

    // For optimization, find the most recent row only once per row
    ManifoldRow lastManifoldRow = manifoldRowList.get(0);
    // Skip the first row because there is nothing above it
    for (int row = 1; row < manifoldRowList.size(); row++) {
      // For optimization, find this row only once per row
      ManifoldRow thisManifoldRow = manifoldRowList.get(row);

      for (int col = 0; col < thisManifoldRow.manifoldSquareList().size(); col++) {
        // See if above was a beam or source
        if (BEAM.equals(lastManifoldRow.manifoldSquareList().get(col))
            || START.equals(lastManifoldRow.manifoldSquareList().get(col))) {
          // Split or propagate
          switch (thisManifoldRow.manifoldSquareList().get(col)) {
            case EMPTY:
              thisManifoldRow.manifoldSquareList().set(col, BEAM);
              break;
            case SPLITTER:
              thisManifoldRow.manifoldSquareList().set(col - 1, BEAM);
              thisManifoldRow.manifoldSquareList().set(col + 1, BEAM);
              splitCount++;
              break;
            case BEAM:
              // Ignore if there is already a beam here
              break;
            case START:
              throw new IllegalArgumentException(
                  "Start found on row " + row + ". Only row 0 should have a start");
          }
        }
      }

      // Set the last row to this one for the next iteration
      lastManifoldRow = thisManifoldRow;
    }

    return splitCount;
  }
}
