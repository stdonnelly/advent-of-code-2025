package io.github.stdonnelly.adventofcode.day12.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.day12.model.PresentShape;
import io.github.stdonnelly.adventofcode.day12.model.ProblemInput;
import io.github.stdonnelly.adventofcode.day12.model.Region;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.junit.jupiter.api.Test;

class ProblemInputLoaderTest {
  private static final String EXAMPLE_FILE_NAME = "example_input.txt";

  @Test
  void testLoad() throws IOException, ParseException {
    final ProblemInput expected =
        new ProblemInput(
            List.of(
                new PresentShape(
                    new boolean[][] {
                      new boolean[] {true, true, true},
                      new boolean[] {true, true, false},
                      new boolean[] {true, true, false}
                    }),
                new PresentShape(
                    new boolean[][] {
                      new boolean[] {true, true, true},
                      new boolean[] {true, true, false},
                      new boolean[] {false, true, true}
                    }),
                new PresentShape(
                    new boolean[][] {
                      new boolean[] {false, true, true},
                      new boolean[] {true, true, true},
                      new boolean[] {true, true, false}
                    }),
                new PresentShape(
                    new boolean[][] {
                      new boolean[] {true, true, false},
                      new boolean[] {true, true, true},
                      new boolean[] {true, true, false}
                    }),
                new PresentShape(
                    new boolean[][] {
                      new boolean[] {true, true, true},
                      new boolean[] {true, false, false},
                      new boolean[] {true, true, true}
                    }),
                new PresentShape(
                    new boolean[][] {
                      new boolean[] {true, true, true},
                      new boolean[] {false, true, false},
                      new boolean[] {true, true, true}
                    })),
            List.of(
                new Region(4, 4, new int[] {0, 0, 0, 0, 2, 0}),
                new Region(12, 5, new int[] {1, 0, 1, 0, 2, 2}),
                new Region(12, 5, new int[] {1, 0, 1, 0, 3, 2})));
    final ProblemInputLoader loader = new ProblemInputLoader(EXAMPLE_FILE_NAME);

    assertEquals(expected, loader.load());
  }
}
