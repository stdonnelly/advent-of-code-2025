package io.github.stdonnelly.adventofcode.day10.mapper;

import io.github.stdonnelly.adventofcode.day10.model.ButtonSchematic;
import io.github.stdonnelly.adventofcode.day10.model.JoltageRequirements;
import io.github.stdonnelly.adventofcode.day10.model.MachineDescription;
import io.github.stdonnelly.adventofcode.day10.model.Matrix;

/// Mapper from [MachineDescription] to [Matrix]
public class MachineToMatrixMapper {
  /// Map the given [MachineDescription] to an augmented [Matrix] for the buttons and joltage
  /// requirements
  ///
  /// @param machineDescription The machine to map
  /// @return An augmented matrix representation of the system of linear equations
  public Matrix map(final MachineDescription machineDescription) {
    return map(machineDescription.buttons(), machineDescription.joltageRequirements());
  }

  /// Map the given [ButtonSchematic] array and [JoltageRequirements] to an augmented [Matrix]
  ///
  /// @param buttonSchematics The buttons in the machine
  /// @param joltageRequirements The array of required joltage
  /// @return An augmented matrix representation of the system of linear equations. The matrix will
  /// have `joltageRequirements.requiredJoltage().length` rows and `buttonSchematics.length + 1`
  /// columns
  public Matrix map(
      final ButtonSchematic[] buttonSchematics, final JoltageRequirements joltageRequirements) {
    final int length = joltageRequirements.requiredJoltage().length;
    Matrix.Row[] rows = new Matrix.Row[length];
    for (int i = 0; i < length; i++) {
      rows[i] = mapRow(buttonSchematics, joltageRequirements.requiredJoltage()[i], i);
    }
    return new Matrix(rows);
  }

  /// Map the given [ButtonSchematic] array and the `joltageRequirement` to a row in an augmented
  /// [Matrix]
  ///
  /// @param buttonSchematics The buttons in the machine
  /// @param joltageRequirement The joltage requirement for this row. Will be used as the last
  /// column
  /// @param joltageIndex The index in the joltage requirements array that this row represents.
  /// This is needed to determine which elements of `buttonSchematics` to include.
  /// @return The augmented matrix row representing this joltage requirement. The result will have
  /// `buttonSchematics.length + 1` columns, with the last column being `joltageRequirement`.
  public Matrix.Row mapRow(
      final ButtonSchematic[] buttonSchematics,
      final int joltageRequirement,
      final int joltageIndex) {
    final int length = buttonSchematics.length;
    int[] values = new int[length + 1];
    for (int i = 0; i < length; i++) {
      values[i] = buttonSchematics[i].contains(joltageIndex) ? 1 : 0;
    }
    // Put the joltage requirement on the last column
    values[length] = joltageRequirement;

    return new Matrix.Row(values);
  }
}
