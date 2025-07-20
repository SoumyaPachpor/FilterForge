package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private JLabel label;

    public ImagePanel(String title) {
        setLayout(new BorderLayout());
        label = new JLabel(title, SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder(title));
    }

    public void setImage(BufferedImage img) {
        if (img != null) {
            Image scaled = img.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaled));
            label.setText("");
        } else {
            label.setIcon(null);
            label.setText("No Image");
        }
    }
}
