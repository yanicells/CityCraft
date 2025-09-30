/**
 The Octagon class represents an octagon object that can be drawn on the screen.
 It implements the DrawingObject class to render the octagon.

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

public class Octagon implements DrawingObject{
    private double[] xPoints;
    private double[] yPoints;
    private Color color;

    /**
     * Constructs an Octagon object with the specified points and color.
     * Initializes the octagon with the given parameters.
     *
     * @param xPoints the x-coordinates of the octagon's points
     * @param yPoints the y-coordinates of the octagon's points
     * @param color the color of the octagon
     * @throws IllegalArgumentException if the number of points is not exactly 8
     */
    public Octagon(double[] xPoints, double[] yPoints, Color color) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.color = color;
    }

    /**
     * Draws the octagon using the provided Graphics2D object.
     * This method sets up the coordinates for the octagon and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        Path2D.Double octagon = new Path2D.Double();
        octagon.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i++) {
            octagon.lineTo(xPoints[i], yPoints[i]);
        }
        octagon.closePath();
        g2d.setColor(color);
        g2d.fill(octagon);
    }

    /**
     * Updates the state of the octagon.
     * This method is currently empty because the octagon requires no movement.
     */
    @Override
    public void update() {

    }
}
