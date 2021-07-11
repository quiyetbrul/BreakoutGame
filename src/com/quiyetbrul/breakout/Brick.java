package com.quiyetbrul.breakout;

import java.awt.*;

public class Brick extends GameObject{


    private Handler handler;
    private Player player;
    private HUD hud;

    public Brick(int x_coordinate, int y_coordinate, ID id, Handler handler) {
        super(x_coordinate, y_coordinate, id);

        this.handler = handler;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void tick() {
        // for (int i = 5; i < WINDOW_WIDTH -5; i++)
        // add each brick
        //
    }

    @Override
    public void render(Graphics g) {

    }
}
