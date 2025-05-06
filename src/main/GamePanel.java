package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    int width = 800;
    int height = 600;

    public GamePanel() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
 }
}
