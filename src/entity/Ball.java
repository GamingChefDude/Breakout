package entity;

import main.GamePanel;

import java.awt.*;

public class Ball {
    int width = 10;
    int height = 10;
    int posX = GamePanel.width / 2 - width / 2;
    int posY = GamePanel.height / 2 - height / 2 - 20;
    int velocityX = 5;
    int velocityY = 7;

    public void update() {
        posY += velocityY;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(posX, posY, width, height);
    }

}
