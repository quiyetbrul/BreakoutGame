package com.quiyetbrul.breakout;

import static com.quiyetbrul.breakout.GameMain.*;

import java.awt.*;

public class Ball extends GameObject{

    private Handler handler;
    private Player player;
    private HUD hud;

    // ball starting point
    /*
     * how to make it dynamic? ball goes where paddle is
     * */
    // redundant since paddle x,y coordinates are used
    // public static float BALL_X = 310;
    // public static float BALL_Y = 250;

    private final int BALL_WIDTH = 10;
    private final int BALL_HEIGHT = 10;

    public Ball(float x_coordinate, float y_coordinate, ID id, Handler handler) {
        super(x_coordinate, y_coordinate, id);

        this.handler = handler;

        setVelX(0); setVelY(0);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x_coordinate, (int) y_coordinate, BALL_WIDTH, BALL_HEIGHT);
    }

    @Override
    public void tick() {
        // updates x,y coordinates in accordance to velocity
        x_coordinate += getVelX();
        y_coordinate += getVelY();

        // PROBLEM: ball only gets redirected in a slant direction

        // hits and destroys bricks
        // adds score
        hitBrick();

        // bounce on LTR edge for now
        bounceOnWall();
        // bounce on paddle
        bounceOnPaddle();

        // each time ball goes beyond bottom edge, ball gets destroyed
        // lives > 0 ? add ball
        ballFalls();

    }

    private void hitBrick(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Brick){
                if(getBounds().intersects(tempObject.getBounds())){
                    // velX *= -1; // keep velX, change velY -> makes physics more natural
                    velY *= -1;

                    // destroy brick once hit
                    handler.removeObject(tempObject);

                    // add score
                    // this does not work
                    // int score; score+1;
                    // hud.setScore(score);
                    hud.score++; // once setScore is figured out, change score to private in HUD
                }
            }
        }
    }

    private void ballFalls(){
        if(y_coordinate >= WINDOW_HEIGHT) {
            hud.LIVES -= 1;
            handler.removeObject(this);
            if(hud.LIVES > 0)
                for(int i = 0; i < handler.object.size(); i++) {
                    GameObject tempObject = handler.object.get(i);
                    if (tempObject.getId() == ID.Player) {
                        handler.addObject(new Ball(tempObject.getX_coordinate() + 40, tempObject.getY_coordinate() - 10, ID.Ball, handler));
                    }
                }
            if(gameStart == GAME_STATE.GameOver)
                handler.removeObject(this);
        }
    }

    private void bounceOnWall(){
        // clamp not working when the ball falls through bottom edge
        // x_coordinate = GameMain.clamp((int) x_coordinate, 0, WINDOW_WIDTH - BALL_WIDTH);
        // y_coordinate = GameMain.clamp((int) y_coordinate, 0, WINDOW_HEIGHT - BALL_HEIGHT);

        // remove || y_coordinate >= WINDOW_HEIGHT - 50 to remove bottom wall
        if(y_coordinate <=0 /*|| y_coordinate >= WINDOW_HEIGHT - 50*/) velY *= -1;
        if(x_coordinate <=0 || x_coordinate >= WINDOW_WIDTH - 20) velX *= -1;
    }

    private void bounceOnPaddle(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player){
                if(getBounds().intersects(tempObject.getBounds())){
                    // velX *= -1; // keep velX, change velY -> makes physics more natural
                    velY *= -1;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.PINK);
        g.fillOval((int) x_coordinate, (int) y_coordinate, BALL_WIDTH, BALL_HEIGHT);
    }
}
