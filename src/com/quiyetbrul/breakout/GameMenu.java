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
            gameStart();

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
                HUD.LIVES = 5;
                gameStart();
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
                addDrawString(g, fnt1, Color.WHITE, "BREAKOUT", 190, 70);

                addDrawString(g, fnt2, Color.WHITE, "Play", 230, 200);
                addClickableBox(g, fnt2, Color.WHITE, 220, 150, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);

                addDrawString(g, fnt2, Color.WHITE, "Help", 230, 300);
                addClickableBox(g, fnt2, Color.WHITE, 220, 250, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);

                addDrawString(g, fnt2, Color.WHITE, "Esc = QUIT", 230, 400);
                addClickableBox(g, fnt2, Color.WHITE, 220, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                break;
            }
            case Help: {

                addDrawString(g, fnt1, Color.WHITE, "Help", 250, 70);

                addDrawString(g, fnt3, Color.WHITE, "use the keys A and D to move the paddle.", 30, 200);
                addDrawString(g, fnt3, Color.WHITE, "dont let the ball fall through bottom edge.", 30, 220);
                addDrawString(g, fnt3, Color.WHITE, "press spacebar to launch the ball.", 30, 240);

                addDrawString(g, fnt4, Color.WHITE, "henlo googlers plz hire me lol", 35, 310);

                addDrawString(g, fnt5, Color.WHITE, "haah ah haha ha", 35, 320);

                addDrawString(g, fnt2, Color.WHITE, "Back", 240, 400);
                addClickableBox(g, fnt2, Color.WHITE, 210, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                break;
            }
            case GameOver: {

                addDrawString(g, fnt1, Color.WHITE, "GAME OVER!", 190, 70);

                addDrawString(g, fnt2, Color.WHITE, "You lost!", 240, 180);
                addDrawString(g, fnt2, Color.WHITE, "Score: " + hud.getScore(), 240, 250);

                addDrawString(g, fnt2, Color.WHITE, "Again?", 230, 400);
                addClickableBox(g, fnt2, Color.WHITE, 220, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                break;
            }
            case GameVictory: {

                addDrawString(g, fnt1, Color.WHITE, "VICTORY!", 190, 70);

                addDrawString(g, fnt2, Color.WHITE, "You won!", 240, 180);
                addDrawString(g, fnt2, Color.WHITE, "Score: " + hud.getScore(), 240, 250);

                addDrawString(g, fnt2, Color.WHITE, "Again?", 230, 400);
                addClickableBox(g, fnt2, Color.WHITE, 220, 350, CLICKABLE_WIDTH, CLICKABLE_HEIGHT);
                break;
            }
            default:
                break;
        }
    }


    private void addDrawString(Graphics g, Font font, Color color, String string, int x_coordinate, int y_coordinate){
        g.setFont(font);
        g.setColor(color);
        g.drawString(string, x_coordinate, y_coordinate);
    }

    private void addClickableBox(Graphics g, Font font, Color color, int x_coordinate, int y_coordinate, int clickable_width, int clickable_height){
        g.setFont(font);
        g.setColor(color);
        g.drawRect(x_coordinate, y_coordinate, clickable_width, clickable_height);
    }


    private void gameStart(){

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
}
