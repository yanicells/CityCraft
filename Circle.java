/**
 The Circle class represents a circle object that can be drawn on the screen.
 It implements the DrawingObject class to render the circle.

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

public class Circle implements DrawingObject {
    private double xPosition;
    private double yPosition;
    private int size;
    private Color color;
    private boolean fill;
    private boolean gradient;

    /**
     * Constructs a Circle object with the specified position, size, color, and fill options.
     * Initializes the circle with the given parameters.
     *
     * @param xPosition the x-coordinate of the circle
     * @param yPosition the y-coordinate of the circle
     * @param size the diameter of the circle
     * @param color the color of the circle
     * @param fill whether the circle should be filled
     */
    public Circle(double xPosition, double yPosition, int size, Color color, boolean fill) {
        this(xPosition, yPosition, size, color, fill, false);
    }

    /**
     * Constructs a Circle object with the specified position, size, color, fill, and gradient options.
     * Initializes the circle with the given parameters.
     *
     * @param xPosition the x-coordinate of the circle
     * @param yPosition the y-coordinate of the circle
     * @param size the diameter of the circle
     * @param color the color of the circle
     * @param fill whether the circle should be filled
     * @param gradient whether the circle should have a gradient
     */
    public Circle(double xPosition, double yPosition, int size, Color color, boolean fill, boolean gradient) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = size;
        this.color = color;
        this.fill = fill;
        this.gradient = gradient;
    }

    /**
     * Draws the circle using the provided Graphics2D object.
     * This method sets up the coordinates for the circle and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        Ellipse2D.Double circle = new Ellipse2D.Double(xPosition, yPosition, size, size);

        if (fill && gradient) {
            g2d.setPaint(makeGradient(xPosition + size / 2, yPosition + size / 2, size, color));
            g2d.fill(circle);
        } else if (gradient) {
            g2d.setPaint(makeGradient(xPosition + size / 2, yPosition + size / 2, size, color));
            g2d.setStroke(new BasicStroke(10));
            g2d.draw(circle);
        } else if (fill) {
            g2d.setColor(color);
            g2d.fill(circle);
        } else {
            g2d.setColor(color);
            g2d.draw(circle);
        }
    }

    /**
     * Updates the state of the circle.
     * This method is currently empty because the circle requires no movement.
     */
    @Override
    public void update() {}

    /**
     * Creates a gradient paint for the circle.
     * This method generates a gradient from the top to the bottom of the circle.
     *
     * @param x the x-coordinate of the center of the circle
     * @param y the y-coordinate of the center of the circle
     * @param circleHeight the height of the circle
     * @param topColor the top color of the gradient
     * @return a GradientPaint object representing the gradient
     */
    public GradientPaint makeGradient(double x, double y, int circleHeight, Color topColor) {
        Color bottomColor = topColor.brighter();

        return new GradientPaint((float) x, (float) (y - circleHeight / 2), topColor, (float) x, (float) (y + circleHeight / 2), bottomColor);
    }
}

