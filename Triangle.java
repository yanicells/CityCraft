/**
 The Triangle class represents a triangle object that can be drawn on the screen.
 It implements the DrawingObject class to render the triangle.

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
import java.awt.geom.Path2D;

public class Triangle implements DrawingObject {
    private double[] xPoints;
    private double[] yPoints;
    private Color color;

    /**
     * Constructs a Triangle object with the specified points and color.
     * Initializes the triangle with the given parameters.
     *
     * @param xPoints the x-coordinates of the triangle's points
     * @param yPoints the y-coordinates of the triangle's points
     * @param color the color of the triangle
     */
    public Triangle(double[] xPoints, double[] yPoints, Color color) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.color = color;
    }

    /**
     * Draws the triangle using the provided Graphics2D object.
     * This method sets up the coordinates for the triangle and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        Path2D.Double triangle = new Path2D.Double();
        triangle.moveTo(xPoints[0], yPoints[0]);
        triangle.lineTo(xPoints[1], yPoints[1]);
        triangle.lineTo(xPoints[2], yPoints[2]);
        triangle.closePath();

        g2d.setColor(color);
        g2d.fill(triangle);
    }

    /**
     * Updates the state of the triangle.
     * This method is currently empty because the triangle requires no movement.
     */
    @Override
    public void update() {

    }
}
