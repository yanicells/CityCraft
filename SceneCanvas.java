/**
The SceneCanvas class represents the canvas where the scene is drawn.
It implements JComponent to render the scene and implements Runnable and ActionListener for animation and event handling.

@author Edrian Miguel E. Capistrano (240939)
@author Sofia Dion Y. Torres (244566)
@version March 5, 2025

I have not discussed the Java language code in my program
with anyone other than my instructor or the teaching assistants
assigned to this course.
I have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of my program.
**/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SceneCanvas extends JComponent implements Runnable, ActionListener {
    private int width;
    private int height;
    private ArrayList<DrawingObject> drawingObjects;
    private ArrayList<Color> colorNPC;
    private Thread animationThread;
    private final int FPS;
    private Random random;
    private KeyHandler keyListener;
    private KeyHandler keyListener2;
    private ActionHandler actionHandler;
    private Sun sun;
    private Moon moon;
    private Person person;

    protected String player1Name;
    protected String player2Name;
    protected int noOfNPC;
    private int currentX;

    /**
     * Constructs a SceneCanvas object with the specified dimensions, player names, and number of NPCs.
     * Initializes the canvas with the given parameters.
     *
     * @param width the width of the canvas
     * @param height the height of the canvas
     * @param player1Name the name of the first player
     * @param player2Name the name of the second player
     * @param noOfNPC the number of NPCs
     */
    public SceneCanvas(int width, int height, String player1Name, String player2Name, int noOfNPC){
        this.width = width;
        this.height = height;
        drawingObjects = new ArrayList<>();
        colorNPC = new ArrayList<>();
        random = new Random();
        keyListener = new KeyHandler("key");
        keyListener2 = new KeyHandler("arrow");
        actionHandler = new ActionHandler();
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.noOfNPC = noOfNPC;
        currentX = getX();

        setUpCanvas();
        startThread();
        FPS = 60;
    }

    /**
     * Paints the component using the provided Graphics object.
     * This method sets up the rendering hints and translates the graphics context based on user input.
     *
     * @param g the Graphics object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform reset = g2d.getTransform();
        super.paintComponent(g);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        if (keyListener.leftPressed && person.canMoveLeft()) {
            currentX += 2; // Move left
        } else if (keyListener.rightPressed && person.canMoveRight()) {
            currentX -= 2; // Move right
        }

        g2d.translate(currentX, 0);
        for (DrawingObject e : drawingObjects) {
            e.draw(g2d);
        }
    }

    /**
     * Sets up the canvas by adding key listeners, mouse listeners, and initializing drawing objects.
     */
    private void setUpCanvas(){
        this.setFocusable(true);
        this.addKeyListener(keyListener);
        this.addKeyListener(keyListener2);
        this.addKeyListener(actionHandler);
        this.addMouseListener(actionHandler);
        this.setPreferredSize(new Dimension(width, height));

        addColors();
        Timer timer = new Timer(0, this);
        timer.start();

        setUpBackground();
        setUpScenes();
        setUpPath();
        setUpPeople();
        setUpScreenElements();
    }

    /**
     * Runs the animation loop.
     * This method updates the canvas at a fixed frame rate.
     */
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(animationThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                if(actionHandler.animate){
                    repaint();
                }
                delta--;
            }
        }
    }

    /**
     * Starts the animation thread.
     */
    private void startThread(){
        animationThread = new Thread(this);
        animationThread.start();
    }

    /**
     * Handles action events.
     * This method adds new drawing objects based on user input.
     *
     * @param e the ActionEvent object containing details of the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(actionHandler.lantern){
            drawingObjects.add(new Lantern(random.nextInt(person.xPosition - 400, person.xPosition + 400), random.nextInt(100, 300), random.nextDouble(0.5, 0.9)));
            actionHandler.lantern = false;
        }
        if(actionHandler.bird){
            drawingObjects.add(new Bird(random.nextInt(person.xPosition - 400, person.xPosition + 400), random.nextDouble(80, 300)));
            actionHandler.bird = false;
        }
        if(actionHandler.placeLantern){
            drawingObjects.add( new Lantern(actionHandler.mousePosition.x - currentX, actionHandler.mousePosition.y, random.nextDouble(0.5, 0.9)));
            actionHandler.placeLantern = false;
        }
    }

    /**
     * Sets up the background elements of the scene.
     */
    private void setUpBackground(){
        sun = new Sun();
        moon = new Moon();
        person = new Person(350, 350, keyListener, new Color(31, 9, 3), new Color (58, 178, 194), new Color(48, 12, 89), true);
        sun.setMoon(moon);
        moon.setSun(sun);

        drawingObjects.add(new Sky(width, height, sun, moon, -200, 0, keyListener, person));
        for(int i = 0; i < 75; i++){
            drawingObjects.add(new Star(random.nextDouble(-400, 3700), random.nextDouble(0, 250), random.nextInt(3, 7), Color.white, moon));
        }
        drawingObjects.add(new MountainBackground(5));
        for (int i = -400; i < 3600; i+=400){
            drawingObjects.add(new Cloud( i += random.nextInt(-20,20), random.nextDouble(50, 200), random.nextInt(100,150), random.nextInt(60,100), new Color(250, 242, 239)));
        }
        drawingObjects.add(sun);
        drawingObjects.add(moon);

        drawingObjects.add(new CityBackground(500, person));

        for (int i = -200; i < 3600; i+=400){
            drawingObjects.add(new Cloud( i += random.nextInt(-20,20), random.nextDouble(5, 200), random.nextInt(100,150), random.nextInt(60,100), new Color(250, 242, 239)));
        }
    }

    /**
     * Sets up the path elements of the scene.
     */
    private void setUpPath(){
        drawingObjects.add(new Fence(2400, -800));
        drawingObjects.add(new Dock());
        drawingObjects.add(new Road());
        drawingObjects.add(new Portal());
        drawingObjects.add(new LampPost(-100));
        drawingObjects.add(new LampPost(700));
        drawingObjects.add(new LampPost(1500));
        drawingObjects.add(new LampPost(2300));
        drawingObjects.add(new LampPost(3250));
    }

    /**
     * Sets up the scene elements.
     */
    private void setUpScenes(){
        drawingObjects.add(new Fireworks(actionHandler, person));

        drawingObjects.add(new Banners());
        drawingObjects.add(new FerrisWheel(1830, 80, person));
        drawingObjects.add(new Stalls(new Color(204, 37, 87), Color.WHITE, 2160));
        drawingObjects.add(new Stalls(new Color(22, 156, 17), Color.WHITE, 1602));

        drawingObjects.add(new Water(2400, 380, new Color(25, 25, 112), 5, -20, person));
        drawingObjects.add(new Water(2400, 400, new Color(173, 216, 230), 7, -30, person));
        drawingObjects.add(new Water(2400, 420, new Color(100, 149, 237), 6, -40, person));
    }

    /**
     * Adds colors for the NPCs.
     */
    private void addColors(){
        colorNPC.add(new Color(245, 173, 255));
        colorNPC.add(new Color(166, 255, 255)); 
        colorNPC.add(new Color(46, 17, 4)); 

        colorNPC.add(new Color(31, 6, 1)); 
        colorNPC.add(new Color(207, 2, 23)); 
        colorNPC.add(new Color(242, 160, 73)); 

        colorNPC.add(new Color(99, 37, 13)); 
        colorNPC.add(new Color(219, 81, 120));
        colorNPC.add(new Color(15, 92, 35)); 

        colorNPC.add(new Color(255, 236, 161));
        colorNPC.add(new Color(93, 28, 115)); 
        colorNPC.add(new Color(20, 4, 26)); 

        colorNPC.add(new Color(99, 45, 16));
        colorNPC.add(new Color(242, 191, 109));
        colorNPC.add(new Color(29, 130, 113)); 
    }

    /**
     * Sets up the people in the scene.
     */
    private void setUpPeople(){
        drawingObjects.add(person);
        drawingObjects.add(new Person(450, 350, keyListener2, new Color(237, 153, 57), new Color (51, 166, 82), new Color(36, 2, 6), false));
        addNPC(noOfNPC);
    }

    /**
     * Sets up the screen elements.
     */
    private void setUpScreenElements(){
        drawingObjects.add(new Rain(actionHandler));
        drawingObjects.add(new Instructions(actionHandler, player1Name, player2Name));
    }

    /**
     * Adds NPCs to the drawing objects.
     *
     * @param NPCs the number of NPCs to add
     */
    private void addNPC(int NPCs){
        for(int i = 1; i <= NPCs; i++){
            drawingObjects.add(new Person(random.nextInt(50, 700), 350, null, colorNPC.remove(0), colorNPC.remove(0),colorNPC.remove(0), random.nextInt(1,3) == 2));
        }
    }
}
