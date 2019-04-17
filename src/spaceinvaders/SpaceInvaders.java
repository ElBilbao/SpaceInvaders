/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

/**
 * SpaceInvaders class is used to run the main function
 *
 * @author Antonio and Rodrigo
 */
public class SpaceInvaders {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game g = new Game("Space Invaders", 358, 350);
        g.start();

    }
}
