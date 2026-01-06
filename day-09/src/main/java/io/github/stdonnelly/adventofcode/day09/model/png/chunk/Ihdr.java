package io.github.stdonnelly.adventofcode.day09.model.png.chunk;

import io.github.stdonnelly.adventofcode.day09.model.png.ColorType;
import io.github.stdonnelly.adventofcode.day09.model.png.InterlaceMethod;
import java.nio.ByteBuffer;
import java.util.Objects;

/// Header chunk
public final class Ihdr extends PngChunk {
  private final ByteBuffer data;

  /// Constructor
  ///
  /// @param width The width of the image. Treated as an unsigned integer. Must be nonzero.
  /// @param height The height of the image. Treated as an unsigned integer. Must be nonzero.
  /// @param bitDepth The bit depth, The allowed bit depth depends on the color type.
  /// @param colorType The color type. Must not be `null`.
  /// @param compressionMethod The compression method. Only 0 is allowed.
  /// @param filterMethod The filter method. Only 0 is allowed.
  /// @param interlaceMethod The interlace method. Must not be `null`.
  public Ihdr(
      int width,
      int height,
      byte bitDepth,
      ColorType colorType,
      byte compressionMethod,
      byte filterMethod,
      InterlaceMethod interlaceMethod) {
    if (width == 0) {
      throw new IllegalArgumentException("Width must be nonzero");
    }
    if (height == 0) {
      throw new IllegalArgumentException("Height must be nonzero");
    }
    Objects.requireNonNull(colorType);
    if (compressionMethod != 0) {
      throw new IllegalArgumentException("Compression method must be 0");
    }
    if (filterMethod != 0) {
      throw new IllegalArgumentException("Filter method must be 0");
    }
    Objects.requireNonNull(interlaceMethod);

    data =
        ByteBuffer.allocate(getLength())
            .putInt(width)
            .putInt(height)
            .put(bitDepth)
            .put(colorType.getColorTypeId())
            .put(compressionMethod)
            .put(filterMethod)
            .put(interlaceMethod.getId());
  }

  public int getWidth() {
    return data.getInt(0);
  }

  public int getHeight() {
    return data.getInt(4);
  }

  public byte getBitDepth() {
    return data.get(8);
  }

  public ColorType getColorType() {
    return switch (data.get(9)) {
      case 0 -> ColorType.GREYSCALE;
      case 2 -> ColorType.TRUECOLOR;
      case 3 -> ColorType.INDEXED_COLOR;
      case 4 -> ColorType.GREYSCALE_WITH_ALPHA;
      case 6 -> ColorType.TRUECOLOR_WITH_ALPHA;
      default -> throw new IllegalStateException("Invalid color type");
    };
  }

  public byte getCompressionMethod() {
    return data.get(10);
  }

  public byte getFilterMethod() {
    return data.get(11);
  }

  public InterlaceMethod getInterlaceMethod() {
    return switch (data.get(12)) {
      case 0 -> InterlaceMethod.NONE;
      case 1 -> InterlaceMethod.ADAM7;
      default -> throw new IllegalArgumentException("Invalid interlace method");
    };
  }

  @Override
  public int getLength() {
    // Override because this is always the same length
    return 4 + 4 + 1 + 1 + 1 + 1 + 1;
  }

  @Override
  public String getType() {
    return "IHDR";
  }

  @Override
  public byte[] getData() {
    return data.array();
  }

  public static class Builder {
    private int width;
    private int height;
    private byte bitDepth;
    private ColorType colorType;
    private byte compressionMethod;
    private byte filterMethod;
    private InterlaceMethod interlaceMethod;

    public Builder width(int width) {
      this.width = width;
      return this;
    }

    public Builder height(int height) {
      this.height = height;
      return this;
    }

    public Builder bitDepth(byte bitDepth) {
      this.bitDepth = bitDepth;
      return this;
    }

    public Builder colorType(ColorType colorType) {
      this.colorType = colorType;
      return this;
    }

    public Builder compressionMethod(byte compressionMethod) {
      this.compressionMethod = compressionMethod;
      return this;
    }

    public Builder filterMethod(byte filterMethod) {
      this.filterMethod = filterMethod;
      return this;
    }

    public Builder interlaceMethod(InterlaceMethod interlaceMethod) {
      this.interlaceMethod = interlaceMethod;
      return this;
    }

    public Ihdr build() {
      return new Ihdr(
          width, height, bitDepth, colorType, compressionMethod, filterMethod, interlaceMethod);
    }
  }
}
