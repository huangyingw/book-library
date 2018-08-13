package com.study.BookLibrary.converter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.stereotype.Component;

@Component
public class ImageResizer {

  private static int WIDTH = 200;
  private static int HEIGHT = 200;
  private static String formatName = "jpg";

  public ImageResizer() {
  }

  //Dwuliniowe
  //868ms, 6242
  public byte[] bilinearResize(byte[] imageDataFile) throws IOException {
    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageDataFile));
    BufferedImage bufferedThumbnail = Thumbnails.of(bufferedImage).size(WIDTH, HEIGHT).asBufferedImage();

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedThumbnail, formatName, outputStream);

    return outputStream.toByteArray();
  }

  //Bikubiczny
  //1600, 6447ms
  public byte[] bicubicResize(byte[] imageDataFile) throws IOException {
    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageDataFile));
    BufferedImage bufferedThumbnail = Scalr
        .resize(bufferedImage, Method.QUALITY, Mode.FIT_TO_WIDTH, WIDTH, HEIGHT,
            Scalr.OP_ANTIALIAS);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedThumbnail, formatName, outputStream);

    return outputStream.toByteArray();
  }
}
