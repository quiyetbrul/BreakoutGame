package com.quiyetbrul.breakout;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private final Handler handler;
    private final boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;

        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                switch (key) {
                    case KeyEvent.VK_D:
                        tempObject.setVelX(7);
                        keyDown[1] = true;
                        break;
                    case KeyEvent.VK_A:
                        tempObject.setVelX(-7);
                        keyDown[2] = true;
                        break;
                    default:
                        break;
                }
            }

            if (tempObject.getId() == ID.Ball) {
                if (key == KeyEvent.VK_SPACE) keyDown[3] = true;///{ball.setVelX(5);ball.setVelY(5);}
            }
        }

        // shortcut to gameplay buggy
        // i dont want to add game objects here again
//        if(key == KeyEvent.VK_ENTER){
//            game.gameStart = GameMain.GAME_STATE.Game;
//        }


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
            if (tempObject.getId() == ID.Ball) {
                if (key == KeyEvent.VK_SPACE) {
                    keyDown[3] = false;
                    // so ball doesn't go to the same direction each start
                    int rand = Math.round(Math.random()) == 1 ? -5 : 5;
                    tempObject.setVelX(rand);
                    tempObject.setVelY(5);
                }
            }
        }
    }
}
