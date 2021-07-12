package com.quiyetbrul.breakout;

import java.awt.*;
import java.util.Random;

import static com.quiyetbrul.breakout.GameMain.gameStart;

public class Brick extends GameObject {

    public static final int BRICK_WIDTH = 50;
    public static final int BRICK_HEIGHT = 20;
    public static final int BRICK_X = 15;
    public static final int BRICK_Y = 15;
    private final Handler handler;
    private final Color col;

    public Brick(int x_coordinate, int y_coordinate, ID id, Handler handler) {
        super(x_coordinate, y_coordinate, id);

        Random r = new Random();
        int blue = r.nextInt(255);
        int green = r.nextInt(255);
        int red = r.nextInt(255);
        col = new Color(red, green, blue);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x_coordinate, (int) y_coordinate, BRICK_WIDTH, BRICK_HEIGHT);
    }

    @Override
    public void tick() {
        if (gameStart == GameMain.GAME_STATE.GameOver) {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect((int) x_coordinate, (int) y_coordinate, BRICK_WIDTH, BRICK_HEIGHT);
    }
}
