package com.quiyetbrul.breakout;

import java.awt.*;

public abstract class GameObject { 

    //constructor + mutator and accessor
    //takes care of the values in the x and y direction
    //takes care of speed in the x and y direction

    // protected: only access by THE object that inherits GameObject
    protected float x_coordinate, y_coordinate;
    protected float velX, velY;
    protected ID id;

    // constructor for a game object
    public GameObject(float x_coordinate, float y_coordinate, ID id) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.id = id;
    }

    public abstract void tick();

    // renders the object
    public abstract void render(Graphics g);
    
    public abstract Rectangle getBounds();

    // x-coordinate
    public void setX_coordinate(float x_coordinate) {
        this.x_coordinate = x_coordinate;
    }
    public float getX_coordinate() {
        return x_coordinate;
    }

    // y-coordinate
    public void setY_coordinate(float y_coordinate) {
        this.y_coordinate = y_coordinate;
    }
    public float getY_coordinate() {
        return y_coordinate;
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
