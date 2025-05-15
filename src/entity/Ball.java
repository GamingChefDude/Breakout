package entity;

import main.GamePanel;
import java.awt.*;

public class Ball implements GamePanel.GameObject {
    public int width = 10;
    public int height = 10;
    public int posX = GamePanel.width / 2 - width / 2;
    public int posY = GamePanel.height - 50;
    public int velocityX = 5;
    public int velocityY = 7;

    public void update() {
        if (posY <= 0) {
            velocityY = -velocityY;
            posY += velocityY;
        }
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

    public int getX() {return posX;}
    public int getY() {return posY;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}

}