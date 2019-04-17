/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Bomb class is used to create a bomb instance
 *
 * @author Antonio and Rodrigo
 */
public class Bomb extends Item {

    // Variables
    private int speedY;             // to have the speed of the Bomb in the y axis
    private int width;              // to handle the width of the Bomb
    private int height;             // to handle the height of the Bomb
    private boolean active;
    private Game game;              // to have the game in which the Bomb is used
    private Animation bombLaunch;     // to handle the Bomb's animation

    /**
     * Used to create an instance of the Bomb
     *
     * @param x to set the x coordinate in the frame
     * @param y to set the y coordinate in the frame
     * @param width to set the width of the Bomb
     * @param height to set the height of the Bomb
     * @param game to set the game in which the Bomb is being used
     */
    public Bomb(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.speedY = 3;
        this.width = width;
        this.height = height;
        this.game = game;
        this.bombLaunch = new Animation(Assets.spritesBomb, 100);
        this.active = false;

    }

    /**
     * To get the x position of the Bomb
     *
     * @return an <code>int</code> value with the x position
     */
    @Override
    public int getX() {
        return super.getX(); //To change body of generated UFOods, choose Tools | Templates.
    }

    /**
     * To get the y position of the Bomb
     *
     * @return an <code>int</code> value with the y position
     */
    @Override
    public int getY() {
        return super.getY(); //To change body of generated UFOods, choose Tools | Templates.
    }

    /**
     * To get the speed in the y axis of the Bomb
     *
     * @return an <code>int</code> value with the speed in y axis
     */
    public int getSpeedY() {
        return speedY;
    }

    /**
     * To get the width of the Bomb
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the Bomb
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To get the value of active
     *
     * @return a <code>boolean</code>
     */
    public boolean isActive() {
        return active;
    }

    /**
     * To change the active state
     *
     * @param active a <code>boolean</code>
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * To change the y position of the Bomb
     *
     * @param y an <code>int</code> value with the new y position
     */
    @Override
    public void setY(int y) {
        super.setY(y); //To change body of generated UFOods, choose Tools | Templates.
    }

    /**
     * To change the x position of the Bomb
     *
     * @param x an <code>int</code> value with the new x position
     */
    @Override
    public void setX(int x) {
        super.setX(x); //To change body of generated UFOods, choose Tools | Templates.
    }

    /**
     * To change the speed of the Bomb in the y axis
     *
     * @param speedY an <code>int</code> value of the new speed in y axis
     */
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    /**
     * To change the width of the Bomb
     *
     * @param width an <code>int</code> value of the new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To change the height of the Bomb
     *
     * @param height an <code>int</code> value of the new height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * To get a rectangle with the perimeter of the Bomb
     *
     * @return a <code>Rectangle</code> with the perimeter of the Bomb
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Check if a player collides with the bomb
     *
     * @param obj an <code>obj</code> with the player's object
     * @return a <code>boolean></code>
     */
    public boolean collidePlayer(Object obj) {
        return obj instanceof Player && getPerimetro().intersects(((Player) obj).getPerimetro());
    }

    /**
     * Tick of the Bomb to use in the main tick
     */
    public void tick() {
        // Move the Bomb diagonally 
        setY(getY() + getSpeedY());
        this.bombLaunch.tick();
    }

    /**
     * Render of the Bomb to use in the main render
     *
     * @param g a <code>Graphics</code> object
     */
    public void render(Graphics g) {
        g.drawImage(bombLaunch.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }

}
