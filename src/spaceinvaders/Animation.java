/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.image.BufferedImage;

/**
 * Animation class is used to handle animations from other objects
 *
 * @author Antonio and Rodrigo
 */
public class Animation {

    // Variables
    private int speed;              // to store the speed
    private int index;              // to store the frame index
    private long lastTime;          // to store the system's last time
    private long timer;             // to store the system's current time
    private BufferedImage[] frames; // to store the array of frames

    /**
     * Used to create and instance of the object
     *
     * @param frames a <code>BufferedImage</code> with the animation frames
     * @param speed a <code>int</code> with the speed of the animation
     */
    public Animation(BufferedImage[] frames, int speed) {
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    /**
     * To get the current frame of the animation
     *
     * @return a <code>BufferedImage</code> with the current frame
     */
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }

    /**
     * Used to tick the animation in the main tick method
     */
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (timer > speed) {
            index++;
            timer = 0;

            if (index >= frames.length) {
                index = 0;
            }
        }
    }

}
