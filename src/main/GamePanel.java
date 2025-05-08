package main;

import javax.swing.*;
import java.awt.*;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {
    Player player = new Player();

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

    void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Player.draw(g);

        g.dispose();
    }
}