package io.github.stdonnelly.adventofcode.day09.model.png;

import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Idat;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Iend;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Ihdr;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Plte;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.PngChunk;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PngImage {
  // #region Fields

  // The signature and end chunk are always the same
  private static final byte[] PNG_SIGNATURE =
      new byte[] {(byte) 0x89, 'P', 'N', 'G', 0x0d, 0x0a, 0x1a, 0x0a};
  private static final Iend IMAGE_END = Iend.getInstance();

  private Ihdr imageHeader;
  // This allows for optional chunks, but does not allow any chunks that must appear after PLTE or
  // IDAT.
  private List<PngChunk> optionalChunks;
  private Optional<Plte> pallette;
  private List<Idat> dataList;

  // #endregion

  // #region Constructors
  private PngImage(
      Ihdr imageHeader,
      List<PngChunk> optionalChunks,
      Optional<Plte> pallette,
      List<Idat> dataList) {

    // Validation
    switch (imageHeader.getColorType()) {
      case GREYSCALE, GREYSCALE_WITH_ALPHA:
        if (pallette.isPresent()) {
          throw new IllegalArgumentException(
              "PLTE chunk must not be present for greyscale color types");
        }
        break;
      case INDEXED_COLOR:
        if (pallette.isEmpty()) {
          throw new IllegalArgumentException(
              "PLTE chunk must be present for indexed-color color type");
        }
        break;
      default:
        // Pallette may or may not be present
    }
    if (dataList.size() != 1) {
      throw new IllegalArgumentException("There must be at least one IDAT chunk");
    }
    Objects.requireNonNull(
        optionalChunks, "Ust an empty list if there are no additional optional chunks");

    this.imageHeader = imageHeader;
    this.optionalChunks = optionalChunks;
    this.pallette = pallette;
    this.dataList = dataList;
  }

  // #endregion

  // #region Public methods

  /// Write the PNG to the given file
  ///
  /// If the file exists, it will be truncated. Otherwise a new file will be created.
  ///
  /// @param path The path of the file to write to
  /// @throws IOException If the write fails for any reason
  public void write(Path path) throws IOException {
    try (FileChannel fileChannel =
        FileChannel.open(
            path,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE)) {
      write(fileChannel);
    }
  }

  /// Write the PNG to the given [FileChannel]
  ///
  /// @param fileChannel The file channel to write to
  /// @throws IOException If the write fails for any reason
  public void write(FileChannel fileChannel) throws IOException {
    fileChannel.write(ByteBuffer.wrap(PNG_SIGNATURE));
    fileChannel.write(imageHeader.getFullChunk());
    for (PngChunk chunk : optionalChunks) {
      fileChannel.write(chunk.getFullChunk());
    }
    if (pallette.isPresent()) {
      fileChannel.write(pallette.get().getFullChunk());
    }
    for (Idat dataChunk : dataList) {
      fileChannel.write(dataChunk.getFullChunk());
    }
    fileChannel.write(IMAGE_END.getFullChunk());
  }

  // #endregion

  public static class Builder {
    // Fields
    private Ihdr imageHeader;
    private List<PngChunk> optionalChunks = List.of();
    private Optional<Plte> pallette = Optional.empty();
    private List<Idat> dataList = List.of();

    public Builder imageHeader(Ihdr imageHeader) {
      this.imageHeader = imageHeader;
      return this;
    }

    public Builder optionalChunks(List<PngChunk> optionalChunks) {
      this.optionalChunks = optionalChunks;
      return this;
    }

    public Builder pallette(Optional<Plte> pallette) {
      this.pallette = pallette;
      return this;
    }

    public Builder dataList(List<Idat> dataList) {
      this.dataList = dataList;
      return this;
    }

    public PngImage build() {
      return new PngImage(imageHeader, optionalChunks, pallette, dataList);
    }
  }
}
