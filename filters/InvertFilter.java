package filters;

import java.awt.image.BufferedImage;

public class InvertFilter implements ImageFilter {
    @Override
    public BufferedImage apply(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int p = image.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = 255 - ((p >> 16) & 0xff);
                int g = 255 - ((p >> 8) & 0xff);
                int b = 255 - (p & 0xff);

                int inverted = (a << 24) | (r << 16) | (g << 8) | b;
                result.setRGB(x, y, inverted);
            }
        }

        return result;
    }
}
