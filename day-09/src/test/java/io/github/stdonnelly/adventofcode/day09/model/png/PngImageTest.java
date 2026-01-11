package io.github.stdonnelly.adventofcode.day09.model.png;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Idat;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Ihdr;
import io.github.stdonnelly.adventofcode.day09.model.png.chunk.Plte;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class PngImageTest {
  // Creates a very small black and white image of a smiley face
  @Test
  void testWrite_blackAndWhite() {
    final PngImage pngImage = getBlackAndWhitePngImage();
    final Path path = Paths.get("output_test_bw.png");

    assertDoesNotThrow(
        () -> {
          pngImage.write(path);
        });
  }

  // Creates a very small image of a smiley face with the pallette colors white, black, red, and
  // green
  @Test
  void testWrite_palletteColor() {
    final PngImage pngImage = getPalletteColorPngImage();
    final Path path = Paths.get("output_test_color.png");

    assertDoesNotThrow(
        () -> {
          pngImage.write(path);
        });
  }

  private PngImage getBlackAndWhitePngImage() {
    return new PngImage.Builder()
        .imageHeader(
            new Ihdr.Builder()
                .width(8)
                .height(8)
                .bitDepth((byte) 1)
                .colorType(ColorType.GREYSCALE)
                .interlaceMethod(InterlaceMethod.NONE)
                .build())
        .dataList(
            List.of(
                Idat.ofUncompressed(
                    ByteBuffer.wrap(
                        new byte[] {
                          0, (byte) 0b11111111,
                          0, (byte) 0b11111111,
                          0, (byte) 0b11011011,
                          0, (byte) 0b11111111,
                          0, (byte) 0b10111101,
                          0, (byte) 0b11000011,
                          0, (byte) 0b11111111,
                          0, (byte) 0b11111111,
                        }))))
        .build();
  }

  private PngImage getPalletteColorPngImage() {
    PackedBitBuffer bitBuffer = PackedBitBuffer.withCapacity(24 * 6, 2);

    bitBuffer.putByte((byte) 0);
    bitBuffer.put((byte) 1);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 1);
    bitBuffer.put((byte) 0);

    bitBuffer.putByte((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 3);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 3);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);

    bitBuffer.putByte((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);

    bitBuffer.putByte((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 2);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 2);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);

    bitBuffer.putByte((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 2);
    bitBuffer.put((byte) 2);
    bitBuffer.put((byte) 2);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);

    bitBuffer.putByte((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);
    bitBuffer.put((byte) 0);

    return new PngImage.Builder()
        .imageHeader(
            new Ihdr.Builder()
                .width(7)
                .height(6)
                .bitDepth((byte) 2)
                .colorType(ColorType.INDEXED_COLOR)
                .interlaceMethod(InterlaceMethod.NONE)
                .build())
        .pallette(
            Optional.of(
                new Plte(
                    List.of(
                        new RGBColor8Bit((byte) 255, (byte) 255, (byte) 255),
                        new RGBColor8Bit((byte) 0, (byte) 0, (byte) 0),
                        new RGBColor8Bit((byte) 255, (byte) 0, (byte) 0),
                        new RGBColor8Bit((byte) 0, (byte) 255, (byte) 0)))))
        .dataList(
            List.of(Idat.ofUncompressed(bitBuffer.getByteBuffer().asReadOnlyBuffer().rewind())))
        .build();
  }
}
