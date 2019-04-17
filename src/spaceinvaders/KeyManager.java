/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Used to handle key events
 *
 * @author Antonio and Rodrigo
 */
public class KeyManager implements KeyListener {

    public boolean up;      // flag to move up the player
    public boolean down;    // flag to move down the player
    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public boolean pause;   // flag to pause the game
    public boolean shoot;   // flag to shoot
    private boolean keys[]; // to store all the flags for every key
    public boolean save;    // flag to save the game's states in a file 
    public boolean load;    // flag to load the game's states of a file
    public boolean start;   // flag to restart game with 'enter'
    public boolean startTwo;  // flag to restart game with 'r'

    /**
     * To create an array of each key in the keyboard
     */
    public KeyManager() {
        keys = new boolean[256];
    }

    /**
     * To check if a key was typed
     *
     * @param e a <code>key event</code>
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * To check if a key was pressed
     *
     * @param e a <code>key event</code>
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        keys[e.getKeyCode()] = true;
    }

    /**
     * To check if a key was released
     *
     * @param e a <code>key event</code>
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        if (!keys[KeyEvent.VK_P]) {
            keys[e.getKeyCode()] = false;
        }

    }

    /**
     * Turn the 'P' key off after being pressed
     */
    public void offP() {
        keys[KeyEvent.VK_P] = false;
    }

    /**
     * Turn the 'Enter' or 'R' key off after being pressed
     */
    public void offEnter() {
        keys[KeyEvent.VK_ENTER] = false;
        keys[KeyEvent.VK_R] = false;
    }

    /**
     * To check if a key was flagged in the tick
     */
    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        pause = keys[KeyEvent.VK_P];
        shoot = keys[KeyEvent.VK_SPACE];
        save = keys[KeyEvent.VK_G];
        load = keys[KeyEvent.VK_C];
        start = keys[KeyEvent.VK_ENTER];
        startTwo = keys[KeyEvent.VK_R];
    }
}
