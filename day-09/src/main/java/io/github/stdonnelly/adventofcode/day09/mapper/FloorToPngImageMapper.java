package io.github.stdonnelly.adventofcode.day09.mapper;

import io.github.stdonnelly.adventofcode.day09.model.Floor;
import io.github.stdonnelly.adventofcode.day09.model.FloorTile;
import io.github.stdonnelly.adventofcode.day09.model.png.ColorType;
import io.github.stdonnelly.adventofcode.day09.model.png.PngImage;
import io.github.stdonnelly.adventofcode.day09.model.png.RGBColor8Bit;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Idat;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Ihdr;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Plte;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/// Mapper from [Floor] to [PngImage]
public class FloorToPngImageMapper {
  // #region Public methods

  /// Crop and map `floor` to a [PngImage]
  public PngImage mapCropped(final Floor floor) {
    return map(floor.crop());
  }

  /// Map `floor` to a [PngImage]
  public PngImage map(final Floor floor) {
    return new PngImage.Builder()
        .imageHeader(generateHeader(floor))
        .pallette(Optional.of(generatePallette()))
        .dataList(List.of(generateData(floor)))
        .build();
  }

  // #endregion

  // #region Private helpers

  /// Generate the IHDR chunk
  private Ihdr generateHeader(final Floor floor) {
    return new Ihdr.Builder()
        .width(floor.getWidth())
        .height(floor.getHeight())
        .bitDepth(getBitDepthForFloorTile())
        .colorType(ColorType.INDEXED_COLOR)
        .build();
  }

  /// Generate the PLTE chunk
  private Plte generatePallette() {
    final List<RGBColor8Bit> pallette =
        Arrays.stream(FloorTile.values())
            .map(
                floorTile ->
                    new RGBColor8Bit(floorTile.getRed(), floorTile.getGreen(), floorTile.getBlue()))
            .toList();

    return new Plte(pallette);
  }

  /// Generate the IDAT chunk
  private Idat generateData(final Floor floor) {
    throw new UnsupportedOperationException("TODO");
  }

  /// Map the row of floor tiles to a [ByteBuffer]
  private ByteBuffer mapRow(final FloorTile[] row) {
    throw new UnsupportedOperationException("TODO");
  }

  /// Get the bit depth needed to represent each [FloorTile]
  private byte getBitDepthForFloorTile() {
    int floorTileColorCount = FloorTile.values().length;
    if (floorTileColorCount > 256) {
      throw new IllegalArgumentException(
          "Unable to generate an image. There cannot be more colors than 256");
    }

    // Find the minimum number of bits that support that color count.
    byte bitDepth = (byte) 0;
    while (floorTileColorCount > 0) {
      bitDepth++;
      floorTileColorCount >>= 1;
    }
    return bitDepth;
  }

  // #endregion
}
