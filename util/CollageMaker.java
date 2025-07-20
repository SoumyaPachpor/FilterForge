package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CollageMaker {

    public static void createCollage(String folderPath, String outputPath) throws Exception {
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png")
        );

        if (files == null || files.length == 0) {
            System.out.println("[ERROR] No images found in: " + folderPath);
            return;
        }

        List<BufferedImage> images = new ArrayList<>();
        int tileWidth = 0, tileHeight = 0;

        for (File file : files) {
            BufferedImage img = ImageIO.read(file);
            if (img != null) {
                images.add(img);
                tileWidth = Math.max(tileWidth, img.getWidth());
                tileHeight = Math.max(tileHeight, img.getHeight());
            }
        }

        int count = images.size();
        int gridCols = (int) Math.ceil(Math.sqrt(count));
        int gridRows = (int) Math.ceil((double) count / gridCols);

        BufferedImage collage = new BufferedImage(
                gridCols * tileWidth,
                gridRows * tileHeight,
                BufferedImage.TYPE_INT_RGB
        );

        Graphics2D g = collage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, collage.getWidth(), collage.getHeight());

        for (int i = 0; i < images.size(); i++) {
            int row = i / gridCols;
            int col = i % gridCols;
            g.drawImage(images.get(i), col * tileWidth, row * tileHeight, null);
        }

        g.dispose();

        String format = outputPath.substring(outputPath.lastIndexOf('.') + 1);
        ImageIO.write(collage, format, new File(outputPath));
        System.out.println("✅ Collage saved as: " + outputPath);
    }
}
