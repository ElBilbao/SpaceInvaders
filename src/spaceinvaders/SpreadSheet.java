/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.image.BufferedImage;

/**
 * SpreadSheet class is used to load and crop the sprites of an image into
 * frames
 *
 * @author Antonio and Rodrigo
 */
public class SpreadSheet {

    // Variables
    private BufferedImage sheet;

    /**
     * Constructor of a spreadsheet instance
     *
     * @param sheet
     */
    public SpreadSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    /**
     * Used to crop a spreadsheet into frames in an array
     *
     * @param x to handle the x position
     * @param y to handle the y position
     * @param width to handle the width
     * @param height to handle the height
     * @return a <code>BufferedImage</code> with the cropped image
     */
    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }

}
