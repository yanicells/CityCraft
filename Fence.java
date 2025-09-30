/**
 The Fence class represents a fence object that can be drawn on the screen.
 It implements the DrawingObject class to render the fence.

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
import java.awt.geom.*;

public class Fence implements DrawingObject {
    private int fenceBaseY = 500;
    private int fenceHeight = 75;
    private int postWidth = 15;
    private int postSpacing = 40;
    private Color postColor = new Color(102, 51, 0);
    private Color plankColor = new Color(139, 69, 19);
    private int length;
    private int start;

    /**
     * Constructs a Fence object with the specified length and starting position.
     * Initializes the fence with the given parameters.
     *
     * @param length the length of the fence
     * @param start the starting x-coordinate of the fence
     */
    public Fence(int length, int start) {
        this.length = length;
        this.start = start;
    }

    /**
     * Draws the fence using the provided Graphics2D object.
     * This method sets up the coordinates for the fence and uses transformations
     * to position and draw it on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();
        g2d.translate(5, 0);
        for (int x = start; x <= length + start; x += postSpacing) {
            Rectangle post = new Rectangle(x, fenceBaseY - fenceHeight, postWidth, fenceHeight, postColor.darker());
            post.draw(g2d);
        }
        g2d.setTransform(reset);

        for (int x = start; x <= length + start; x += postSpacing) {
            Rectangle post = new Rectangle(x, fenceBaseY - fenceHeight, postWidth, fenceHeight, postColor);
            post.draw(g2d);
        }

        Rectangle topPlank = new Rectangle(start, fenceBaseY - fenceHeight / 3, length, 10, plankColor);
        Rectangle bottomPlank = new Rectangle(start, fenceBaseY - fenceHeight / 1.5, length, 10, plankColor);

        topPlank.draw(g2d);
        bottomPlank.draw(g2d);
    }

    /**
     * Updates the state of the fence.
     * This method is currently empty because the fence requires no movement.
     */
    @Override
    public void update() {}
}
