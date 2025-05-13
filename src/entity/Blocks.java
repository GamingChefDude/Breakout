package entity;

import java.awt.*;

public class Blocks {
    int width;
    int height;
    int posY;
    int posX;
    public boolean visible = true;

    public Blocks(int posY, int posX, int width, int height) {
        this.posY = posY;
        this.posX = posX;
        this.width = width;
        this.height = height;
    }

    public void update() {

    }

    public void draw(Graphics g) {
        if (visible) {
            g.setColor(new Color(255, 50, 50));
            g.fillRect(posX, posY, width, height);
        }
    }
}