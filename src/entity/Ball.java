package entity;

import main.GamePanel;
import java.awt.*;

/*
    Handle Ball
    Makes the ball and moves it
 */

public class Ball implements GamePanel.GameObject {
    public int width = 10;
    public int height = 10;
    public int posX;
    public int posY;
    public int velocityX = 5;
    public int velocityY = 7;


    public void update() {
        // bounce of the roof
        if (posY <= 0) {
            velocityY = -velocityY;
            posY += velocityY;
        }
        // bounce on the walls
        if (posX <= 0 || posX >= GamePanel.width - width) {
            velocityX = -velocityX;
            posX += velocityX;
        }
        posY += velocityY;
        posX += velocityX;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(posX, posY, width, height);
    }

    // get things for collision
    public int getX() {return posX;}
    public int getY() {return posY;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}

}