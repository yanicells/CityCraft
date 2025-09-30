/**
 The Sun class represents the sun object that can be drawn on the screen.
 It implements the DrawingObject class to render the sun.

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
import java.util.Random;

public class Sun implements DrawingObject {
    private double positionX;
    private double positionY;
    private double radius;
    private Random random;
    private Moon moon;
    private boolean visible;
    private boolean hasSet;
    private int counter;
    private int rayDirection;
    private int rayLength;

    /**
     * Constructs a Sun object.
     * Initializes the sun with default parameters.
     */
    public Sun() {
        positionX = 50;
        positionY = 300;
        radius = (double) 180 / 2;
        random = new Random();
        visible = true;
        hasSet = false;
        counter = 0;
        rayDirection = 1;
        rayLength = 20;
    }

    /**
     * Draws the sun using the provided Graphics2D object.
     * This method sets up the coordinates for the sun and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (visible) {
            Circle base = new Circle(positionX, positionY, (int) (radius * 2), Color.yellow, true);
            base.draw(g2d);
            int centerX = (int) (positionX + radius);
            int centerY = (int) (positionY + radius);

            if (counter > 30) {
                rayLength += rayDirection;
                if (rayLength >= 50 || rayLength <= 20) {
                    rayDirection *= -1; // Flip direction
                }
            }

            for (int i = 0; i < 360; i += 15) { // Every 15 degrees
                double angle = Math.toRadians(i);

                int x1 = (int) (centerX + radius * Math.cos(angle));
                int y1 = (int) (centerY + radius * Math.sin(angle));

                int x2 = (int) (centerX + (radius + rayLength) * Math.cos(angle));
                int y2 = (int) (centerY + (radius + rayLength) * Math.sin(angle));

                Line line = new Line(x1, y1, x2, y2, random.nextInt(2, 5), Color.yellow);
                line.draw(g2d);
            }
            counter++;
            update();
        }
    }

    /**
     * Updates the state of the sun.
     * This method handles the movement of the sun and its visibility.
     */
    @Override
    public void update() {

        if (positionX >= 700 && positionY > 450) {
            visible = false;
            if (!hasSet) {
                moon.setVisible(true);
                hasSet = true; // Mark that Sun has already set
            }
        }

        if (positionY < 50) {
            positionX += random.nextDouble(0, 1.6);
            positionY += random.nextDouble(0, 0.5);
        } else if (positionX > 2200) { // GO DOWN
            positionX += random.nextDouble(0, 1.2);
            positionY += random.nextDouble(0, (double) 1 / 2);
        } else {
            positionX += random.nextDouble(0, 1);
            positionY += -random.nextDouble(0, (double) 1 / 2);
        }

    }

    /**
     * Resets the position and visibility of the sun.
     */
    public void reset() {
        positionX = 50;
        positionY = 300;
        visible = true;
        hasSet = false;
    }

    /**
     * Sets the moon object associated with the sun.
     *
     * @param moon the Moon object
     */
    public void setMoon(Moon moon) {
        this.moon = moon;
    }

    /**
     * Checks if the sun is visible.
     *
     * @return true if the sun is visible, false otherwise
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Gets the x-coordinate of the sun's position.
     *
     * @return the x-coordinate of the sun's position
     */
    public double getPositionX() {
        return positionX;
    }

    /**
     * Gets the y-coordinate of the sun's position.
     *
     * @return the y-coordinate of the sun's position
     */
    public double getPositionY() {
        return positionY;
    }
}
