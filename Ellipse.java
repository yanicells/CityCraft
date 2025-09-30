/**
 The Ellipse class represents an ellipse object that can be drawn on the screen.
 It implements the DrawingObject class to render the ellipse.

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
import java.awt.geom.Ellipse2D;

public class Ellipse implements DrawingObject{
    private double xPosition;
    private double yPosition;
    private int width;
    private int height;
    private Color color;

    /**
     * Constructs an Ellipse object with the specified position, size, and color.
     * Initializes the ellipse with the given parameters.
     *
     * @param xPosition the x-coordinate of the ellipse
     * @param yPosition the y-coordinate of the ellipse
     * @param width the width of the ellipse
     * @param height the height of the ellipse
     * @param color the color of the ellipse
     */
    public Ellipse(double xPosition, double yPosition, int width, int height, Color color) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Draws the ellipse using the provided Graphics2D object.
     * This method sets up the coordinates for the ellipse and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        Ellipse2D.Double ellipse = new Ellipse2D.Double(xPosition, yPosition, width, height);
        g2d.setColor(color);
        g2d.fill(ellipse);
    }

    /**
     * Updates the state of the ellipse.
     * This method is currently empty because the ellipse requires no movement.
     */
    @Override
    public void update() {}
}
