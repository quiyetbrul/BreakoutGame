package com.quiyetbrul.breakout;

import java.awt.*;

public class HUD {
    
    public static int LIVES = 5;
    private int score = 0;
    
    public void tick(){
        LIVES = GameMain.clamp(LIVES, 0, 5);
        // create  collision. once ball bounces, brick disappears and score goes up by one
        // score++;
    }
    
    public void render (Graphics g){
        g.setColor(Color.GREEN);
        g.drawString("LIVES: " + LIVES + "     SCORE: " + getScore(), 15, 25);
    }
    
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    
}
