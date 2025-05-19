package entity;

import main.GamePanel;
import java.awt.*;

/*
    handles Block
    Makes block and get it ready for the array in GamePanel class
 */

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

    public void draw(Graphics g) {
        // draw only the visible
        // easiest way to break them
        if (visible) {
            g.setColor(new Color(255, 50, 50));
            g.fillRect(posX, posY, blockWidth, blockHeight);
        }
    }

    // get things for collision
    public int getX() {return posX;}
    public int getY() {return posY;}
    public int getWidth() {return blockWidth;}
    public int getHeight() {return blockHeight;}
}