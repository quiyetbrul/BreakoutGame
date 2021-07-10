package com.quiyetbrul.breakout;

import java.awt.*;
import java.awt.event.*;

import static com.quiyetbrul.breakout.GameMain.*;

public class GameMenu extends MouseAdapter {

    private GameMain game;
    private Handler handler;
    private HUD hud;

    private final int CLICKABLE_WIDTH = 200, CLICKABLE_HEIGHT = 60;

    public GameMenu(GameMain game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //play button
        if (mouseOver(mx, my, 210, 150, CLICKABLE_WIDTH, CLICKABLE_HEIGHT)) {
            game.gameStart = GameMain.GAME_STATE.Game;
            handler.addObject(new Player(PADDLE_X, PADDLE_Y, ID.Player, handler));
            handler.addObject(new Ball(BALL_X, BALL_Y, ID.Ball, handler));
        }

        //help button
        else if (mouseOver(mx, my, 210, 250, CLICKABLE_WIDTH, CLICKABLE_HEIGHT)) {
            game.gameStart = GameMain.GAME_STATE.Help;
        }

        //back button for help
        else if (game.gameStart == GAME_STATE.Help) {
            if (mouseOver(mx, my, 210, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT)) {
                game.gameStart = GAME_STATE.Menu;
            }
        }
        
        //try again button
        else if (game.gameStart == GameMain.GAME_STATE.GameOver || game.gameStart == GAME_STATE.GameVictory) {

            // clicks on try again
            if (mouseOver(mx, my, 210, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT)) {
                hud.setScore(0);
                game.gameStart = GameMain.GAME_STATE.Game;
                handler.addObject(new Player(PADDLE_X, PADDLE_Y, ID.Player, handler));
                handler.addObject(new Ball(BALL_X, BALL_Y, ID.Ball, handler));
            }

            // maybe add a back-to-menu button
        }

        //quit button
        else if (game.gameStart == GameMain.GAME_STATE.Menu) {
            if (mouseOver(mx, my, 210, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT)) {
                System.exit(0);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    // hover area
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            return my > y && my < y + height;
        } else {
            return false;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {

        if (null != game.gameStart) switch (game.gameStart) {
            case Menu:{

                Font fnt = new Font("Arial", 1, 50);
                Font fnt2 = new Font("Arial", 1, 30);
                
                g.setFont(fnt);
                g.setColor(Color.WHITE);
                g.drawString("BREAKOUT", 190, 70);
                
                g.setFont(fnt2);
                g.drawRect(220, 150, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                g.drawString("Play", 230, 200);
                g.drawRect(220, 250, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                g.drawString("Help", 230, 300);

                g.drawRect(220, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                g.drawString("Esc = QUIT", 230, 400);
                    break;
                }
            case Help:{
                Font fnt = new Font("Arial", 1, 50);
                Font fnt2 = new Font("Arial", 1, 15);
                Font fnt3 = new Font("Arial", 1, 30);
                Font fnt4 = new Font("Arial", 1, 8);
                Font fnt5 = new Font("Arial", 1, 7);
                
                g.setFont(fnt);
                g.setColor(Color.WHITE);
                g.drawString("Help", 250, 70);
                
                g.setFont(fnt2);
                g.drawString("use the keys A and D to move the paddle.", 30, 200);
                g.drawString("dont let the ball fall through bottom edge.", 30, 220);

                g.setFont(fnt4);
                g.drawString("henlo googlers plz hire me lol", 35, 310);
                
                g.setFont(fnt5);
                g.drawString("haah ah haha ha", 35, 320);
                
                g.setFont(fnt3);
                g.drawRect(210, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                g.drawString("Back", 240, 400);
                    break;
                }
            case GameOver:{
                Font fnt = new Font("Arial", 1, 50);
                Font fnt2 = new Font("Arial", 1, 30);
                
                g.setFont(fnt);
                g.setColor(Color.WHITE);
                g.drawString("Game Over", 190, 70);
                
                g.setFont(fnt2);
                g.drawString("You lost!", 240, 180);
                g.drawString("Score: " + hud.getScore(), 240, 250);
                
                g.drawRect(220, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                g.drawString("TRY AGAIN", 230, 400);
                    break;
                }
            default:
                break;
        }
    }
}
