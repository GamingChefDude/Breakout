package main;

import javax.swing.*;
import java.awt.*;
import entity.Ball;
import entity.Blocks;
import entity.Player;

/*
    Handles Game loop, updating entities and paint components
*/

public class GamePanel extends JPanel implements Runnable {
    Player player = new Player();
    Ball ball = new Ball();

    int rows = 1;
    int cols = 1;
    Blocks[][] blocks = new Blocks[rows][cols];

    Screens screen = new Screens();

    int blockPosX;
    int blockPosY;

    public static int width = 800;
    public static int height = 600;
    int fps = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(255, 255, 255, 100));
        this.setFocusable(true);
        this.addKeyListener(keyH);

        // places all the blocks from the array
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
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
        // running update and repaint the fps

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
        // get things for collision
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

    boolean victory;
    boolean death;

    public void checkVictoryCondition() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (blocks[r][c].visible) {
                    return; // Still some left
                }
            }
        }
        victory = true;
        setBackground(new Color(100, 255, 100, 100));
    }

    void update() {
        player.update();
        ball.update();

        if (checkCollision(player, ball)) {
            collision();
        }

        // collision for ball and blocks
        for (Blocks[] value : blocks) {
            for (Blocks block : value) {
                if (block != null && block.visible) {
                    if (checkCollision(ball, block)) {
                        collision();
                        block.visible = false;
                    }
                }
            }
        }
        // check for death
        if (ball.posY > height + ball.height) {
            death = true;
            setBackground(new Color(255, 100, 100, 200 ));
        }

        checkVictoryCondition();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw entities
        {
            player.draw(g);
            ball.draw(g);

            // paint the array of blocks
            for (Blocks[] block : blocks) {
                for (Blocks b : block) {
                    if (b != null && b.visible) {
                        b.draw(g);
                    }
                }
            }
        }

        // draw screens
        {
            if (victory) {
                screen.victoryDraw(g);
            } else if (death) {
                screen.deathDraw(g);
            }
        }
        g.dispose();
    }
}