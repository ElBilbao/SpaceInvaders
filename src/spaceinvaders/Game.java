/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Game class is used to handle the general functionalities of the game
 *
 * @author Antonio and Rodrigo
 */
public class Game implements Runnable {

    // Variables
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    private String title;           // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private Missile missile;        // to use a missile
    private LinkedList<UFO> ufos;   // to create various blocks of ufo
    private Bomb bomb;              // to create the bomb of the aliens
    private int score;              // to keep track of the score
    private KeyManager keyManager;  // to manage the keyboard
    private boolean pause;          // to check if the game is paused
    private boolean gameOver;       // to check if the game is over
    private boolean gameWin;        // to check if the player won the game
    private int numUFO;             // to check the amount of ufos removed
    private String nombreArchivo;   // to create and read the saved states

    /**
     * To create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.running = false;
        this.keyManager = new KeyManager();
        this.ufos = new LinkedList<UFO>();
        this.pause = false;
        this.gameOver = false;
        this.gameWin = false;
        this.score = 0;
        this.nombreArchivo = "SpaceInvaders.txt";
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To get the key manager of the game
     *
     * @return a <code>KeyManager</code> with the key events
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        player = new Player(getWidth() / 2, getHeight() - 48, 2, 48, 44, this);
        missile = new Missile(-20, -20, 13, 27, this);
        bomb = new Bomb(20, 20, 13, 27, this);

        // Create a matrix of UFOs
        int iPosX = 0;
        int iPosY = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 6; i++) {
                ufos.add(new UFO(iPosX, iPosY, 1, 32, 16, this));
                iPosX += 30;
            }
            iPosX = 0;
            iPosY += 30;
        }

        // Initialize the background music
        Assets.music.setLooping(true);
        Assets.music.play();

        // Add key listener to JFrame
        display.getJframe().addKeyListener(keyManager);
    }

    /**
     * Used to run the game and establish the game ticking.
     *
     */
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }

        }
        stop();
    }

    /**
     * To read the state file to re-establish the saved game
     *
     * @throws IOException in case of not finding a file to load
     */
    public void leeArchivo() throws IOException {

        BufferedReader fileIn;
        try {
            fileIn = new BufferedReader(new FileReader(nombreArchivo));
        } catch (FileNotFoundException e) {
            // In case the file was not found, print to console
            File puntos = new File(nombreArchivo);
            PrintWriter fileOut = new PrintWriter(puntos);
            fileOut.println("100,demo");
            fileOut.close();
            fileIn = new BufferedReader(new FileReader(nombreArchivo));
        }
        // Retrieve the first line of the file
        String dato = fileIn.readLine();

        // Continue to read the file until it reaches the end of the stream
        while (dato != null) {
            // Read the x, y and durability of each ufo and set it
            for (int i = 0; i < ufos.size(); i++) {
                ufos.get(i).setX(Integer.parseInt(dato));
                dato = fileIn.readLine();
                ufos.get(i).setY(Integer.parseInt(dato));
                dato = fileIn.readLine();
                ufos.get(i).setlive(Integer.parseInt(dato));
                dato = fileIn.readLine();
            }

            // Reset the x position, lives and shooting state of the player
            player.setX(Integer.parseInt(dato));
            dato = fileIn.readLine();
            player.setShooting(Boolean.parseBoolean(dato));
            dato = fileIn.readLine();
            player.setLives(Integer.parseInt(dato));
            dato = fileIn.readLine();

            // Reset the missil's position
            missile.setX(Integer.parseInt(dato));
            dato = fileIn.readLine();
            missile.setY(Integer.parseInt(dato));
            dato = fileIn.readLine();

            // Reset the position of the bomb and it's activity
            bomb.setX(Integer.parseInt(dato));
            dato = fileIn.readLine();
            bomb.setY(Integer.parseInt(dato));
            dato = fileIn.readLine();
            bomb.setActive(Boolean.parseBoolean(dato));
            dato = fileIn.readLine();

            // Reset the score and number of UFOs defeated
            score = (Integer.parseInt(dato));
            dato = fileIn.readLine();
            numUFO = (Integer.parseInt(dato));
            dato = fileIn.readLine();

        }
        // Close the file for security reasons
        fileIn.close();
    }

    /**
     * Used to save the state of the objects in a text file
     *
     * @throws IOException in case of not being able to print to file
     */
    public void grabaArchivo() throws IOException {

        // Create a new file to print to
        PrintWriter fileOut = new PrintWriter(new FileWriter(nombreArchivo));
        // Print in each line the x, y and durability of each ufo block
        for (int i = 0; i < ufos.size(); i++) {
            fileOut.println(Integer.toString(ufos.get(i).getX()));
            fileOut.println(Integer.toString(ufos.get(i).getY()));
            fileOut.println(Integer.toString(ufos.get(i).getlive()));
        }

        // Print the x position, shooting state and lives
        fileOut.println(player.getX());
        fileOut.println(player.isShooting());
        fileOut.println(player.getLives());

        // Print the coords of the missle
        fileOut.println(missile.getX());
        fileOut.println(missile.getY());

        // Print the position and activity of the bomb
        fileOut.println(bomb.getX());
        fileOut.println(bomb.getY());
        fileOut.println(bomb.isActive());

        // Print the score and number of UFOs defeated
        fileOut.println(score);
        fileOut.println(numUFO);

        // Close the file for security reasons
        fileOut.close();
    }

    // Used to print to screen the current score and lives
    public void paint(Graphics g) {
        String sPoints = Integer.toString(score);
        String sLives = Integer.toString(player.getLives());
        g.setColor(Color.red);
        g.drawString("Score: " + sPoints, 5, getHeight() - 5);
        g.setColor(Color.red);
        g.drawString("Lives: " + sLives, getWidth() - 50, getHeight() - 5);
    }

    /**
     * Used to handle the ticking of all objects in the game
     */
    private void tick() {
        // Tick key manager to check if a key was pressed
        keyManager.tick();

        // Check if the "G" was pressed to save the states file
        if (getKeyManager().save && (!pause || !gameWin || !gameOver)) {
            try {
                grabaArchivo();
            } catch (IOException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }

        // Check if the "C" was pressed to load the states file
        if (getKeyManager().load && (!pause || !gameWin || !gameOver)) {
            try {
                leeArchivo();
                gameOver = false;
            } catch (IOException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }

        // Check if the "P" was pressed to pause the game
        if (getKeyManager().pause) {
            pause = !pause;
            getKeyManager().offP();
        }

        // Allow the player to shoot a missle if there are non in screen
        if (getKeyManager().shoot && !player.isShooting()) {
            player.setShooting(true);
            missile.setX(player.getX() + player.getWidth() / 2 - missile.getWidth() / 2);
            missile.setY(player.getY() + player.getHeight() / 2 - missile.getHeight() / 2);

        }

        // Generate a random chance of bomb appearance 
        int random = (int) (Math.random() * 400);

        // While not paused or game won tick everything else
        if (!pause && !gameWin) {
            // Check changes of the player
            player.tick();
            missile.tick();
            bomb.tick();

            // Check change in the missile
            // Check each ufo block's changes
            for (int i = 0; i < ufos.size(); i++) {
                // Auxiliar ufo object for the loop
                UFO ufo = ufos.get(i);
                // Check if the block is still being displayed
                if (ufo.getlive() > 0) {
                    ufo.tick();
                    // Move the ufo in the x axis
                    ufo.setX(ufo.getX() + ufo.getSpeed());

                    // Move ufos down if they collide with left/right border
                    if (ufo.getX() + ufo.getWidth() >= getWidth() || ufo.getX() < 0) {
                        for (int j = 0; j < ufos.size(); j++) {
                            ufos.get(j).setSpeed(-ufos.get(j).getSpeed());
                            ufos.get(j).setY(ufos.get(j).getY() + 20);
                        }
                    }
                }

                // Check if random chance complies and activates bomb from random UFO
                // Only one bomb at a time (only one bomb in screen)
                if (random <= 100 && !bomb.isActive()) {
                    bomb.setActive(true);

                    // Random UFO to launch
                    int randomUFO = (int) (Math.random() * 400);
                    UFO ufoAux = ufos.get(randomUFO % 24);

                    // Generate a new random UFO to launch, until one alive is found
                    while (ufoAux.getlive() <= 0) {
                        randomUFO = (int) (Math.random() * 400);
                        ufoAux = ufos.get(randomUFO % 24);
                    }

                    // Position bomb in random UFO
                    bomb.setX(ufoAux.getX() + ufoAux.getWidth() / 2 - bomb.getWidth() / 2);
                    bomb.setY(ufoAux.getY() + ufoAux.getHeight() / 2 - bomb.getHeight() / 2);
                }

                // When the bomb collides with the player they lose a live & flag that another bomb can be dropped
                // If the bomb misses it has to leave the screen before another bomb can be dropped
                // A sound will play when the player is hit
                if ((bomb.collidePlayer(player) && player.getLives() > 0) || bomb.getY() >= getHeight()) {
                    bomb.setActive(false);
                    if (bomb.collidePlayer(player)) {
                        Assets.shipHit.play();
                        player.setLives(player.getLives() - 1);
                        bomb.setActive(false);
                        bomb.setX(20);
                        bomb.setY(20);

                    }
                }
                // When the missile collides with the UFO they lose their live & flag that another missile can be shot
                // If the missile misses it has to leave the screen before another missile can be shot
                // A sound will play when an UFO is hit
                // A counter will indicate how many UFO's you have hit
                if ((missile.collideUFO(ufo) && ufo.getlive() > 0) || missile.getY() <= -30) {
                    player.setShooting(false);
                    if (missile.collideUFO(ufo)) {
                        Assets.alienHit.play();
                        ufo.setlive(ufo.getlive() - 1);
                        player.setShooting(false);
                        score = score + 10;
                        missile.setX(-20);
                        missile.setY(-20);
                        numUFO++;
                    }
                }

                // When the UFO's reach the player you lose all your lives. 
                if (ufo.getY() > getHeight() || ufo.collidePlayer(player)) {
                    player.setLives(0);
                }

            }
        }

        //When you reach 0 lives you will lose & it's paused so no other movement occours. 
        if (player.getLives() == 0) {
            pause = true;
            gameOver = true;
        }

        //When you have shot all the ufos you win the game & it's paused so no other movement occours. 
        if (numUFO >= 24) {
            pause = true;
            gameWin = true;
        }

        // Check if the ufos have reached the bottom border
        // Restart game if space keep pressed
        if (getKeyManager().start || getKeyManager().startTwo) {
            // Dispose current JFrame
            display.getJframe().dispose();
            
            // Stop current music track
            Assets.music.stop();
            
            // Remove all ufos of the linked list
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 6; j++) {
                    ufos.pop();
                }
            }

            // Restart score & number of UFO's shot
            this.score = 0;
            this.numUFO = 0;

            // Reset restart key
            getKeyManager().offEnter();

            //Reset the gameOver, gameWin, and pause flags
            gameOver = false;
            gameWin = false;
            pause = false;

            // Re-initialize the game
            init();
        }

    }

    /**
     * Used to render each object into the frame
     */
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();

            //Render the background
            g.drawImage(Assets.background, 0, 0, width, height, null);

            //If there is a missile shot it will be rendered
            if (player.isShooting()) {
                missile.render(g);
            }

            //If there is a bomb dropping it will be rendered
            if (bomb.isActive()) {
                bomb.render(g);
            }

            //Render the player
            player.render(g);

            //Render the score and lives display
            paint(g);

            //If the ufo has life it will be rendered
            for (int i = 0; i < ufos.size(); i++) {
                UFO ufo = ufos.get(i);
                if (ufo.getlive() > 0) {
                    ufo.render(g);
                }
            }

            // Display paused image
            if (pause) {
                g.drawImage(Assets.pause, getWidth() / 2 - 225, getHeight() / 2 - 65, 450, 130, null);
            }

            // Display the game over image
            if (gameOver) {
                g.drawImage(Assets.lose, getWidth() / 2 - 225, getHeight() / 2 - 65, 450, 130, null);
            }

            // Display win image
            if (gameWin) {
                g.drawImage(Assets.win, getWidth() / 2 - 225, getHeight() / 2 - 65, 450, 130, null);
            }

            bs.show();
            g.dispose();
        }

    }

    /**
     * Setting the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
