package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.*;

/*
    Handle player
    Making the player and moves it
 */

public class Player implements GamePanel.GameObject {
    int velocityXLeft;
    int velocityXRight;
    public int width = 200;
    public int height = 20;
    public int posX;
    public int posY;

    public void update() {
        // move Player
        if (KeyHandler.left) {
            posX -= velocityXLeft;
        } else if (KeyHandler.right) {
            posX += velocityXRight;
        }

        // window-edge collision
        {
            if (posX <= 0) {
                velocityXLeft = 0;
            } else {
                velocityXLeft = 8;
            }
            if (posX >= GamePanel.width - width) {
                velocityXRight = 0;
            } else {
                velocityXRight = 8;
            }
        }
    }

    public void draw(Graphics g) {
        // draw player
        g.setColor(Color.darkGray);
        g.fillRect(posX, posY, width, height);
    }

    // get things for collision
    public int getX() {return posX;}
    public int getY() {return posY;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
}