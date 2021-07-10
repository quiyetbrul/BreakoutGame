package com.quiyetbrul.breakout;

import java.awt.*;

public abstract class GameObject { 

    //constructor + mutator and accessor
    //takes care of the values in the x and y direction
    //takes care of speed in the x and y direction

    // protected: only access by THE object that inherits GameObject
    protected float x, y;
    protected ID id;
    protected float velX, velY;

    // constructor for a game object
    public GameObject(float x, float y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();

    // renders the object
    public abstract void render(Graphics g);
    
    public abstract Rectangle getBounds();

    // x-coordinate
    public void setX(float x) {
        this.x = x;
    }
    public float getX() {
        return x;
    }

    // y-coordinate
    public void setY(float y) {
        this.y = y;
    }
    public float getY() {
        return y;
    }

    // game ID, for collision, collider
    public void setId(ID id) {
        this.id = id;
    }
    public ID getId() {
        return id;
    }

    // x-coordinate velocity
    public void setVelX(float velX) {
        this.velX = velX;
    }
    public float getVelX() {
        return velX;
    }

    // y-coordinate velocity
    public void setVelY(float velY) {
        this.velY = velY;
    }
    public float getVelY() {
        return velY;
    }
}
