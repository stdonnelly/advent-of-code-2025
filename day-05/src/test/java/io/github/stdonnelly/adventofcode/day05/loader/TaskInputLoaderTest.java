package io.github.stdonnelly.adventofcode.day05.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.common.model.InclusiveRange;
import io.github.stdonnelly.adventofcode.day05.model.TaskInput;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class TaskInputLoaderTest {
  private static final String EXAMPLE_FILE_NAME = "example_input.txt";

  /// Ensure the loader is working by loading the example input and checking the loaded object
  @Test
  void testLoad_exampleInput() throws IOException {
    final TaskInputLoader taskInputLoader = new TaskInputLoader(EXAMPLE_FILE_NAME);
    final TaskInput taskInput = taskInputLoader.load();

    List<InclusiveRange> expectedFreshIngredientRanges =
        List.of(
            new InclusiveRange(3, 5),
            new InclusiveRange(10, 14),
            new InclusiveRange(16, 20),
            new InclusiveRange(12, 18));

    List<Long> expectedAvailableIngredients = List.of(1L, 5L, 8L, 11L, 17L, 32L);

    assertEquals(
        expectedFreshIngredientRanges,
        taskInput.freshIngredientRanges(),
        "Fresh ingredient ranges");
    assertEquals(
        expectedAvailableIngredients,
        taskInput.availableIngredients(),
        "Available ingredient list");
  }
}
