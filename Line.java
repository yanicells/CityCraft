/**
 The Line class represents a line object that can be drawn on the screen.
 It implements the DrawingObject class to render the line.

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
import java.awt.geom.Line2D;

public class Line implements DrawingObject{
    private int xStartPosition;
    private int yStartPosition;
    private int xEndPosition;
    private int yEndPosition;
    private int lineThickness;
    private Color color;

    /**
     * Constructs a Line object with the specified start and end positions, thickness, and color.
     * Initializes the line with the given parameters.
     *
     * @param xStartPosition the starting x-coordinate of the line
     * @param yStartPosition the starting y-coordinate of the line
     * @param xEndPosition the ending x-coordinate of the line
     * @param yEndPosition the ending y-coordinate of the line
     * @param lineThickness the thickness of the line
     * @param color the color of the line
     */
    public Line(int xStartPosition, int yStartPosition, int xEndPosition, int yEndPosition, int lineThickness, Color color) {
        this.xStartPosition = xStartPosition;
        this.yStartPosition = yStartPosition;
        this.xEndPosition = xEndPosition;
        this.yEndPosition = yEndPosition;
        this.lineThickness = lineThickness;
        this.color = color;
    }

    /**
     * Draws the line using the provided Graphics2D object.
     * This method sets up the coordinates for the line and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        Line2D.Double line = new Line2D.Double(xStartPosition, yStartPosition, xEndPosition, yEndPosition);
        g2d.setStroke(new BasicStroke(lineThickness));
        g2d.setColor(color);
        g2d.draw(line);
    }

    /**
     * Updates the state of the line.
     * This method is currently empty because the line requires no movement.
     */
    @Override
    public void update() {}
}
