package com.quiyetbrul.breakout;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.quiyetbrul.breakout.GameMain.*;

public class GameMenu extends MouseAdapter {

    private final int CLICKABLE_WIDTH = 200, CLICKABLE_HEIGHT = 60;
    private final int SPACER = 5;
    private final int ROWS = 8;
    private final int BRICK_SPACE_X = Brick.BRICK_WIDTH + SPACER; // brick_width + desired space between bricks
    private final int BRICK_SPACE_Y = Brick.BRICK_HEIGHT + SPACER;
    public static int brick_count = 0;
    private final GameMain game;
    private final Handler handler;
    private final HUD hud;

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

            gameStart = GameMain.GAME_STATE.Game;

            // PADDLE
            handler.addObject(new Player(Player.PADDLE_X, Player.PADDLE_Y, ID.Player, handler));

            // ROWS OF BRICKS
            // NOTES FOR WINDOW HEIGHT
            /*
             * WINDOW_HEIGHT = WINDOW_WIDTH / 12 * 9; (algo for a 16x9 ration)
             * in this instance, WINDOW_HEIGHT is ~480
             *
             * BRICK_HEIGHT = 20
             *
             * I NEED 8 ROWS OF BRICKS -> BRICK_HEIGHT * 8 = 160 (minimum space for 8 rows of bricks needed)
             * HALF OF THE WINDOW HEIGHT IS 240
             * NEEDED SPACE OF THE FRAME IS 200 (minimum height + number of rows * spacer)
             * */

            //x_rep is where the brick starts in each row
            //y_rep is where the brick starts in each col
            for (int y_rep = Brick.BRICK_Y; y_rep < (WINDOW_HEIGHT / 2) - (ROWS * SPACER); y_rep += BRICK_SPACE_Y) {
                for (int x_rep = Brick.BRICK_X; x_rep < WINDOW_WIDTH - Brick.BRICK_WIDTH; x_rep += BRICK_SPACE_X) {
                    handler.addObject(new Brick(x_rep, y_rep, ID.Brick, handler));
                    brick_count++;
                }
            }


            // BALL STARTS ON TOP OF THE PADDLE // STATIC
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ID.Player) {
                    handler.addObject(new Ball(tempObject.getX_coordinate() + 40, tempObject.getY_coordinate() - 10, ID.Ball, handler));
                }
            }
        }

        //help button
        else if (mouseOver(mx, my, 210, 250, CLICKABLE_WIDTH, CLICKABLE_HEIGHT)) {
            gameStart = GameMain.GAME_STATE.Help;
        }

        //back button for help
        else if (gameStart == GAME_STATE.Help) {
            if (mouseOver(mx, my, 210, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT)) {
                gameStart = GAME_STATE.Menu;
            }
        }

        //try again button
        else if (gameStart == GameMain.GAME_STATE.GameOver || gameStart == GAME_STATE.GameVictory) {

            // clicks on try again
            if (mouseOver(mx, my, 210, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT)) {
                hud.setScore(0);

                gameStart = GameMain.GAME_STATE.Game;

                handler.addObject(new Player(Player.PADDLE_X, Player.PADDLE_Y, ID.Player, handler));

                for (int y_rep = Brick.BRICK_Y; y_rep < (WINDOW_HEIGHT / 2) - (ROWS * SPACER); y_rep += BRICK_SPACE_Y) {
                    for (int x_rep = Brick.BRICK_X; x_rep < WINDOW_WIDTH - Brick.BRICK_WIDTH; x_rep += BRICK_SPACE_X) {
                        handler.addObject(new Brick(x_rep, y_rep, ID.Brick, handler));
                        brick_count++;
                    }
                }

                for (int i = 0; i < handler.object.size(); i++) {
                    GameObject tempObject = handler.object.get(i);
                    if (tempObject.getId() == ID.Player) {
                        handler.addObject(new Ball(tempObject.getX_coordinate() + 40, tempObject.getY_coordinate() - 10, ID.Ball, handler));
                    }
                }
            }

            // maybe add a back-to-menu button
        }

        //quit button
        else if (gameStart == GameMain.GAME_STATE.Menu) {
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
        final Font fnt1 = new Font(fontName, Font.BOLD, 50);
        final Font fnt2 = new Font(fontName, Font.BOLD, 30);
        final Font fnt3 = new Font(fontName, Font.BOLD, 15);
        final Font fnt4 = new Font(fontName, Font.BOLD, 8);
        final Font fnt5 = new Font(fontName, Font.BOLD, 7);

        if (null != gameStart) switch (gameStart) {
            case Menu: {
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
            case Help: {
                g.setFont(fnt1);
                g.setColor(Color.WHITE);
                g.drawString("Help", 250, 70);

                g.setFont(fnt3);
                g.drawString("use the keys A and D to move the paddle.", 30, 200);
                g.drawString("dont let the ball fall through bottom edge.", 30, 220);
                g.drawString("press spacebar to launch the ball.", 30, 240);

                g.setFont(fnt4);
                g.drawString("henlo googlers plz hire me lol", 35, 310);

                g.setFont(fnt5);
                g.drawString("haah ah haha ha", 35, 320);

                g.setFont(fnt2);
                g.drawRect(210, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                g.drawString("Back", 240, 400);
                break;
            }
            case GameOver: {
                g.setFont(fnt1);
                g.setColor(Color.WHITE);
                g.drawString("Game Over", 190, 70);

                g.setFont(fnt2);
                g.drawString("You lost!", 240, 180);
                g.drawString("Score: " + hud.getScore(), 240, 250);

                g.drawRect(220, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                g.drawString("AGAIN", 230, 400);
                break;
            }
            case GameVictory: {
                g.setFont(fnt1);
                g.setColor(Color.WHITE);
                g.drawString("VICTORY!", 190, 70);

                g.setFont(fnt2);
                g.drawString("You won!", 240, 180);
                g.drawString("Score: " + hud.getScore(), 240, 250);

                g.drawRect(220, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                g.drawString("AGAIN?", 230, 400);
                break;
            }
            default:
                break;
        }
    }
}
