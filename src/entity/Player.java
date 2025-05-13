package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player {
    static int speed = 7;
    static int width = 200;
    static int height = 20;
    static int posX = GamePanel.width / 2 - width / 2;
    static int posY = GamePanel.height - height;

    public void update() {
        if (KeyHandler.left) {
            posX -= speed;
        } else if (KeyHandler.right) {
            posX += speed;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(posX, posY, width, height);
    }
}