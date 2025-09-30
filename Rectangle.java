/**
The Rectangle class represents a rectangle object that can be drawn on the screen.
It implements the DrawingObject class to render the rectangle.

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
import java.awt.geom.Rectangle2D;

public class Rectangle implements DrawingObject{
    private double xPosition;
    private double yPosition;
    private double width;
    private double height;
    private Color color;
    private boolean gradient;

    /**
     * Constructs a Rectangle object with the specified position, dimensions, and color.
     * Initializes the rectangle with the given parameters.
     *
     * @param xPosition the x-coordinate of the rectangle
     * @param yPosition the y-coordinate of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     */
    public Rectangle(double xPosition, double yPosition, double width, double height, Color color) {
        this(xPosition, yPosition, width, height, color, false);
    }

    /**
     * Constructs a Rectangle object with the specified position, dimensions, color, and gradient option.
     * Initializes the rectangle with the given parameters.
     *
     * @param xPosition the x-coordinate of the rectangle
     * @param yPosition the y-coordinate of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     * @param gradient whether the rectangle should have a gradient fill
     */
    public Rectangle(double xPosition, double yPosition, double width, double height, Color color, boolean gradient) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.color = color;
        this.gradient = gradient;
    }


    /**
     * Draws the rectangle using the provided Graphics2D object.
     * This method sets up the coordinates for the rectangle and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        Rectangle2D.Double rectangle = new Rectangle2D.Double(xPosition, yPosition, width, height);
        if (gradient && color != null) {
            g2d.setPaint(makeGradient(xPosition + width / 2, yPosition + height / 2, (int) height, color));
        } else if (color != null) {
            g2d.setColor(color);
        }
        g2d.fill(rectangle);
    }

    /**
     * Updates the state of the rectangle.
     * This method is currently empty because the rectangle requires no movement.
     */
    @Override
    public void update() {

    }

    /**
     * Creates a gradient paint for the rectangle.
     * This method generates a gradient from the top to the bottom of the rectangle.
     *
     * @param x the x-coordinate of the gradient's center
     * @param y the y-coordinate of the gradient's center
     * @param rectHeight the height of the rectangle
     * @param topColor the top color of the gradient
     * @return the GradientPaint object representing the gradient
     */
    public GradientPaint makeGradient(double x, double y, int rectHeight, Color topColor) {
        Color bottomColor = topColor.brighter(); // You can adjust this if needed
        return new GradientPaint((float) x, (float) (y - rectHeight / 2), topColor, (float) x, (float) (y + rectHeight / 2), bottomColor);
    }
}
