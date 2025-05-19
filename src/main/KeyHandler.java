package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
    Handles key input for the player
    Moves Left and Right
 */

public class KeyHandler implements KeyListener {
    public static boolean left, right;

    // don't use
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A){
            left = true;
        } else if (key == KeyEvent.VK_D) {
            right = true;
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A){
            left = false;
        } else if (key == KeyEvent.VK_D) {
            right = false;
        }
    }
}