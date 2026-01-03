package io.github.stdonnelly.adventofcode.day09.model.image;

import io.github.stdonnelly.adventofcode.day09.model.Floor;
import io.github.stdonnelly.adventofcode.day09.model.FloorTile;

import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.util.Vector;

/// A [Floor] that also implements [RenderedImage]
public class FloorRenderedImage extends Floor implements RenderedImage {
  // #region Constructors and factory methods
  public FloorRenderedImage(FloorTile[][] tiles) {
    super(tiles);
  }

  /// @see Floor#ofSize(int, int)
  public static FloorRenderedImage ofSize(int width, int height) {
    return new FloorRenderedImage(getTilesForSize(width, height));
  }

  //#endregion

  //#region RenderedImage overrides

  @Override
  public Vector<RenderedImage> getSources() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public Object getProperty(String name) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public String[] getPropertyNames() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public ColorModel getColorModel() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public SampleModel getSampleModel() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int getWidth() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int getHeight() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int getMinX() {
    return 0;
  }

  @Override
  public int getMinY() {
    return 0;
  }

  @Override
  public int getNumXTiles() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int getNumYTiles() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int getMinTileX() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int getMinTileY() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int getTileWidth() {
    return 1;
  }

  @Override
  public int getTileHeight() {
    return 1;
  }

  @Override
  public int getTileGridXOffset() {
    return 0;
  }

  @Override
  public int getTileGridYOffset() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public Raster getTile(int tileX, int tileY) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public Raster getData() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public Raster getData(Rectangle rect) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public WritableRaster copyData(WritableRaster raster) {
    throw new UnsupportedOperationException("TODO");
  }

  // #endregion
}
