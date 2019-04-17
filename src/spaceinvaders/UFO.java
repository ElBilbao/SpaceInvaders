/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * UFO class is used to create a UFO instance
 *
 * @author Antonio and Rodrigo
 */
public class UFO extends Item {

    // Variables
    private int live;           // to store the lives
    private int width;          // to store the width
    private int height;         // to store the height
    private int speed;          // to store the speed
    private Game game;          // to store the game in which the UFO is being used
    private Animation ufoLight; // to store the animation of the UFO

    /**
     * Used to create an instance of UFO
     *
     * @param x to handle the x position of the UFO 
     * @param y to handle the y position of the UFO 
     * @param live to handle the lives/live of the 
     * @param width to handle the width
     * @param height to handle the height
     * @param game to handle the game in which it is being used
     */
    public UFO(int x, int y, int live, int width, int height, Game game) {
        super(x, y);
        this.live = live;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 1;
        this.ufoLight = new Animation(Assets.spritesUfo, 100);
    }

    /**
     * To get the x position of the UFO block
     *
     * @return a <code>int</code> with the x position
     */
    @Override
    public int getX() {
        return super.getX(); //To change body of generated UFOods, choose Tools | Templates.
    }

    /**
     * To get the y position of the UFO block
     *
     * @return a <code>int</code> with the y position
     */
    @Override
    public int getY() {
        return super.getY(); //To change body of generated UFOods, choose Tools | Templates.
    }

    /**
     * to get the live of the UFO
     *
     * @return a <code>int</code> value with the live
     */
    public int getlive() {
        return live;
    }

    /**
     * To get the width of the UFO block
     *
     * @return a <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the UFO block
     *
     * @return a <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To get the perimeter of the UFO block
     *
     * @return a <code>Rectangle</code> with the perimeter of the UFO block
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight() / 2);
    }

    /**
     * To get the speed of the UFO
     *
     * @return a <code>int</code> value with the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * To change the live of the UFO block
     *
     * @param live a <code>int</code> value with the new live
     */
    public void setlive(int live) {
        this.live = live;
    }

    /**
     * To change the width of the UFO block
     *
     * @param width a <code>int</code> value with the new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To change the height of the UFO block
     *
     * @param height a <code>int</code> value with the new height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * To change the y position of the UFO block
     *
     * @param y a <code>int</code> with the new y position
     */
    @Override
    public void setY(int y) {
        super.setY(y); //To change body of generated UFOods, choose Tools | Templates.
    }

    /**
     * To change the x position of the UFO block
     *
     * @param x a <code>int</code> value with the new x position
     */
    @Override
    public void setX(int x) {
        super.setX(x); //To change body of generated UFOods, choose Tools | Templates.
    }

    /**
     * To change the speed of the UFO
     *
     * @param speed a <code>int</code> with the new speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    /**
     * Check if a UFO block collides with Player
     *
     * @param obj an <code>obj</code> with a player instance
     * @return a <code>boolean></code>
     */
    public boolean collidePlayer(Object obj) {
        return obj instanceof Player && getPerimetro().intersects(((Player) obj).getPerimetro());
    }

    /**
     * To tick the animation of the UFO in the main tick
     */
    @Override
    public void tick() {

        ufoLight.tick();
    }

    /**
     * To render the UFO block in the main render
     *
     * @param g a <code>Graphics</code> to render the UFO in it
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(ufoLight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);

    }
}
