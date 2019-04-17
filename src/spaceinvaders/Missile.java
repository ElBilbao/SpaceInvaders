/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Missile class is used to create the projectile of the game
 *
 * @author Antonio and Rodrigo
 */
public class Missile extends Item {

    // Variables
    private int speedY;             // to have the speed of the Missile in the y axis
    private int width;              // to handle the width of the Missile
    private int height;             // to handle the height of the Missile
    private Game game;              // to have the game in which the Missile is used
    private Animation missileLaunch;     // to handle the Missile's animation

    /**
     * Used to create an instance of the Missile
     *
     * @param x to set the x coordinate in the frame
     * @param y to set the y coordinate in the frame
     * @param width to set the width of the Missile
     * @param height to set the height of the Missile
     * @param game to set the game in which the Missile is being used
     */
    public Missile(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.speedY = -3;
        this.width = width;
        this.height = height;
        this.game = game;
        this.missileLaunch = new Animation(Assets.spritesMissile, 100);

    }

    /**
     * To get the x position of the Missile
     *
     * @return an <code>int</code> value with the x position
     */
    @Override
    public int getX() {
        return super.getX(); //To change body of generated UFOods, choose Tools | Templates.
    }

    /**
     * To get the y position of the Missile
     *
     * @return an <code>int</code> value with the y position
     */
    @Override
    public int getY() {
        return super.getY(); //To change body of generated UFOods, choose Tools | Templates.
    }

    /**
     * To get the speed in the y axis of the Missile
     *
     * @return an <code>int</code> value with the speed in y axis
     */
    public int getSpeedY() {
        return speedY;
    }

    /**
     * To get the width of the Missile
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the Missile
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To change the y position of the Missile
     *
     * @param y an <code>int</code> value with the new y position
     */
    @Override
    public void setY(int y) {
        super.setY(y); //To change body of generated UFOods, choose Tools | Templates.
    }

    /**
     * To change the x position of the Missile
     *
     * @param x an <code>int</code> value with the new x position
     */
    @Override
    public void setX(int x) {
        super.setX(x); //To change body of generated UFOods, choose Tools | Templates.
    }

    /**
     * To change the speed of the Missile in the y axis
     *
     * @param speedY an <code>int</code> value of the new speed in y axis
     */
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    /**
     * To change the width of the Missile
     *
     * @param width an <code>int</code> value of the new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To change the height of the Missile
     *
     * @param height an <code>int</code> value of the new height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * To get a rectangle with the perimeter of the Missile
     *
     * @return a <code>Rectangle</code> with the perimeter of the Missile
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), 10, getHeight());
    }

    /**
     * Check if a UFO block collides with the Missile
     *
     * @param obj an <code>obj</code> with the UFO block's object
     * @return a <code>boolean></code>
     */
    public boolean collideUFO(Object obj) {
        return obj instanceof UFO && getPerimetro().intersects(((UFO) obj).getPerimetro());
    }

    /**
     * Tick of the Missile to use in the main tick
     */
    public void tick() {
        // Move the Missile diagonally 
        setY(getY() + getSpeedY());
        this.missileLaunch.tick();
    }

    /**
     * Render of the Missile to use in the main render
     *
     * @param g a <code>Graphics</code> object
     */
    public void render(Graphics g) {
        g.drawImage(missileLaunch.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }

}
