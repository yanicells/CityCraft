/**
 The Pentagon class represents a pentagon object that can be drawn on the screen.
 It implements the DrawingObject class to render the pentagon.

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

public class Pentagon implements DrawingObject{
    private double[] xPoints;
    private double[] yPoints;
    private Color color;

    /**
     * Constructs a Pentagon object with the specified points and color.
     * Initializes the pentagon with the given parameters.
     *
     * @param xPoints the x-coordinates of the pentagon's points
     * @param yPoints the y-coordinates of the pentagon's points
     * @param color the color of the pentagon
     * @throws IllegalArgumentException if the number of points is not exactly 5
     */
    public Pentagon(double[] xPoints, double[] yPoints, Color color) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.color = color;
    }

    /**
     * Draws the pentagon using the provided Graphics2D object.
     * This method sets up the coordinates for the pentagon and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        Path2D.Double pentagon = new Path2D.Double();
        pentagon.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i++) {
            pentagon.lineTo(xPoints[i], yPoints[i]);
        }

        pentagon.closePath();
        g2d.setColor(color);
        g2d.fill(pentagon);
    }

    /**
     * Updates the state of the pentagon.
     * This method is currently empty because the pentagon requires no movement.
     */
    @Override
    public void update() {

    }
}
