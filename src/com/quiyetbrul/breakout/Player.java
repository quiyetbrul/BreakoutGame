package com.quiyetbrul.breakout;

import java.awt.*;

import static com.quiyetbrul.breakout.GameMain.GAME_STATE;
import static com.quiyetbrul.breakout.GameMain.gameStart;

public class Player extends GameObject {

    // paddle starting point
    public static final float PADDLE_X = 250;
    public static final float PADDLE_Y = 410;
    private final int PADDLE_WIDTH = 100;
    private final int PADDLE_HEIGHT = 10;
    private final Handler handler;

    public Player(float x_coordinate, float y_coordinate, ID id, Handler handler) {
        super(x_coordinate, y_coordinate, id);

        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x_coordinate, (int) y_coordinate, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    public void tick() {
        // so movement isn't laggy
        // x-coord vel is set in KeyInput
        x_coordinate += getVelX();

        // x-coordinate boundary
        x_coordinate = GameMain.clamp((int) x_coordinate, 0, GameMain.WINDOW_WIDTH - PADDLE_WIDTH);

        // removes paddle when GameOver screen shows up
        if (gameStart == GAME_STATE.GameOver || gameStart == GAME_STATE.GameVictory) {
            handler.removeObject(this);
        }
    }

    // renders paddle
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) x_coordinate, (int) y_coordinate, PADDLE_WIDTH, PADDLE_HEIGHT);
    }
}
