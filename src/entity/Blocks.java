package entity;

import main.GamePanel;
import java.awt.*;

public class Blocks implements GamePanel.GameObject {
    public static int padding= 8;
    public static int blockWidth = 60; // 90
    public static int blockHeight = 20; // 25
    public int posY;
    public int posX;
    public boolean visible = true;

    public Blocks(int posY, int posX) {
        this.posY = posY;
        this.posX = posX;
    }

    public void update() {

    }

    public void draw(Graphics g) {
        if (visible) {
            g.setColor(new Color(255, 50, 50));
            g.fillRect(posX, posY, blockWidth, blockHeight);
        }
    }

    public int getX() {return posX;}
    public int getY() {return posY;}
    public int getWidth() {return blockWidth;}
    public int getHeight() {return blockHeight;}
}