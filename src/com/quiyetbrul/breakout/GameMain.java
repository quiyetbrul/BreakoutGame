package com.quiyetbrul.breakout;

import java.awt.*;
import java.awt.image.*;

public class GameMain extends Canvas implements Runnable {

    private static final long serialVersionUID = 4730308219818161523L;

    // screen size
    public static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = WINDOW_WIDTH / 12 * 9; // 16x9 ratio

    // paddle starting point
    public static final int PADDLE_X = 210, PADDLE_Y = 400;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private GameMenu menu;

    public enum GAME_STATE {
        Menu, Help, Game, GameOver
    }

    public static GAME_STATE gameStart = GAME_STATE.Menu;

    public GameMain() {
        handler = new Handler();
        hud = new HUD();
        menu = new GameMenu(this, handler, hud);
        this.addMouseListener(menu);
        this.addKeyListener(new KeyInput(handler));

        new Window(WINDOW_WIDTH, WINDOW_HEIGHT, "BREAKOUT GAME BY QUIYET BRUL", this);

        if (gameStart == GAME_STATE.Game) {
            handler.addObject(new Player(PADDLE_X, PADDLE_Y, ID.Player, handler));
        }
    }

    public synchronized void start() {
        // initialize multiple thread
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    // fps for the game that loops over the code

    // research part: renders game as fast as it can
    // resolves large amount of collisions per frame, dragging the performance.
    // calls tick() at a steady frequency to make the game stable

    // improvement: avoid using System.currentTimeMillis() it is susceptible to
    // changing the system clock

    // research other or create game loops that offer variable timestep but doesn't
    // wreck game physics computations

    */
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);

                frames = 0;
            }
        }
        stop();
    }

    // updates the game at the rate specified in function run()
    private void tick() {
        handler.tick();
        if (gameStart == GAME_STATE.Game) {
            hud.tick();
            menu.tick();

            // determines user health + game over
//            if (HUD.LIVES <= 0) {
//                HUD.LIVES = 5;
//                gameStart = GAME_STATE.GameOver;
//                handler.ScreenClear();
//            } else if (gameStart == GAME_STATE.Menu || gameStart == GAME_STATE.GameOver) {
//                menu.tick();
//            }
        }
    }

    // renders background, game state
    private void render() {
        BufferStrategy bs = this.getBufferStrategy(); // starts value at null
        if (bs == null) {
            this.createBufferStrategy(3); // 3: buffer creations
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black); // stops flashing background
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        handler.render(g);

        if (gameStart == GAME_STATE.Game) {
            hud.render(g);
        } else if (gameStart == GAME_STATE.Menu || gameStart == GAME_STATE.Help || gameStart == GAME_STATE.GameOver) {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    // Clamps the elapsed time from the game loop
    /*
     * Without this, objects can possibly be led tunneling through other objects or
     * getting out of bounds
     */
    public static int clamp(int var, int min, int max) {

        // Clamping means literally in this instance.
        //// it's used to restrict a value to a given range
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public static void main(String args[]) {
        new GameMain();
    }
}
