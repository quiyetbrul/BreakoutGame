package com.quiyetbrul.breakout;

import static com.quiyetbrul.breakout.GameMain.*;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;
    private final int PADDLE_WIDTH = 120;
    private final int PADDLE_HEIGHT = 30;

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
        
        //player character
        handler.addObject(new RenderObject((int)x, 0, ID.Player, Color.white, PADDLE_X, PADDLE_Y, handler));

//        collision();
    }

    // ball y-coordinate < y -> LIVES-=1
//    private void collision(){
//        for(int i = 0; i < handler.object.size(); i++){
//            GameObject player = handler.object.get(i);
//
//            if(player.getId() == ID.EnemyBasic
//                    || player.getId() == ID.EnemyFast
//                    || player.getId() == ID.EnemySmart
//                    || player.getId() == ID.EnemyBoss){
//                if(getBounds().intersects(player.getBounds())){
//                    HUD.LIVES -= 1;
//                }
//            }
//        }
//    }

    public void render(Graphics g) {
        // renders paddle
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }
}
