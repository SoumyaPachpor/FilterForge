package filters;

import java.awt.image.BufferedImage;

public class GrayscaleFilter implements ImageFilter {
    @Override
    public BufferedImage apply(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int p = image.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                int avg = (r + g + b) / 3;

                int gray = (a << 24) | (avg << 16) | (avg << 8) | avg;
                result.setRGB(x, y, gray);
            }
        }

        return result;
    }
}
