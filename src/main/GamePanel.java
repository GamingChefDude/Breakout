package main;

import javax.swing.*;
import java.awt.*;

import entity.Ball;
import entity.Blocks;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {
    Player player = new Player();
    Ball ball = new Ball();
    Blocks[][] blocks = new Blocks[9][12];

    int blockPosX;
    int blockPosY;

    public static int width = 800;
    public static int height = 600;
    int fps = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(keyH);

        for (int row = 2; row < 9; row++) {
            for (int col = 0; col < 12; col++) {
                blockPosX = col * (Blocks.blockWidth + Blocks.padding);
                blockPosY = row * (Blocks.blockHeight + Blocks.padding);
                blocks[row][col] = new Blocks(blockPosY, blockPosX);
            }
        }
    }

    public void startGameThread() {
        // starting the gameThread
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // the main gameLoop
        // running update and repaint 60 times a second because fps = 60

        // making variables for delta time / the fps
        double drawInterval = (double) 1000000000 /fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            // making the update and repaint happen with the frames
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public interface GameObject {
        int getX();
        int getY();
        int getWidth();
        int getHeight();
    }

    public boolean checkCollision(GameObject a, GameObject b) {
        return a.getX() < b.getX() + b.getWidth() &&
                a.getX() + a.getWidth() > b.getX() &&
                a.getY() < b.getY() + b.getHeight() &&
                a.getY() + a.getHeight() > b.getY();
    }

    public void collision() {
        ball.velocityY = -ball.velocityY;
        ball.posY += ball.velocityY;
    }

    void update() {
        player.update();
        ball.update();

        if (checkCollision(player, ball)) {
            collision();
        }
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[row].length; col++) {
                Blocks block = blocks[row][col];
                if (block != null && block.visible) {
                    if (checkCollision(ball, block)) {
                        collision();
                        block.visible = false;
                    }
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        ball.draw(g);

        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[row].length; col++) {
                Blocks b = blocks[row][col];
                if (b != null && b.visible) {
                    b.draw(g);
                }
            }
        }
        g.dispose();
    }
}