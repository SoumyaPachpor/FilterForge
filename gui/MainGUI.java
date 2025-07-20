package gui;

import java.io.IOException;
import java.util.Objects;

import filters.*;
import io.*;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainGUI {
    private JFrame frame;
    private JComboBox<String> filterSelector;
    private ImagePanel originalPanel, filteredPanel;

    private BufferedImage originalImage, filteredImage;

    private final String[] filters = {
            "grayscale", "invert", "sepia", "brightness:1.2", "blur"
    };

    public MainGUI() {
        frame = new JFrame("Java Image Filter Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        originalPanel = new ImagePanel("Original");
        filteredPanel = new ImagePanel("Filtered");

        JPanel topPanel = new JPanel();
        JButton loadBtn = new JButton("Load");
        JButton applyBtn = new JButton("Apply");
        JButton saveBtn = new JButton("Save");
        JButton batchBtn = new JButton("Batch Filter");
        JButton collageBtn = new JButton("Make Collage");
        
        filterSelector = new JComboBox<>(filters);
        
        topPanel.add(batchBtn);
        topPanel.add(collageBtn);
        topPanel.add(loadBtn);
        topPanel.add(filterSelector);
        topPanel.add(applyBtn);
        topPanel.add(saveBtn);

        JPanel imagePanel = new JPanel(new GridLayout(1, 2));
        imagePanel.add(originalPanel);
        imagePanel.add(filteredPanel);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(imagePanel, BorderLayout.CENTER);

        // Actions
        loadBtn.addActionListener(e -> loadImage());
        applyBtn.addActionListener(e -> applyFilter());
        saveBtn.addActionListener(e -> saveImage());
        batchBtn.addActionListener(e -> runBatchFilter());
        collageBtn.addActionListener(e -> runCollage());

        frame.setSize(1000, 600);
        frame.setVisible(true);
    }

    private void loadImage() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                originalImage = ImageLoader.load(file.getAbsolutePath());
                originalPanel.setImage(originalImage);
                filteredPanel.setImage(null);
            } catch (Exception e) {
                showError("Failed to load image.");
            }
        }
    }

    private void applyFilter() {
        if (originalImage == null) {
            showError("No image loaded.");
            return;
        }

        String selected = (String) filterSelector.getSelectedItem();
        ImageFilter filter;

        if (selected.startsWith("brightness")) {
            float factor = 1.2f; // use float literal
            filter = new BrightnessFilter(factor);
        }
         else {
            filter = FilterMapper.getSingleFilter(selected);
        }

        if (filter == null) {
            showError("Invalid filter.");
            return;
        }

        try {
            filteredImage = filter.apply(originalImage);
            filteredPanel.setImage(filteredImage);
        } catch (Exception e) {
            showError("Filter failed.");
        }
    }

    private void saveImage() {
        if (filteredImage == null) {
            showError("Nothing to save.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                String format = "jpg";
                if (file.getName().contains(".")) {
                    format = file.getName().substring(file.getName().lastIndexOf('.') + 1);
                }
                ImageIO.write(filteredImage, format, file);
                JOptionPane.showMessageDialog(frame, "Saved!");
            } catch (Exception e) {
                showError("Save failed.");
            }
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
    private void runBatchFilter() {
    JFileChooser inputChooser = new JFileChooser();
    inputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int inRes = inputChooser.showOpenDialog(frame);
    if (inRes != JFileChooser.APPROVE_OPTION) return;

    JFileChooser outputChooser = new JFileChooser();
    outputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int outRes = outputChooser.showSaveDialog(frame);
    if (outRes != JFileChooser.APPROVE_OPTION) return;

    String selected = (String) filterSelector.getSelectedItem();
    ImageFilter filter = FilterMapper.getSingleFilter(selected);
    if (selected.startsWith("brightness")) {
        float factor = 1.2f;
        filter = new BrightnessFilter(factor);
    }
    if (filter == null) {
        showError("Select a valid filter");
        return;
    }

    BatchProcessor.processFolder(inputChooser.getSelectedFile(), outputChooser.getSelectedFile(), filter);
    JOptionPane.showMessageDialog(frame, " Batch completed!");
}

    private void runCollage() {
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = folderChooser.showOpenDialog(frame);
        if (result != JFileChooser.APPROVE_OPTION) return;

        BufferedImage collage = CollageMaker.createCollage(folderChooser.getSelectedFile());
        if (collage == null) {
            showError("Failed to make collage.");
            return;
        }

        filteredImage = collage;
        filteredPanel.setImage(collage);

        JOptionPane.showMessageDialog(frame, "✔ Collage generated! Use 'Save' to export.");
    }

}
