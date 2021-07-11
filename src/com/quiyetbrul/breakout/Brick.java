package com.quiyetbrul.breakout;

import java.awt.*;

import static com.quiyetbrul.breakout.GameMain.*;

public class Brick extends GameObject{


    private Handler handler;
    private Player player;
    private HUD hud;

    public final int BRICK_WIDTH = 50;
    public final int BRICK_HEIGHT = 20;

    public static int BRICK_X = 15;
    public static int BRICK_Y = 15;

    public Brick(int x_coordinate, int y_coordinate, ID id, Handler handler) {
        super(x_coordinate, y_coordinate, id);

        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x_coordinate, (int)y_coordinate, BRICK_WIDTH, BRICK_HEIGHT);
    }

    @Override
    public void tick() {
        if(gameStart == GameMain.GAME_STATE.GameOver) {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) x_coordinate, (int) y_coordinate, BRICK_WIDTH, BRICK_HEIGHT);
    }
}
