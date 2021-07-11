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
            hud.setScore(0);
            game.gameStart = GameMain.GAME_STATE.Game;
            handler.addObject(new Player(PADDLE_X, PADDLE_Y, ID.Player, handler));
            handler.addObject(new Ball(PADDLE_X+40, PADDLE_Y-10, ID.Ball, handler));
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
    private boolean mouseOver(int mx, int my, int x_coordinate, int y_coordinate, int width, int height) {
        if (mx > x_coordinate && mx < x_coordinate + width) {
            return my > y_coordinate && my < y_coordinate + height;
        } else {
            return false;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {

        final String fontName = "Arial";
        final int style = 1;
        final Font fnt1 = new Font(fontName, style, 50);
        final Font fnt2 = new Font(fontName, style, 30);
        final Font fnt3 = new Font(fontName, style, 15);
        final Font fnt4 = new Font(fontName, style, 8);
        final Font fnt5 = new Font(fontName, style, 7);

        if (null != game.gameStart) switch (game.gameStart) {
            case Menu:{
                g.setFont(fnt1);
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
                g.setFont(fnt1);
                g.setColor(Color.WHITE);
                g.drawString("Help", 250, 70);
                
                g.setFont(fnt3);
                g.drawString("use the keys A and D to move the paddle.", 30, 200);
                g.drawString("dont let the ball fall through bottom edge.", 30, 220);

                g.setFont(fnt4);
                g.drawString("henlo googlers plz hire me lol", 35, 310);
                
                g.setFont(fnt5);
                g.drawString("haah ah haha ha", 35, 320);
                
                g.setFont(fnt2);
                g.drawRect(210, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                g.drawString("Back", 240, 400);
                    break;
                }
            case GameOver:{
                g.setFont(fnt1);
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
