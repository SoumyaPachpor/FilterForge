package io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage load(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
}
