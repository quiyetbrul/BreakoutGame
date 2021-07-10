package com.quiyetbrul.breakout;

import java.awt.*;

public class Ball extends GameObject{

    private Handler handler;
    private final int BALL_WIDTH = 10;
    private final int BALL_HEIGHT = 10;

    public Ball(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

//        setVelX(5); setVelY(5);
        velX = 5; velY = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, BALL_WIDTH, BALL_HEIGHT);
    }


    //// LOSE LIFE
    // ball x-coordinate < 0 && y-coodrinate > WINDOW_HEIGTH || x-coordinate > WINDOW_WIDTH && y-coodrinate > WINDOW_HEIGTH
    // LIVES-=1;

    //// BALL BOUNCE
    // ball x-coordinate == PADDLE_X && y-coordinate = PADDLE_Y
    // ball bounces;

    @Override
    public void tick() {
        // updates x,y coordinates in accordance to velocity
        x += velX;
        y += velY;

        // bounce on ALL FOUR WALLS for now
        if(y<=0 || y >= GameMain.WINDOW_HEIGHT - 50) velY *= -1;
        if(x<=0 || x >= GameMain.WINDOW_WIDTH - 20) velX *= -1;

        // add bounce on paddle

//        handler.addObject(new RenderObject((int)x, (int)y, ID.Ball, Color.WHITE, BALL_WIDTH, BALL_HEIGHT, handler));
    }

    @Override
    public void render(Graphics g) {
        g.fillOval((int)x, (int)y, BALL_WIDTH, BALL_HEIGHT);
    }
}
