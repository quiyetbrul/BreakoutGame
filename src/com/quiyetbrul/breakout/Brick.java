package com.quiyetbrul.breakout;

import java.awt.*;

public class Brick extends GameObject{


    private Handler handler;
    private Player player;
    private HUD hud;

    public Brick(int x_coordinate, int y_coordinate, ID id, Handler handler) {
        super(x_coordinate, y_coordinate, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
