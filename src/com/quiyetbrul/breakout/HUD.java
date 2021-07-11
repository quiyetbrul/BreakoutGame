package com.quiyetbrul.breakout;

import java.awt.*;

public class HUD {
    
    public static int LIVES = 5;
    public static int score = 0;

    public static float HUD_X = 15;
    public static float HUD_Y = 440;
    
    public void tick(){
    }
    
    public void render (Graphics g){
        g.setColor(Color.GREEN);
        g.drawString("LIVES: " + LIVES + "     SCORE: " + getScore(), (int)HUD_X, (int)HUD_Y);
    }
    
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    
}
