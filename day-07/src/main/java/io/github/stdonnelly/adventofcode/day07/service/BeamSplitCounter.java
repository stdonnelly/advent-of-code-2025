package io.github.stdonnelly.adventofcode.day07.service;

import io.github.stdonnelly.adventofcode.day07.model.ManifoldRow;
import io.github.stdonnelly.adventofcode.day07.model.ManifoldSquare;
import io.github.stdonnelly.adventofcode.day07.model.ManifoldSquare.Beam;
import io.github.stdonnelly.adventofcode.day07.model.ManifoldSquare.Empty;
import io.github.stdonnelly.adventofcode.day07.model.ManifoldSquare.Splitter;
import io.github.stdonnelly.adventofcode.day07.model.ManifoldSquare.Start;
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
        final long aboveBeamCount = lastManifoldRow.manifoldSquareList().get(col).getBeamCount();
        if (aboveBeamCount > 0) {
          // Split or propagate
          final ManifoldSquare thisManifoldSquare = thisManifoldRow.manifoldSquareList().get(col);
          if (thisManifoldSquare instanceof Empty || thisManifoldSquare instanceof Beam) {
            setOrIncrementBeamCount(thisManifoldRow.manifoldSquareList(), col, aboveBeamCount);
          } else if (thisManifoldSquare instanceof Splitter) {
            setOrIncrementBeamCount(thisManifoldRow.manifoldSquareList(), col - 1, aboveBeamCount);
            setOrIncrementBeamCount(thisManifoldRow.manifoldSquareList(), col + 1, aboveBeamCount);
            splitCount++;
          } else {
            throw new IllegalArgumentException(
                "Unexpected manifold square found on row " + row + ".");
          }
        }
      }

      // Set the last row to this one for the next iteration
      lastManifoldRow = thisManifoldRow;
    }

    return splitCount;
  }

  private void setOrIncrementBeamCount(
      List<ManifoldSquare> manifoldSquareList, int index, long additionalBeams) {
    switch (manifoldSquareList.get(index)) {
      case Empty _ -> {
        Beam beam = new Beam();
        beam.setBeamCount(additionalBeams);
        manifoldSquareList.set(index, beam);
      }
      case Beam beam -> beam.addBeams(additionalBeams);
      case Splitter _ -> throw new IllegalArgumentException("Splitter found in unexpected place");
      case Start _ ->
          throw new IllegalArgumentException(
              "Start found on incorrect row. Only row 0 should have a start");
      default -> throw new IllegalArgumentException("Unexpected manifold square type");
    }
  }
}
