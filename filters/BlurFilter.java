package filters;

import java.awt.image.BufferedImage;

public class BlurFilter implements ImageFilter {
    @Override
    public BufferedImage apply(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, image.getType());

        int[][] kernel = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        int kernelSum = 9;

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int r = 0, g = 0, b = 0;

                for (int ky = -1; ky <= 1; ky++) {
                    for (int kx = -1; kx <= 1; kx++) {
                        int p = image.getRGB(x + kx, y + ky);
                        r += ((p >> 16) & 0xff) * kernel[ky + 1][kx + 1];
                        g += ((p >> 8) & 0xff) * kernel[ky + 1][kx + 1];
                        b += (p & 0xff) * kernel[ky + 1][kx + 1];
                    }
                }

                r /= kernelSum;
                g /= kernelSum;
                b /= kernelSum;

                int a = (image.getRGB(x, y) >> 24) & 0xff;
                int blurred = (a << 24) | (r << 16) | (g << 8) | b;
                result.setRGB(x, y, blurred);
            }
        }

        return result;
    }
}
