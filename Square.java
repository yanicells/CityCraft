/**
 The Square class represents a square object that can be drawn on the screen.
 It implements the DrawingObject class to render the square.

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

public class Square implements DrawingObject{
    private double xPosition;
    private double yPosition;
    private double size;
    private Color color;

    /**
     * Constructs a Square object with the specified position, size, and color.
     * Initializes the square with the given parameters.
     *
     * @param xPosition the x-coordinate of the square
     * @param yPosition the y-coordinate of the square
     * @param size the size of the square
     * @param color the color of the square
     */
    public Square(double xPosition, double yPosition, double size, Color color) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = size;
        this.color = color;
    }

    /**
     * Draws the square using the provided Graphics2D object.
     * This method sets up the coordinates for the square and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        Rectangle2D.Double square = new Rectangle2D.Double(xPosition, yPosition, size, size);
        g2d.setColor(color);
        g2d.fill(square);
    }

    /**
     * Updates the state of the square.
     * This method is currently empty because the square requires no movement.
     */
    @Override
    public void update() {

    }
}
