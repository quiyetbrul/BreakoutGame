package com.quiyetbrul.breakout;

import java.awt.event.*;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;

        keyDown[1] = false;
        keyDown[2] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                switch (key) {
                    case KeyEvent.VK_D: tempObject.setVelX(5); keyDown[1] = true; break;
                    case KeyEvent.VK_A: tempObject.setVelX(-5); keyDown[2] = true; break;
                    default: break;
                }
            }
        }

        // escape the game anytime
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_D) {
                    keyDown[1] = false;
                }
                if (key == KeyEvent.VK_A) {
                    keyDown[2] = false;
                }

                if (!keyDown[1] && !keyDown[2]) {
                    tempObject.setVelX(0);
                }
            }
        }
    }
}
