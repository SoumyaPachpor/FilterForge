package gui;

import io.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CollageMaker {
    public static BufferedImage createCollage(File folder) {
        File[] images = folder.listFiles((dir, name) -> {
            String lc = name.toLowerCase();
            return lc.endsWith(".jpg") || lc.endsWith(".png") || lc.endsWith(".jpeg");
        });

        if (images == null || images.length == 0) return null;

        int gridSize = (int) Math.ceil(Math.sqrt(images.length));
        int tileSize = 200;
        BufferedImage collage = new BufferedImage(
                tileSize * gridSize,
                tileSize * gridSize,
                BufferedImage.TYPE_INT_RGB
        );
        Graphics2D g = collage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, collage.getWidth(), collage.getHeight());

        int x = 0, y = 0;
        for (File imgFile : images) {
            try {
                BufferedImage img = ImageLoader.load(imgFile.getAbsolutePath());
                Image scaled = img.getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH);
                g.drawImage(scaled, x * tileSize, y * tileSize, null);
                x++;
                if (x == gridSize) {
                    x = 0;
                    y++;
                }
            } catch (Exception e) {
                System.err.println("Failed to load: " + imgFile.getName());
            }
        }
        g.dispose();
        return collage;
    }
}
