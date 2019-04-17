/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Player class is used to create the bar in the game
 *
 * @author Antonio and Rodrigo
 */
public class Player extends Item {

    // Variables
    private int speed;              // to store the speed
    private int width;              // to store the width
    private int height;             // to store the height
    private int lives;              // to store the lives
    private Game game;              // to store the game in which the player is being used
    private boolean shooting;       // to store if the player has shot a bullet

    /**
     * Used to create an instance of the player object
     *
     * @param x to handle the x position of the player
     * @param y to handle the y position of the player
     * @param direction to handle the direction of the player
     * @param width to have the width of the player
     * @param height to have the height of the player
     * @param game to have the game in which the player is being used
     */
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.speed = 4;
        this.lives = 3;
        this.width = width;
        this.height = height;
        this.game = game;
        this.shooting = false;
    }

    /**
     * To get the x position of the player
     *
     * @return a <code>int</code> value with the x position
     */
    @Override
    public int getX() {
        return super.getX(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * To get the speed of the player
     *
     * @return a <code>int</code> value with the speed of the player
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * To get the width of the player
     *
     * @return a <code>int</code> value with the width size
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the player
     *
     * @return a <code>int</code> value with the height size
     */
    public int getHeight() {
        return height;
    }

    /**
     * To get the value of shooting
     *
     * @return a <code>boolean</code> value of shooting state
     */
    public boolean isShooting() {
        return shooting;
    }

    /**
     * To get the perimeter of the player
     *
     * @return a <code>Rectangle</code> object with the perimeter of the player
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getX() + 5, getY() + 5, getWidth() - 10, getHeight() - 5);
    }

    /**
     * To get the lives of the player
     *
     * @return a <code>int</code> value with the lives of the player
     */
    public int getLives() {
        return lives;
    }

    /**
     * To change the lives of the player
     *
     * @param lives a <code>int</code> value with the new lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * To change the x position of the player
     *
     * @param x a <code>int</code> value with the new x position
     */
    @Override
    public void setX(int x) {
        super.setX(x); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * To change the speed of the player
     *
     * @param speed a <code>int</code> value with the new speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * To change the width of the player
     *
     * @param width a <code>int</code> value with the new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To change the height of the player
     *
     * @param height a <code>int</code> value with the new height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * To change the shooting instance
     *
     * @param shooting a <code>boolean</code>
     */
    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    /**
     * Used to tick the player in the main tick method
     */
    @Override
    public void tick() {

        // Move the player left and right, depending on keymanager flags
        if (game.getKeyManager().left) {
            setX(getX() - speed);
        }
        if (game.getKeyManager().right) {
            setX(getX() + speed);
        }

        if (game.getKeyManager().shoot) {
            shooting = true;
        }

        // Contain the player inside the JFrame
        if (getX() + getWidth() >= game.getWidth()) {
            setX(game.getWidth() - getWidth());
        } else if (getX() <= 0) {
            setX(0);
        }

    }

    /**
     * Used to render the player in the game
     *
     * @param g a <code>Graphics</code> in which the player is being rendered
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
}
