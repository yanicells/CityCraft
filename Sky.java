
/**
 The Sky class represents the sky object that can be drawn on the screen.
 It implements the DrawingObject class to render the sky.

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

public class Sky implements DrawingObject {

    private double width;
    private double height;
    private Sun sun;
    private Moon moon;
    private int xPosition;
    private int yPosition;
    private KeyHandler keyListener;
    private int speed;
    private Sound sound;
    private Person person;

    /**
     * Constructs a Sky object with the specified dimensions, sun, moon, position,
     * and key listener.
     * Initializes the sky with the given parameters.
     *
     * @param width       the width of the sky
     * @param height      the height of the sky
     * @param sun         the Sun object
     * @param moon        the Moon object
     * @param xPosition   the x-coordinate of the sky
     * @param yPosition   the y-coordinate of the sky
     * @param keyListener the key listener for user inputs
     */
    public Sky(double width, double height, Sun sun, Moon moon, int xPosition, int yPosition, KeyHandler keyListener,
            Person person) {
        this.width = width * 5;
        this.height = height;
        this.sun = sun;
        this.moon = moon;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.keyListener = keyListener;
        this.person = person;
        speed = 2;
        sound = new Sound("assets/bg.wav");
        sound.loop();
    }

    /**
     * Draws the sky using the provided Graphics2D object.
     * This method sets up the coordinates for the sky and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        Rectangle background;
        if (sun.isVisible()) {
            if (sun.getPositionX() >= 50 && sun.getPositionX() < 400) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(249, 192, 107), true);
            } else if (sun.getPositionX() >= 400 && sun.getPositionX() < 800) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(255, 222, 112), true);
            } else if (sun.getPositionX() >= 800 && sun.getPositionX() < 1200) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(152, 208, 239), true);
            } else if (sun.getPositionX() >= 1200 && sun.getPositionX() < 1600) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(108, 226, 255), true);
            } else if (sun.getPositionX() >= 1600 && sun.getPositionX() < 2000) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(162, 216, 245), true);
            } else if (sun.getPositionX() >= 2000 && sun.getPositionX() < 2200) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(252, 254, 213), true);
            } else if (sun.getPositionX() >= 2200 && sun.getPositionX() < 2400) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(245, 187, 79), true);
            } else if (sun.getPositionX() >= 2400 && sun.getPositionX() < 2600) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(253, 128, 70), true);
            } else {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(170, 88, 91), true);
            }
        } else {
            if (moon.getxPosition() >= -100 && moon.getxPosition() < 400) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(128, 69, 101), true);
            } else if (moon.getxPosition() >= 400 && moon.getxPosition() < 800) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(87, 49, 112), true);
            } else if (moon.getxPosition() >= 800 && moon.getxPosition() < 1200) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(45, 29, 122), true);
            } else if (moon.getxPosition() >= 1200 && moon.getxPosition() < 1800) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(19, 24, 98), true);
            } else if (moon.getxPosition() >= 1800 && moon.getxPosition() < 2400) {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(181, 114, 142), true);
            } else {
                background = new Rectangle(xPosition, yPosition, width, height, new Color(218, 127, 125), true);
            }
        }
        background.draw(g2d);
        update();
    }

    /**
     * Updates the state of the sky.
     * This method handles the movement of the sky based on user input.
     */
    @Override
    public void update() {
        if (keyListener.leftPressed || keyListener.rightPressed) {
            if (keyListener.leftPressed && person.canMoveLeft()) {
                xPosition -= speed;
            } else if (keyListener.rightPressed && person.canMoveRight()) {
                xPosition += speed;
            }
        }
    }
}
