package com.quiyetbrul.breakout;

import java.awt.*;
import java.util.*;


// handles all of objects in game concurrently
// individually updates and renders objects to the screen
public class Handler {

    LinkedList<GameObject> object = new LinkedList<>();
    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }
    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {this.object.add(object);}
    public void removeObject(GameObject object) {this.object.remove(object);}

    public void ScreenClear() { // clears the screen and rerenders player
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if (tempObject.getId() == ID.Player) {
                object.clear();
                if(GameMain.gameStart != GameMain.GAME_STATE.GameOver){ 
                    addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
                }
            }
        }
    }
}