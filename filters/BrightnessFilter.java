package filters;

import java.awt.image.BufferedImage;

public class BrightnessFilter implements ImageFilter {
    private final float factor;

    public BrightnessFilter(float factor) {
        this.factor = factor; // e.g., 1.2 for +20%, 0.8 for -20%
    }

    @Override
    public BufferedImage apply(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int p = image.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = Math.min(255, (int)(((p >> 16) & 0xff) * factor));
                int g = Math.min(255, (int)(((p >> 8) & 0xff) * factor));
                int b = Math.min(255, (int)((p & 0xff) * factor));

                int bright = (a << 24) | (r << 16) | (g << 8) | b;
                result.setRGB(x, y, bright);
            }
        }

        return result;
    }
}
