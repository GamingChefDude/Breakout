package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player implements GamePanel.GameObject {
    static int speed = 8;
    public static int width = 200;
    public static int height = 20;
    public static int posX = GamePanel.width / 2 - width / 2;
    public static int posY = GamePanel.height - height;

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

    public int getX() {return posX;}
    public int getY() {return posY;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
}