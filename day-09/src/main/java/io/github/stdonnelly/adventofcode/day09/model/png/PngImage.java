package io.github.stdonnelly.adventofcode.day09.model.png;

import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Idat;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Iend;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Ihdr;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Plte;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.PngChunk;
import java.util.List;
import java.util.Optional;

public class PngImage {
  private static final byte[] PNG_SIGNATURE =
      new byte[] {(byte) 0x89, 'P', 'N', 'G', 0x0d, 0x0a, 0x1a, 0x0a};
  private Ihdr imageHeader;
  // This allows for optional chunks, but does not allow any chunks that must appear after PLTE or
  // IDAT.
  private List<PngChunk> optionalChunks;
  private Optional<Plte> pallette;
  private List<Idat> dataList;
  private Iend imageEnd;
}
