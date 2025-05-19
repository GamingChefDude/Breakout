package main;

import java.awt.*;

public class Screens {
    public void startPageDraw(Graphics g) {
        if (!KeyHandler.start) {
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Breakout" , GamePanel.width / 2 - 100, GamePanel.height / 2 / 2);

            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press Space to start" , GamePanel.width / 2 - 105, GamePanel.height / 2 + 50);
        }
    }

    public void victoryDraw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Victory", GamePanel.width / 2 - 60, GamePanel.height / 2 / 2);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Press ESC to quit", GamePanel.width / 2 - 85, GamePanel.height / 2 + 50);
    }
    public void deathDraw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Game Over", GamePanel.width / 2 - 80, GamePanel.height / 2 / 2);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Press ESC to quit", GamePanel.width / 2 - 85, GamePanel.height / 2 + 50);
    }
}
