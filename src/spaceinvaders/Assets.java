/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.image.BufferedImage;

/**
 * Assets class is used to create an image, sound or animation
 *
 * @author Antonio and Rodrigo
 */
public class Assets {

    public static BufferedImage background;         // to store background image
    public static BufferedImage ufo;                // to store the ufo image
    public static BufferedImage spritesMissile[];   // to store the player bullet's frames
    public static BufferedImage player;             // to store the player
    public static BufferedImage spritesUfo[];       // to store the ufo's frames
    public static BufferedImage Missile;            // to store the player's bullets
    public static BufferedImage pause;              // to store the pause image
    public static BufferedImage bomb;               // to store the bomb image
    public static BufferedImage spritesBomb[];      // to store the bomb's frames
    public static SoundClip alienHit;               // to store the sound of alien collision
    public static SoundClip shipHit;                // to store the sound of ship collision
    public static SoundClip music;                  // to store the background music
    public static BufferedImage win;                // to store the win image
    public static BufferedImage lose;               // to store the losing image

    /**
     * initializing the images of the game
     */
    public static void init() {
        // Image loading
        background = ImageLoader.loadImage("/Image/space.png");
        ufo = ImageLoader.loadImage("/Image/ufo.png");
        player = ImageLoader.loadImage("/Image/ship.png");
        Missile = ImageLoader.loadImage("/Image/misil.png");
        pause = ImageLoader.loadImage("/Image/pause.png");
        bomb = ImageLoader.loadImage("/Image/bomb.png");
        alienHit = new SoundClip("/sounds/alienhit.wav");
        shipHit = new SoundClip("/sounds/collision.wav");
        music = new SoundClip("/sounds/Superboy.wav");
        win = ImageLoader.loadImage("/Image/win.png");
        lose = ImageLoader.loadImage("/Image/LOSE.png");

        // Load the sprite sheets into buffered images
        SpreadSheet spritesheetUfo = new SpreadSheet(ufo);
        spritesUfo = new BufferedImage[6];

        SpreadSheet spritesheetMissile = new SpreadSheet(Missile);
        spritesMissile = new BufferedImage[6];

        SpreadSheet spritesheetBomb = new SpreadSheet(bomb);
        spritesBomb = new BufferedImage[6];

        // To crop the sprite sheets into frames
        for (int i = 0; i < 6; i++) {
            spritesUfo[i] = spritesheetUfo.crop(i * 32, 0, 32, 16);
            spritesMissile[i] = spritesheetMissile.crop(i * 13, 0, 13, 27);
            spritesBomb[i] = spritesheetBomb.crop(i * 16, 0, 16, 32);
        }
    }
}
