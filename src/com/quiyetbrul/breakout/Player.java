package com.quiyetbrul.breakout;

import static com.quiyetbrul.breakout.GameMain.*;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;
    private final int PADDLE_WIDTH = 100;
    private final int PADDLE_HEIGHT = 10;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    public void tick() {
        // so movement isn't laggy
        x += velX;

        // x-coordinate boundary
        x = GameMain.clamp((int)x, 0, GameMain.WINDOW_WIDTH - PADDLE_WIDTH);
    }

    public void render(Graphics g) {
        // renders paddle
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }
}
