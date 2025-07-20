package io;

import filters.*;
import java.util.*;
import java.io.*;

public class FilterMapper {
    public static Map<String, ImageFilter> loadFilterMap(String path) throws IOException {
        Map<String, ImageFilter> map = new LinkedHashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length != 2) continue;
            String filterName = parts[0].trim().toLowerCase();
            String fileName = parts[1].trim();
            map.put(fileName, getFilter(filterName));
        }
        reader.close();
        return map;
    }

    private static ImageFilter getFilter(String name) {
        if (name.startsWith("brightness:")) {
            float factor = Float.parseFloat(name.split(":")[1]);
            return new BrightnessFilter(factor);
        }
        return switch (name) {
            case "grayscale" -> new GrayscaleFilter();
            case "invert" -> new InvertFilter();
            case "sepia" -> new SepiaFilter();
            case "blur" -> new BlurFilter();
            default -> null;
        };
    }
    public static ImageFilter getSingleFilter(String name) {
        if (name.startsWith("brightness:")) {
            float factor = Float.parseFloat(name.split(":")[1]);
            return new BrightnessFilter(factor);
        }
        return switch (name) {
            case "grayscale" -> new GrayscaleFilter();
            case "invert" -> new InvertFilter();
            case "sepia" -> new SepiaFilter();
            case "blur" -> new BlurFilter();
            default -> null;
        };
    }
}

