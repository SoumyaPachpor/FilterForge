package gui;

import filters.ImageFilter;
import io.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BatchProcessor {
    public static void processFolder(File inputDir, File outputDir, ImageFilter filter) {
        if (!outputDir.exists()) outputDir.mkdirs();

        File[] files = inputDir.listFiles((dir, name) -> {
            String lc = name.toLowerCase();
            return lc.endsWith(".jpg") || lc.endsWith(".png") || lc.endsWith(".jpeg");
        });

        if (files == null) return;

        for (File file : files) {
            try {
                BufferedImage img = ImageLoader.load(file.getAbsolutePath());
                BufferedImage result = filter.apply(img);

                String format = getExtension(file.getName());
                File outFile = new File(outputDir, file.getName());
                ImageIO.write(result, format, outFile);

                System.out.println("Filtered: " + file.getName());
            } catch (Exception e) {
                System.err.println("Failed: " + file.getName());
            }
        }
    }

    private static String getExtension(String filename) {
        int dot = filename.lastIndexOf('.');
        return (dot >= 0) ? filename.substring(dot + 1).toLowerCase() : "jpg";
    }
}
