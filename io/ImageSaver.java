package io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSaver {
    public static void save(BufferedImage image, String format, String outputPath) throws IOException {
        ImageIO.write(image, format, new File(outputPath));
    }
}
